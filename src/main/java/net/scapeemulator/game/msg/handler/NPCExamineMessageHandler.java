package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.definition.NPCDefinitions;
import net.scapeemulator.game.msg.NpcExamineMessage;

public final class NPCExamineMessageHandler extends MessageHandler<NpcExamineMessage> {

	public void handle(Player player, NpcExamineMessage msg) {
		String examine = NPCDefinitions.forId(msg.getType()).getExamine();
		if (examine.equalsIgnoreCase("unknown") || examine.length() <= 0) {
			examine = "It's a " + NPCDefinitions.forId(msg.getType()).getName();
		}
		player.sendMessage(examine);
	}
}
