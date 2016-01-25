package net.scapeemulator.game.msg;

public class ClickItemMessage extends Message {
	private final int itemId, interfaceId, slotId;
	
	public ClickItemMessage(int itemId, int interfaceId, int slotId) {
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
	
	public int getSlotId() {
		return slotId;
	}
}
