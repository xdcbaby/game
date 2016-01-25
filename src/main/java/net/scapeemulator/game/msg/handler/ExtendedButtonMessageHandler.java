package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.button.ButtonDispatcher;
import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ExtendedButtonMessage;

public final class ExtendedButtonMessageHandler extends MessageHandler<ExtendedButtonMessage> {

	private final ButtonDispatcher dispatcher;

	public ExtendedButtonMessageHandler(ButtonDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void handle(Player player, ExtendedButtonMessage message) {
		dispatcher.handle(player, message.getId(), message.getSlot(), message.getParameter());
	}

}
