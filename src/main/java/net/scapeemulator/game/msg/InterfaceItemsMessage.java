package net.scapeemulator.game.msg;

import net.scapeemulator.game.model.item.Item;

public final class InterfaceItemsMessage extends Message {

	private final int id, slot, type;
	private final Item[] items;

	public InterfaceItemsMessage(int id, int slot, int type, Item[] items) {
		this.id = id;
		this.slot = slot;
		this.type = type;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot;
	}

	public int getType() {
		return type;
	}

	public Item[] getItems() {
		return items;
	}

}
