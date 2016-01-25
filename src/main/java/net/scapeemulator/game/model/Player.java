package net.scapeemulator.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.item.EquipmentDefinition;
import net.scapeemulator.game.model.item.Inventory;
import net.scapeemulator.game.model.item.InventoryAppearanceListener;
import net.scapeemulator.game.model.item.InventoryFullListener;
import net.scapeemulator.game.model.item.InventoryMessageListener;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.model.membership.Membership;
import net.scapeemulator.game.model.trade.Trade;
import net.scapeemulator.game.msg.ChatMessage;
import net.scapeemulator.game.msg.EnergyMessage;
import net.scapeemulator.game.msg.LogoutMessage;
import net.scapeemulator.game.msg.Message;
import net.scapeemulator.game.msg.ServerMessage;
import net.scapeemulator.game.net.game.GameSession;

public final class Player extends Mob {
	private static final Logger logger = LoggerFactory.getLogger(Player.class);
	private static int appearanceTicketCounter = 0;

	private static int nextAppearanceTicket() {
		if (++appearanceTicketCounter == 0)
			appearanceTicketCounter = 1;

		return appearanceTicketCounter;
	}

	private int databaseId;
	private String username;
	private String password;
	private int rights = 0;
	private GameSession session;
	private boolean regionChanging;
	private Position lastKnownRegion;
	private final List<Player> localPlayers = new ArrayList<>();
	private final List<Npc> localNpcs = new ArrayList<>();
	private Appearance appearance = Appearance.DEFAULT_APPEARANCE;
	private int energy = 100;
	private final Inventory inventory = new Inventory(28);
	private final Inventory equipment = new Inventory(14);
	private final Inventory bank = new Inventory(496, Inventory.StackMode.ALWAYS);
	private ChatMessage chatMessage;
	private final PlayerSettings settings = new PlayerSettings(this);
	private final InterfaceSet interfaceSet = new InterfaceSet(this);
	private int[] appearanceTickets = new int[World.MAX_PLAYERS];
	private int appearanceTicket = nextAppearanceTicket();
	
	private final Trade trade = new Trade(this);
	private final Membership membership = new Membership();
	
	public Membership getMembership() {
		return membership;
	}
	
	public Trade getTrade() {
		return trade;
	}

	private final Map<String, Object> attributes = new HashMap<>();
	
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}
	
	public Object getAttribute(String key) {
		return attributes.get(key);
	}
	
	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	public Player() {
		init();
	}

	private void init() {
		skillSet.addListener(new SkillMessageListener(this));
		skillSet.addListener(new SkillAppearanceListener(this));

		inventory.addListener(new InventoryMessageListener(this, 149, 0, 93));
		inventory.addListener(new InventoryFullListener(this, "inventory"));

		bank.addListener(new InventoryFullListener(this, "bank"));

		equipment.addListener(new InventoryMessageListener(this, 387, 29, 94));
		equipment.addListener(new InventoryFullListener(this, "equipment"));
		equipment.addListener(new InventoryAppearanceListener(this));
	}

	public int getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(int databaseId) {
		this.databaseId = databaseId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public GameSession getSession() {
		return session;
	}
	
	public void setSession(GameSession session) {
		this.session = session;
	}

	public ChannelFuture send(Message message) {
		if (session != null)
			return session.send(message);
		else
			return null;
	}

	public void sendMessage(String text) {
		send(new ServerMessage(text));
	}

	public boolean isRegionChanging() {
		return regionChanging;
	}

	public Position getLastKnownRegion() {
		return lastKnownRegion;
	}

	public void setLastKnownRegion(Position lastKnownRegion) {
		this.lastKnownRegion = lastKnownRegion;
		this.regionChanging = true;
	}

	public List<Player> getLocalPlayers() {
		return localPlayers;
	}

	public List<Npc> getLocalNpcs() {
		return localNpcs;
	}

	public int getAppearanceTicket() {
		return appearanceTicket;
	}

	public int[] getAppearanceTickets() {
		return appearanceTickets;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
		this.appearanceTicket = nextAppearanceTicket();
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		this.send(new EnergyMessage(energy));
	}

	public ChatMessage getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessage message) {
		this.chatMessage = message;
	}

	public boolean isChatUpdated() {
		return chatMessage != null;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Inventory getEquipment() {
		return equipment;
	}

	public Inventory getBank() {
		return bank;
	}

	public int getStance() {
		Item weapon = equipment.get(Equipment.WEAPON);
		if (weapon != null)
			return EquipmentDefinition.forId(weapon.getId()).getStance();
		else
			return 1426;
	}

	public PlayerSettings getSettings() {
		return settings;
	}

	public InterfaceSet getInterfaceSet() {
		return interfaceSet;
	}

	public void logout() {
		// TODO this seems fragile
		ChannelFuture future = send(new LogoutMessage());
		if (future != null)
			future.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void reset() {
		super.reset();
		regionChanging = false;
		chatMessage = null;
	}

	@Override
	public boolean isRunning() {
		return settings.isRunning();
	}

	public boolean isHitUpdated() {
		return false;
	}
}
