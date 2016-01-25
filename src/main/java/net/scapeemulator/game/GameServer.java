package net.scapeemulator.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;
import net.scapeemulator.cache.Cache;
import net.scapeemulator.cache.ChecksumTable;
import net.scapeemulator.cache.FileStore;
import net.scapeemulator.game.cache.MapSet;
import net.scapeemulator.game.io.DummyPlayerSerializer;
import net.scapeemulator.game.io.JdbcPlayerSerializer;
import net.scapeemulator.game.io.PlayerSerializer;
import net.scapeemulator.game.model.Profanity;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.model.definition.NPCDefinitions;
import net.scapeemulator.game.model.definition.ObjectDefinitions;
import net.scapeemulator.game.model.item.EquipmentDefinition;
import net.scapeemulator.game.model.item.ItemDefinitions;
import net.scapeemulator.game.model.region.RegionMapListener;
import net.scapeemulator.game.msg.codec.CodecRepository;
import net.scapeemulator.game.msg.handler.MessageDispatcher;
import net.scapeemulator.game.net.HttpChannelInitializer;
import net.scapeemulator.game.net.RsChannelInitializer;
import net.scapeemulator.game.net.login.LoginService;
import net.scapeemulator.game.net.update.UpdateService;
import net.scapeemulator.game.pf.TraversalMapListener;
import net.scapeemulator.game.plugin.PluginContext;
import net.scapeemulator.game.plugin.PluginLoader;
import net.scapeemulator.game.tools.ObjectMembersDumper;
import net.scapeemulator.game.util.LandscapeKeyTable;
import net.scapeemulator.util.NetworkConstants;

public final class GameServer {

	private static final Logger logger = LoggerFactory.getLogger(GameServer.class);

	public static void main(String[] args) {
		try {
			InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());

			GameServer server = new GameServer(new InetSocketAddress(NetworkConstants.LOGIN_PORT));

			try {
				server.httpBind(new InetSocketAddress(NetworkConstants.HTTP_PORT));
			} catch (Throwable t) {
				/* TODO: fix Netty's diagnostic message in this case */
				logger.warn("Failed to bind to HTTP port.", t);
			}
			server.httpBind(new InetSocketAddress(NetworkConstants.HTTP_ALT_PORT));

			try {
				server.serviceBind(new InetSocketAddress(NetworkConstants.SSL_PORT));
			} catch (Throwable t) {
				/* TODO: fix Netty's diagnostic message in this case */
				logger.warn("Failed to bind to SSL port.", t);
			}
			server.serviceBind(new InetSocketAddress(NetworkConstants.GAME_PORT));

			server.start();
		} catch (Throwable t) {
			logger.error("Failed to start server.", t);
		}
	}

	private final World world = World.getWorld();
	private final ExecutorService executor = Executors.newCachedThreadPool();
	private final EventLoopGroup loopGroup = new NioEventLoopGroup();
	private final ServerBootstrap serviceBootstrap = new ServerBootstrap();
	private final ServerBootstrap httpBootstrap = new ServerBootstrap();
	private final LoginService loginService;
	private final UpdateService updateService = new UpdateService();
	private final Cache cache;
	private final ChecksumTable checksumTable;
	private final LandscapeKeyTable landscapeKeyTable;
	private final CodecRepository codecRepository;
	private final MessageDispatcher messageDispatcher;
    private final PluginLoader pluginLoader;
	private final int version = 550;

	public GameServer(SocketAddress loginAddress) throws IOException, SQLException, ScriptException {
		logger.info("Starting ScapeEmulator game server...");

		/* load landscape keys */
		landscapeKeyTable = LandscapeKeyTable.open("data/landscape-keys");

		/* load game cache */
		cache = new Cache(FileStore.open("data/cache"));
		checksumTable = cache.createChecksumTable();
		
		ItemDefinitions.init(cache);
        ObjectDefinitions.init(cache);
        EquipmentDefinition.init();
		NPCDefinitions.init(cache);
		
		Profanity.load();
		
        MapSet mapSet = new MapSet();
        mapSet.addListener(new RegionMapListener(world.getRegionManager()));
        mapSet.addListener(new TraversalMapListener(world.getTraversalMap()));
        mapSet.init(cache, landscapeKeyTable);

		/* load message codecs and dispatcher */
		codecRepository = new CodecRepository(landscapeKeyTable);
		messageDispatcher = new MessageDispatcher();

        /* load the server plugins */
        PluginContext pluginContext = new PluginContext();
        pluginLoader = new PluginLoader(pluginContext);
        pluginLoader.load("./data/plugins/");

        /* decorate each of the dispatchers */
        messageDispatcher.decorateDispatchers(pluginContext);

		/* load player serializer from config file */
		PlayerSerializer serializer = createPlayerSerializer();
		logger.info("Using serializer: " + serializer + ".");
		loginService = new LoginService(serializer);

		/* start netty */
		httpBootstrap.group(loopGroup);
		httpBootstrap.channel(NioServerSocketChannel.class);
		httpBootstrap.childHandler(new HttpChannelInitializer());
		serviceBootstrap.group(loopGroup);
		serviceBootstrap.channel(NioServerSocketChannel.class);
		serviceBootstrap.childHandler(new RsChannelInitializer(this));
	}

	private PlayerSerializer createPlayerSerializer() throws IOException, SQLException {
		Properties properties = new Properties();
		try (InputStream is = new FileInputStream("data/serializer.conf")) {
			properties.load(is);
		}

		String type = (String) properties.get("type");
		switch (type) {
		case "dummy":
			return new DummyPlayerSerializer();

		case "jdbc":
			String url = (String) properties.get("url");
			String username = (String) properties.get("username");
			String password = (String) properties.get("password");
			return new JdbcPlayerSerializer(url, username, password);

		default:
			throw new IOException("unknown serializer type");
		}
	}

	public void httpBind(SocketAddress address) throws InterruptedException {
		logger.info("Binding to HTTP address: " + address + "...");
		httpBootstrap.localAddress(address).bind().sync();
	}

	public void serviceBind(SocketAddress address) throws InterruptedException {
		logger.info("Binding to service address: " + address + "...");
		serviceBootstrap.localAddress(address).bind().sync();
	}

	public void start() {
		logger.info("Ready for connections.");

		/* start login and update services */
		executor.submit(loginService);
		executor.submit(updateService);

		/* main game tick loop */
		while(true) {
			long start = System.currentTimeMillis();
			tick();
			long elapsed = (System.currentTimeMillis() - start);
			long waitFor = 600 - elapsed;
			if (waitFor >= 0) {
				try {
					Thread.sleep(waitFor);
				} catch (InterruptedException e) {
					/* ignore */
				}
			}
		}
	}

	private void tick() {
		/*
		 * As the MobList class is not thread-safe, players must be registered
		 * within the game logic processing code.
		 */
		loginService.registerNewPlayers(world);

		world.tick();
	}

	public World getWorld() {
		return world;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public UpdateService getUpdateService() {
		return updateService;
	}

	public int getVersion() {
		return version;
	}

	public Cache getCache() {
		return cache;
	}

	public ChecksumTable getChecksumTable() {
		return checksumTable;
	}

	public CodecRepository getCodecRepository() {
		return codecRepository;
	}

	public MessageDispatcher getMessageDispatcher() {
		return messageDispatcher;
	}

	public LandscapeKeyTable getLandscapeKeyTable() {
		return landscapeKeyTable;
	}

}
