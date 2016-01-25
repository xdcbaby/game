package net.scapeemulator.game.msg;

public class ItemOnObjectMessage extends Message {
	private final int objectId, objectX, objectY, itemId, slotId, interfaceId;
	
	public ItemOnObjectMessage(int objectId, int objectX, int objectY, int itemId, int slotId, int interfaceId) {
		this.objectId = objectId;
		this.objectX = objectX;
		this.objectY = objectY;
		this.itemId = itemId;
		this.slotId = slotId;
		this.interfaceId = interfaceId;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
	public int getObjectX() {
		return objectX;
	}
	
	public int getObjectY() {
		return objectY;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public int getSlotId() {
		return slotId;
	}
	
	public int getInterfaceId() {
		return interfaceId;
	}
}
