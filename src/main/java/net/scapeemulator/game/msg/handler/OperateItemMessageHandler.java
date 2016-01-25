package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.action.impl.EmoteAction;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.msg.OperateItemMessage;

public class OperateItemMessageHandler extends MessageHandler<OperateItemMessage> {

	@Override
	public void handle(Player player, OperateItemMessage message) {
		if (message.getSlotId() == Equipment.CAPE) {
			if (message.getItemId() == 9750 || message.getItemId() == 9751) {
				player.startAction(new EmoteAction(player, 4981, 828, 11));
				return;
			} 
		} else if (message.getSlotId() == Equipment.SHIELD) {
			return;
		}
		player.sendMessage("Nothing interesting happens.");
	}

}
