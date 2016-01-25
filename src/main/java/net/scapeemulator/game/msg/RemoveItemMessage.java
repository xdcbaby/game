package net.scapeemulator.game.msg;

public final class RemoveItemMessage extends Message {

	private final int id, slot, itemSlot, itemId;

	public RemoveItemMessage(int id, int slot, int itemSlot, int itemId) {
		this.id = id;
		this.slot = slot;
		this.itemSlot = itemSlot;
		this.itemId = itemId;
	}

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot;
	}

	public int getItemSlot() {
		return itemSlot;
	}

	public int getItemId() {
		return itemId;
	}

}
