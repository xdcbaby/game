package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class LogoutButtonHandler extends ButtonHandler {

	public LogoutButtonHandler() {
		super(Interface.LOGOUT);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 6) {
			player.logout();
		}
	}

}
