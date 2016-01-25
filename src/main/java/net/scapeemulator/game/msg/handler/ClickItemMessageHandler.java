package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.consumables.Food;
import net.scapeemulator.game.model.skills.herblore.Herblore;
import net.scapeemulator.game.msg.ClickItemMessage;

public class ClickItemMessageHandler extends MessageHandler<ClickItemMessage> {

	@Override
	public void handle(Player player, ClickItemMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			if (Herblore.cleanHerb(player, message.getSlotId())) {
				return;
			}
			
			for (Food food : Food.values()) {
				if (food.getId() == message.getItemId()) {
					if (Food.consume(player, message.getSlotId())) {
						return;
					}
				}
			}
		}
	}

}
