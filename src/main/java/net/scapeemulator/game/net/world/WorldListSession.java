package net.scapeemulator.game.net.world;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import net.scapeemulator.game.GameServer;
import net.scapeemulator.game.net.Session;

public final class WorldListSession extends Session {

	private static final Country[] COUNTRIES = {
        new Country(Country.FLAG_USA, "US"),
		new Country(Country.FLAG_UK, "UK")
	};

	public WorldListSession(GameServer server, Channel channel) {
		super(server, channel);
	}

	@Override
	public void messageReceived(Object message) {
		World[] worlds = { new World(1, World.FLAG_MEMBERS | World.FLAG_HIGHLIGHT, 0, "-", "127.0.0.1") };
		int[] players = { 0 };
		channel.write(new WorldListMessage(0xDEADBEEF, COUNTRIES, worlds, players)).addListener(ChannelFutureListener.CLOSE);
	}

}
