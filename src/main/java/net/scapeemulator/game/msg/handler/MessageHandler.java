package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.Message;

public abstract class MessageHandler<T extends Message> {

	public abstract void handle(Player player, T message);

}
