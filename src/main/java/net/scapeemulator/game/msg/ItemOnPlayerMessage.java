package net.scapeemulator.game.msg;

public class ItemOnPlayerMessage extends Message {
	private int localPlayerId, slotId, itemId;
	
	public ItemOnPlayerMessage(int localPlayerId, int slotId, int itemId) {
		this.localPlayerId = localPlayerId;
		this.slotId = slotId;
		this.itemId = itemId;
	}
	
	public int getLocalPlayerId() {
		return localPlayerId;
	}
	
	public int getSlotId() {
		return slotId;
	}
	
	public int getItemId() {
		return itemId;
	}
}
