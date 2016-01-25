package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.RemoveItemMessage;

public final class RemoveItemMessageHandler extends MessageHandler<RemoveItemMessage> {

	@Override
	public void handle(Player player, RemoveItemMessage message) {
		if (message.getId() == Interface.EQUIPMENT && message.getSlot() == 29) { //slot == 28
			Item item = player.getEquipment().get(message.getItemSlot());
			if (item == null || item.getId() != message.getItemId())
				return;
			Equipment.remove(player, message.getItemSlot());
		}
	}

}
