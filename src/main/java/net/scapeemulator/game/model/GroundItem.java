package net.scapeemulator.game.model;

import net.scapeemulator.game.model.item.Item;

public final class GroundItem extends Entity {

	private final Item item;

	public GroundItem(Position position, Item item) {
		this.position = position;
		this.item = item;
	}

	public Position getPosition() {
		return position;
	}

	public Item getItem() {
		return item;
	}

}
