package net.scapeemulator.game.model.item;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.SlottedItem;
import net.scapeemulator.game.msg.InterfaceItemsMessage;
import net.scapeemulator.game.msg.InterfaceResetItemsMessage;
import net.scapeemulator.game.msg.InterfaceSlottedItemsMessage;

public final class InventoryMessageListener implements InventoryListener {

	private final Player player;
	private final int id, slot, type;

	public InventoryMessageListener(Player player, int id, int slot, int type) {
		this.player = player;
		this.id = id;
		this.slot = slot;
		this.type = type;
	}

	@Override
	public void itemChanged(Inventory inventory, int slot, Item item) {
		SlottedItem[] items = new SlottedItem[] {
			new SlottedItem(slot, item)
		};
		player.send(new InterfaceSlottedItemsMessage(id, this.slot, type, items));
	}

	@Override
	public void itemsChanged(Inventory inventory) {
		if (inventory.isEmpty()) {
			// TODO: consider how this interacts with the 'type'?
			player.send(new InterfaceResetItemsMessage(id, slot));
		} else {
			Item[] items = inventory.toArray();
			player.send(new InterfaceItemsMessage(id, slot, type, items));
		}
	}

	@Override
	public void capacityExceeded(Inventory inventory) {
		/* empty */
	}

}
