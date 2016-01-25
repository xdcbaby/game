package net.scapeemulator.game.msg;

public class MoveItemMessage extends Message {
	private final int interfaceId, oldSlotId, newSlotId;
	
	public MoveItemMessage(int interfaceId, int oldSlotId, int newSlotId) {
		this.interfaceId = interfaceId;
		this.oldSlotId = oldSlotId;
		this.newSlotId = newSlotId;
	}
	
	public int getInterfaceId() {
		return interfaceId;
	}
	
	public int getOldSlotId() {
		return oldSlotId;
	}
	
	public int getNewSlotId() {
		return newSlotId;
	}
}
