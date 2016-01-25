package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;

public class BankCommandHandler extends CommandHandler {

	public BankCommandHandler() {
		super("bank");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 0) {
			return;
		}
		player.getInterfaceSet().openBank();
	}

}
