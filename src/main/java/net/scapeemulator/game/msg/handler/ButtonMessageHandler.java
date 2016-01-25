package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.button.ButtonDispatcher;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ButtonMessage;

public final class ButtonMessageHandler extends MessageHandler<ButtonMessage> {

	private final ButtonDispatcher dispatcher;

	public ButtonMessageHandler(ButtonDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void handle(Player player, ButtonMessage message) {
		dispatcher.handle(player, message.getId(), message.getSlot(), 0);
	}

}
