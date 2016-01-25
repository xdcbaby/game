package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.MoveItemMessage;

public class MoveItemMessageHandler extends MessageHandler<MoveItemMessage> {

	@Override
	public void handle(Player player, MoveItemMessage message) {
		int oldSlot = message.getOldSlotId();
		int newSlot = message.getNewSlotId();
		Item item = player.getInventory().get(oldSlot);
		player.getInventory().set(newSlot, item);
		player.getInventory().set(oldSlot, null);
	}

}
