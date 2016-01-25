package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Player;

public abstract class ButtonHandler {

	private final int id;

	public ButtonHandler(int id) {
		this.id = id;
	}

	public final int getId() {
		return id;
	}

	public abstract void handle(Player player, int slot, int parameter);

}
