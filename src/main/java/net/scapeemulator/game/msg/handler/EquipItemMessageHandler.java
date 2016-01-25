package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.EquipItemMessage;

public final class EquipItemMessageHandler extends MessageHandler<EquipItemMessage> {

	public void handle(Player player, EquipItemMessage message) {
		
		if (message.getInterfaceId() == Interface.INVENTORY && message.getSlot() == 0) {
			Item item = player.getInventory().get(message.getInventorySlot());
			if (item == null || item.getId() != message.getItemId()) {
				return;
			}
			Equipment.equip(player, message.getInventorySlot());
		}
	}

}

