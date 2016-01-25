package net.scapeemulator.game.model;

import net.scapeemulator.game.model.item.Item;

public final class SlottedItem {

	private final int slot;
	private final Item item;

	public SlottedItem(int slot, Item item) {
		this.slot = slot;
		this.item = item;
	}

	public int getSlot() {
		return slot;
	}

	public Item getItem() {
		return item;
	}

}
