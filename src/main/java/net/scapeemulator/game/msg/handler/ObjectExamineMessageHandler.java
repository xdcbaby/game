package net.scapeemulator.game.msg.handler;

import net.scapeemulator.cache.def.ObjectDefinition;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.definition.ObjectDefinitions;
import net.scapeemulator.game.msg.ObjectExamineMessage;

public class ObjectExamineMessageHandler extends MessageHandler<ObjectExamineMessage> {

	@Override
	public void handle(Player player, ObjectExamineMessage message) {
		String examine = "";
		ObjectDefinition def = ObjectDefinitions.forId(message.getId());
		if (def != null) {
			examine = def.getDescription();
		} else {
			examine = "It's an object.";
		}
		player.sendMessage(examine);
	}

}
