package net.scapeemulator.game.msg;

public final class ButtonMessage extends Message {

	private final int id, slot;

	public ButtonMessage(int id, int slot) {
		this.id = id;
		this.slot = slot;
	}

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot;
	}

}
