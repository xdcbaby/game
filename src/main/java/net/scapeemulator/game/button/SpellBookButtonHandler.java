package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public class SpellBookButtonHandler extends ButtonHandler {

	public SpellBookButtonHandler() {
		super(Interface.MAGIC);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		System.out.println("Slot: " + slot + "\nParam: " + parameter);
	
		if (slot == 0) {
			//Home teleport
			
		}
	}

}
