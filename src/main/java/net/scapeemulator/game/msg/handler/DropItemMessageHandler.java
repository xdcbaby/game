package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.DropItemMessage;

public class DropItemMessageHandler extends MessageHandler<DropItemMessage> {

	@Override
	public void handle(Player player, DropItemMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			Item item = player.getInventory().get(message.getSlot());
			if (item != null) {
				player.getInventory().remove(item, message.getSlot());
				//TODO: Create ground object
			}
		}
	}

}
