package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public class SkillsButtonHandler extends ButtonHandler {
	private int[] interfaces = new int[28];
	
	public SkillsButtonHandler() {
		super(Interface.SKILLS);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		int index = 0;
		for (int i = 126; i < 148; i++) {
			if (slot == i) {
				player.getInterfaceSet().openWindow(interfaces[index]);
				break;
			}
			index++;
		}
	}

}
