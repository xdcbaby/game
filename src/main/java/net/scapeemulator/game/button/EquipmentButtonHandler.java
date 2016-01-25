package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public class EquipmentButtonHandler extends ButtonHandler {
	
	public EquipmentButtonHandler() {
		super(Interface.EQUIPMENT);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 56) {
			player.getInterfaceSet().openWindow(667);
		} else if (slot == 63) {
			//Price Check
		} else if (slot == 53) {
			//Items on Death
		}
	}

}
