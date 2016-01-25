package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.model.skills.herblore.Herblore;
import net.scapeemulator.game.msg.ItemOnItemMessage;

public class ItemOnItemMessageHandler extends MessageHandler<ItemOnItemMessage> {

	@Override
	public void handle(Player player, ItemOnItemMessage message) {
		if (message.post(message.getItemUsed(), Herblore.VIAL)) {
			if (Herblore.makeUnfinishedPotion(player, player.getInventory().get(message.getItemUsedSlot()))) {
				return;
			}
			if (Herblore.makeUnfinishedPotion(player, player.getInventory().get(message.getUsedOnItemSlot()))) {
				return;
			}
		}
		if (Herblore.makePotion(player, player.getInventory().get(message.getItemUsedSlot()), player.getInventory().get(message.getUsedOnItemSlot()))) {
			return;
		}
		
		if (message.getItemUsed() == Herblore.PESTLE_AND_MORTAR) {
			if (Herblore.grind(player, message.getItemUsedWith())) {
				return;
			}
		} else if(message.getItemUsedWith() == Herblore.PESTLE_AND_MORTAR) {
			if (Herblore.grind(player, message.getItemUsed())) {
				return;
			}
		}
		
		
		if (message.post(11690, 11702)) {
			player.getInventory().remove(player.getInventory().get(message.getItemUsedSlot()));
			player.getInventory().remove(player.getInventory().get(message.getUsedOnItemSlot()));
			player.getInventory().add(new Item(11694, 1));
			return;
		}
	}

}
