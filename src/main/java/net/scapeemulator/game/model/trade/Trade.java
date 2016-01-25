package net.scapeemulator.game.model.trade;

import java.util.ArrayList;
import java.util.List;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.InterfaceTextMessage;
import net.scapeemulator.game.msg.ScriptMessage;

public class Trade {
	private final List<Player> sentRequests = new ArrayList<>();
	private final List<Player> recievedRequests = new ArrayList<>();
	
	private TradeSession session;
	
	private Player player;
	private Player other;
	
	public Trade(Player player) {
		this.player = player;
	}
	
	public void sendRequest(Player other) {
		if (player.getLocalPlayers().contains(other)) {
			
			if (other.getTrade().isTrading()) {
				player.sendMessage("The other player is busy at the moment");
				return;
			}
			
			if (sentRequests.size() > 5) {
				sentRequests.remove(0);
			}
			
			if (recievedRequests.size() > 5) {
				recievedRequests.remove(0);
			}
			
			if (!sentRequests.contains(other)) {
				sentRequests.add(player);
			}
			
			if (!other.getTrade().recievedRequests.contains(player)) {
				other.getTrade().recievedRequests.add(player);
			}
			
			other.sendMessage(player.getUsername() + ":tradereq:");
			player.sendMessage("Sending trade request...");
			
		} else {
			player.sendMessage("Unable to find " + other.getUsername());
		}
	}
	
	public void acceptRequest(Player other) {
		if (player.getLocalPlayers().contains(other)) {
			
			if (other.getTrade().isTrading()) {
				player.sendMessage("The other player is busy at the moment");
				return;
			}
			
			if (recievedRequests.contains(other)) {
				recievedRequests.remove(other);
				this.other = other;
				
				other.getTrade().sentRequests.remove(player);
				other.getTrade().setOther(player);
				
				
				session = new TradeSession(player, other);
				openTrade();
				
			} else {
				sendRequest(other);
			}
			
		} else {
			player.sendMessage("Unable to find " + player.getUsername());
			return;
		}
	}
	
	public void openTrade() {
		if (other != null) {
			player.getInterfaceSet().openWindow(Interface.TRADE);
			player.send(new InterfaceTextMessage(Interface.TRADE, 15, "Trading with: " + other.getUsername()));
			player.send(new InterfaceTextMessage(Interface.TRADE, 37, ""));
			player.send(new InterfaceTextMessage(Interface.TRADE, 21, other.getUsername() + " has " + other.getInventory().freeSlots() + " free inventory slot."));
			player.send(new InterfaceTextMessage(Interface.TRADE, 41, "Limit: 10,000"));
			player.send(new InterfaceTextMessage(Interface.TRADE, 43, "Limit: 10,000"));

			other.getInterfaceSet().openWindow(Interface.TRADE);
			other.send(new InterfaceTextMessage(Interface.TRADE, 15, "Trading with: " + player.getUsername()));
			other.send(new InterfaceTextMessage(Interface.TRADE, 37, ""));
			other.send(new InterfaceTextMessage(Interface.TRADE, 21, player.getUsername() + " has " + player.getInventory().freeSlots() + " free inventory slot."));
			other.send(new InterfaceTextMessage(Interface.TRADE, 41, "Limit: 10,000"));
			other.send(new InterfaceTextMessage(Interface.TRADE, 43, "Limit: 10,000"));
		} else {
			player.sendMessage("Unable to find " + other.getUsername() + ".");
		}
	}
	
	public void flashIcon(Player player, int slot) {
		Object[] params = new Object[] { slot, 7, 4,  21954593 };
		player.send(new ScriptMessage(143, "Iiii", params));
	}
	
	public void setOther(Player player) {
		this.other = player;
	}
	
	public TradeSession getSession() {
		return session;
	}
	
	public boolean isTrading() {
		return session != null && session.getState() != TradeState.NOT_IN_TRADE;
	}
}
