package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.definition.NPCDefinitions;
import net.scapeemulator.game.model.item.ItemDefinitions;
import net.scapeemulator.game.msg.ItemExamineMessage;

public class ItemExamineMessageHandler extends MessageHandler<ItemExamineMessage> {

	@Override
	public void handle(Player player, ItemExamineMessage message) {
		
		String examine = ItemDefinitions.forId(message.getId()).getDescription();
		if (examine.equalsIgnoreCase("unknown") || examine.length() <= 0) {
			String name = NPCDefinitions.forId(message.getId()).getName();
			if (name.equalsIgnoreCase("null") || name.length() <= 0) {
				name = NPCDefinitions.forId(message.getId() - 1).getName();
			}
			examine = "It's a " + name;
		}
		
		player.sendMessage(examine);
	}

}
