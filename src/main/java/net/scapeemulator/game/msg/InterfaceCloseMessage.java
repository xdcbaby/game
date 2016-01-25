package net.scapeemulator.game.msg;

public final class InterfaceCloseMessage extends Message {

	private final int id, slot;

	public InterfaceCloseMessage(int id, int slot) {
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
