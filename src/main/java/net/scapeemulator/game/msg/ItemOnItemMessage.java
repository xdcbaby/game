package net.scapeemulator.game.msg;

public class ItemOnItemMessage extends Message {
	private final int interfaceId, itemUsed, usedOnItem, itemUsedSlot, usedOnItemSlot;
	
	public ItemOnItemMessage(int interfaceId, int itemUsed, int itemUsedSlot, int usedOnItem, int usedOnItemSlot) {
		this.interfaceId = interfaceId;
		this.itemUsed = itemUsed;
		this.itemUsedSlot = itemUsedSlot;
		this.usedOnItem = usedOnItem;
		this.usedOnItemSlot = usedOnItemSlot;
	}
	
	public boolean post(int item1, int item2) {
		if (itemUsed == item1 && usedOnItem == item2) {
			return true;
		}
		if (usedOnItem == item1 && itemUsed == item2) {
			return true;
		}
		return false;
	}
	
	public int getInterfaceId() {
		return interfaceId;
	}
	
	public int getItemUsed() {
		return itemUsed;
	}

	public int getItemUsedSlot() {
		return itemUsedSlot;
	}
	
	public int getItemUsedWith() {
		return usedOnItem;
	}
	
	public int getUsedOnItemSlot() {
		return usedOnItemSlot;
	}
}
