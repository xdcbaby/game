package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Player;

public class ReportAbuseButtonHandler extends ButtonHandler {

	public ReportAbuseButtonHandler() {
		super(751);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 28) {
			player.getInterfaceSet().openWindow(553);
		}
	}

}
