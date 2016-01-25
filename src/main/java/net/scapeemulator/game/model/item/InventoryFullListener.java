package net.scapeemulator.game.model.item;

import net.scapeemulator.game.model.Player;

public final class InventoryFullListener implements InventoryListener {

	private final Player player;
	private final String name;

	public InventoryFullListener(Player player, String name) {
		this.player = player;
		this.name = name;
	}

	@Override
	public void itemChanged(Inventory inventory, int slot, Item item) {
		/* ignore */
	}

	@Override
	public void itemsChanged(Inventory inventory) {
		/* ignore */
	}

	@Override
	public void capacityExceeded(Inventory inventory) {
		player.sendMessage("Not enough " + name + " space.");
	}

}
