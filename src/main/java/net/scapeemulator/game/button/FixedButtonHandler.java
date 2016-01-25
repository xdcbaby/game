package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class FixedButtonHandler extends ButtonHandler {

	public FixedButtonHandler() {
		super(Interface.FIXED);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 70) {
			player.getInterfaceSet().openWorldMap();
		}
	}

}
