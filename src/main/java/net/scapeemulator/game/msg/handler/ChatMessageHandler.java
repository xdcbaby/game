package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ChatMessage;

public final class ChatMessageHandler extends MessageHandler<ChatMessage> {

	@Override
	public void handle(Player player, ChatMessage message) {
		player.setChatMessage(message);
	}

}
