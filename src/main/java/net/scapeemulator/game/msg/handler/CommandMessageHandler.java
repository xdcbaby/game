package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.command.CommandDispatcher;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.CommandMessage;

public final class CommandMessageHandler extends MessageHandler<CommandMessage> {

	private final CommandDispatcher dispatcher;

    public CommandMessageHandler(CommandDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
	public void handle(Player player, CommandMessage message) {
		dispatcher.handle(player, message.getCommand());
	}

}
