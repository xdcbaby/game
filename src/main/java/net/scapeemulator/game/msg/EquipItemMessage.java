package net.scapeemulator.game.msg;

public final class EquipItemMessage extends Message {

	private final int interfaceId, inventorySlot, itemId, slot;

	public EquipItemMessage(int interfaceId, int inventorySlot, int itemId, int slot) {
		this.interfaceId = interfaceId;
		this.inventorySlot = inventorySlot;
		this.itemId = itemId;
		this.slot = slot;
	}

	public int getInterfaceId() {
		return interfaceId;
	}

	public int getInventorySlot() {
		return inventorySlot;
	}

	public int getItemId() {
		return itemId;
	}

	public int getSlot() {
		return slot;
	}

}
