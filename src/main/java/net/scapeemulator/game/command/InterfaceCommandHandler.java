package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;

public class InterfaceCommandHandler extends CommandHandler {

	public InterfaceCommandHandler() {
		super("inter");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::inter [id]");
			return;
		}
		
		int id = Integer.parseInt(arguments[0]);
		player.getInterfaceSet().openWindow(id);
	}

}
