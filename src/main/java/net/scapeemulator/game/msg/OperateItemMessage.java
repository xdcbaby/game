package net.scapeemulator.game.msg;

public class OperateItemMessage extends Message {
	private int slotId, itemId;
	
	public OperateItemMessage(int slotId, int itemId) {
		this.slotId = slotId;
		this.itemId = itemId;
	}
	
	public int getSlotId() {
		return slotId;
	}
	
	public int getItemId() {
		return itemId;
	}
}
