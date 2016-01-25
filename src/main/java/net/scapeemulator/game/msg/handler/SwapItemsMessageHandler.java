package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.SwapItemsMessage;

public final class SwapItemsMessageHandler extends MessageHandler<SwapItemsMessage> {

	@Override
	public void handle(Player player, SwapItemsMessage message) {
		if (message.getId() == Interface.INVENTORY && message.getSlot() == 0) {
			player.getInventory().swap(message.getSource(), message.getDestination());
		}
	}

}
