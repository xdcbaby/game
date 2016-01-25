package net.scapeemulator.game.model.trade;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Inventory;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.InterfaceItemsMessage;
import net.scapeemulator.game.msg.InterfaceTextMessage;

public class TradeSession {
	private Player player;
	private Player other;
	
	private TradeState state = TradeState.NOT_IN_TRADE;
	private Inventory offer = new Inventory(28);
	
	public TradeSession(Player player, Player other) {
		this.player = player;
		this.other = other;
	}
	
	public void addItem(Item item) {
		if (player.getInventory().contains(item.getId()) && state == TradeState.TRADING_FIRST_SCREEN) {
			if (item.getDefinition().isTradeable()) {
				offer.add(item);
				player.getInventory().remove(item);
				update();
			} else {
				player.sendMessage("That item is not tradeable.");
			}
		}
	}
	
	public void removeItem(Item item) {
		if (offer.contains(item.getId()) && state == TradeState.TRADING_FIRST_SCREEN) {
			offer.remove(item);
			player.getInventory().add(item);
			update();
		}
	}
	
	public void update() {
		state = TradeState.TRADING_FIRST_SCREEN;
		other.getTrade().getSession().setState(TradeState.TRADING_FIRST_SCREEN);
		
		player.send(new InterfaceItemsMessage(-1, 2, 90, offer.toArray()));
		player.send(new InterfaceItemsMessage(-2, 60981, 90, other.getTrade().getSession().getOffer().toArray()));
		player.send(new InterfaceItemsMessage(-1, 1, 93, player.getInventory().toArray()));
		
		other.send(new InterfaceItemsMessage(-1, 2, 90, other.getTrade().getSession().getOffer().toArray()));
		other.send(new InterfaceItemsMessage(-2, 60981, 90, offer.toArray()));
		other.send(new InterfaceItemsMessage(-1, 1, 93, other.getInventory().toArray()));
	}
	
	public void preAccept() {
		state = TradeState.PRE_ACCEPT;
		
		if(other.getTrade().getSession().getState() == TradeState.PRE_ACCEPT) {
			
			player.getInterfaceSet().openWindow(334); //TODO: Get correct id
			other.getInterfaceSet().openWindow(334);
			
			
		} else {
			//TODO: Fix these config ids
			player.send(new InterfaceTextMessage(335, 33, "Waiting for other player..."));
			other.send(new InterfaceTextMessage(335, 33, "Other player has accepted."));
		}
	}
	
	public void acceptTrade() {
		state = TradeState.ACCEPTED;
		
		if (other.getTrade().getSession().getState() == TradeState.ACCEPTED) {
			
			Item[] playerItems = offer.toArray();
			Item[] otherItems = other.getTrade().getSession().getOffer().toArray();
			
			for(Item item : playerItems) {
				if (item != null) {
					other.getInventory().add(item);
				}
			}
			
			for(Item item : otherItems) {
				if (item != null) {
					player.getInventory().add(item);
				}
			}
			
			player.getInterfaceSet().closeWindow();
			other.getInterfaceSet().closeWindow();
			
			player.sendMessage("Trade accepted.");
			other.sendMessage("Trade accepted.");
			
			state = TradeState.NOT_IN_TRADE;
			other.getTrade().getSession().setState(TradeState.NOT_IN_TRADE);
			
		} else {
			player.send(new InterfaceTextMessage(334, 33, "Waiting for other player..."));
			other.send(new InterfaceTextMessage(334, 33, "Other player has accepted."));
		}
	}
	
	public void declineTrade() {
		Item[] playerItems = offer.toArray();
		Item[] otherItems = other.getTrade().getSession().getOffer().toArray();
		
		for(Item item : playerItems) {
			if (item != null) {
				player.getInventory().add(item);
			}
		}
		
		for(Item item : otherItems) {
			if (item != null) {
				other.getInventory().add(item);
			}
		}
		
		offer.empty();
		other.getTrade().getSession().getOffer().empty();
		
		state = TradeState.NOT_IN_TRADE;
		other.getTrade().getSession().setState(TradeState.NOT_IN_TRADE);
		
		other.getInterfaceSet().closeWindow();
		player.getInterfaceSet().closeWindow();
		
		other.sendMessage("Other player declined the trade.");
		return;
	}
	
	public boolean isTrading() {
		return state != TradeState.NOT_IN_TRADE;
	}
	
	public void setState(TradeState state) {
		this.state = state;
	}
	
	public TradeState getState() {
		return state;
	}
	
	public Inventory getOffer() {
		return offer;
	}
}
