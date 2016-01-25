package net.scapeemulator.game.model;

import java.util.Arrays;

import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.InterfaceCloseMessage;
import net.scapeemulator.game.msg.InterfaceItemsMessage;
import net.scapeemulator.game.msg.InterfaceOpenMessage;
import net.scapeemulator.game.msg.InterfaceRootMessage;
import net.scapeemulator.game.msg.InterfaceTextMessage;
import net.scapeemulator.game.msg.ScriptIntMessage;

public final class InterfaceSet {

	public enum DisplayMode {
		FIXED, RESIZABLE;
	}

	private final Player player;
	private final int[] tabs = new int[15];
	private int fullscreen = -1;
	private DisplayMode mode = DisplayMode.FIXED;

	public InterfaceSet(Player player) {
		this.player = player;

		Arrays.fill(tabs, -1);
	}

	public DisplayMode getDisplayMode() {
		return mode;
	}

	public void setDisplayMode(DisplayMode mode) {
		this.mode = mode;
	}

	public void init() {
		// TODO close any windows/overlays/etc. that may be left open if not reconnecting ?
		// also consider the display mode changing
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceRootMessage(Interface.FIXED));
			player.send(new InterfaceOpenMessage(Interface.FIXED, 81, 752, 1)); // chat box
			player.send(new InterfaceOpenMessage(Interface.FIXED, 18, 751, 1)); // chat options
			player.send(new InterfaceOpenMessage(752, 8, 137, 1));  // chat username & scroll bar
			player.send(new InterfaceOpenMessage(Interface.FIXED, 10, 754, 1)); // PM split chat

			player.send(new InterfaceOpenMessage(Interface.FIXED, 75, Interface.HITPOINTS_ORB, 1)); // hitpoints orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 76, Interface.PRAYER_ORB, 1)); // prayer orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 77, Interface.ENERGY_ORB, 1)); // energy orb
			player.send(new InterfaceOpenMessage(Interface.FIXED, 78, Interface.SUMMONING_ORB, 1)); // summoning orb
		} else {
			player.send(new InterfaceRootMessage(Interface.RESIZABLE));
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 70, 752, 1)); // chat box
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 23, 751, 1)); // chat options
			player.send(new InterfaceOpenMessage(752, 8, 137, 1));  // chat username & scroll bar
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 71, 754, 1)); // PM split chat (correct?)

			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 13, Interface.HITPOINTS_ORB, 1)); // hitpoints orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 14, Interface.PRAYER_ORB, 1)); // prayer orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 15, Interface.ENERGY_ORB, 1)); // energy orb
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 16, Interface.SUMMONING_ORB, 1)); // summoning orb
		}

		Equipment.openAttackTab(player);
		openTab(Tab.SKILLS, Interface.SKILLS);
		openTab(Tab.QUEST, Interface.QUESTS);
		openTab(Tab.INVENTORY, Interface.INVENTORY);
		openTab(Tab.EQUIPMENT, Interface.EQUIPMENT);
		openTab(Tab.PRAYER, Interface.PRAYER);
		openTab(Tab.MAGIC, Interface.MAGIC);
		openTab(Tab.FRIENDS, Interface.FRIENDS);
		openTab(Tab.IGNORES, Interface.IGNORES);
		openTab(Tab.CLAN, Interface.CLAN);
		openTab(Tab.SETTINGS, Interface.SETTINGS);
		openTab(Tab.EMOTES, Interface.EMOTES);
		openTab(Tab.MUSIC, Interface.MUSIC);
		openTab(Tab.LOGOUT, Interface.LOGOUT);

		//openTab(Tab.SUMMONING, Interface.SUMMONING);
		//for (int i = 0; i < 6; i++)
		//	player.send(new InterfaceVisibleMessage(747, i, true));
	}

	public int getTab(int tab) {
		return tabs[tab];
	}

	public int getFullscreen() {
		return fullscreen;
	}

	public void openTab(int tab, int id) {
		tabs[tab] = id;
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 89 + tab, id, 1));
		} else {
			// 76 = force a single tab to be shown ?
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 93 + tab, id, 1));
		}
	}

	public void switchToTab(int tab) {
		player.send(new ScriptIntMessage(168, tab));
	}

	public void closeTab(int tab) {
		tabs[tab] = -1;
		if (mode == DisplayMode.FIXED) {

		} else {

		}
	}

	public void openWindow(int id) {
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 15, id, 0));
		} else {
			// TODO: id == 499 => slot 5 in xeno
			// TODO: another source: 3 norm, 4 for bank , 6 for help?
			// somewhere else it uses 8?
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 6, id, 0));
		}
	}

	public void openOverlay(int id) {
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceOpenMessage(Interface.FIXED, 5, id, 1));
		} else {
			player.send(new InterfaceOpenMessage(Interface.RESIZABLE, 5, id, 1));
		}
	}

	public void openFullscreen(int id) {
		fullscreen = id;
		player.send(new InterfaceRootMessage(id));
	}

	public void closeFullscreen() {
		fullscreen = -1;
		if (mode == DisplayMode.FIXED) {
			player.send(new InterfaceRootMessage(Interface.FIXED));
		} else {
			player.send(new InterfaceRootMessage(Interface.RESIZABLE));
		}
	}
	
	public void closeWindow() {
		player.send(new InterfaceCloseMessage(Interface.FIXED, 15));
	}

	public void openWorldMap() {
		openFullscreen(Interface.WORLD_MAP);
		player.send(new ScriptIntMessage(622, player.getPosition().toPackedInt())); // map center
		player.send(new ScriptIntMessage(674, player.getPosition().toPackedInt())); // player position
	}

	public void openBank() {
		player.getBank().add(new Item(11694));
		Item[] bankItems = player.getBank().toArray();
		Item[] inventItems = player.getInventory().toArray();
		
	// TODO: check, following ScriptIntMessage packet may be wrong
		player.send(new ScriptIntMessage(563, 4194304));
		player.send(new ScriptIntMessage(1248, -2013265920));
		
		//TODO: send bank options
		
		//refresh bank
		player.send(new InterfaceItemsMessage(-1, 64207, 95, bankItems));
		player.send(new InterfaceItemsMessage(-1, 64209, 93, inventItems));
		player.send(new InterfaceItemsMessage(149, 0, 93, inventItems));
		
		for (int i = 0; i < 40; i++) {
			player.send(new InterfaceTextMessage(Interface.BANK, i, "" + i));
		}
		
		//display interface
		openWindow(Interface.BANK);
		openTab(Tab.INVENTORY, 763);
		
		for (int i = 0; i < 40; i++) {
			player.send(new InterfaceTextMessage(Interface.BANK, i, "" + i));
		}
	}
}
