package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class EnergyOrbButtonHandler extends ButtonHandler {

	public EnergyOrbButtonHandler() {
		super(Interface.ENERGY_ORB);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 1) {
			player.getSettings().toggleRunning();
		}
	}

}
