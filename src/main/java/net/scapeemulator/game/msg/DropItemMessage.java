package net.scapeemulator.game.msg;

public class DropItemMessage extends Message {
	private int itemId, interfaceId, slotId;
	
	public DropItemMessage(int itemId, int interfaceId, int slotId) {
		this.itemId = itemId;
		this.interfaceId = interfaceId;
		this.slotId = slotId;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getInterfaceId() {
		return interfaceId;
	}
	
	public int getSlot() {
		return slotId;
	}
}
