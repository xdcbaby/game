package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class ResizableButtonHandler extends ButtonHandler {

	public ResizableButtonHandler() {
		super(Interface.RESIZABLE);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 110) {
			player.getInterfaceSet().openWorldMap();
		}
	}

}
