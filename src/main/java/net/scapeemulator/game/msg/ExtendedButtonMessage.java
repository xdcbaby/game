package net.scapeemulator.game.msg;

public final class ExtendedButtonMessage extends Message {

	private final int id, slot, parameter;

	public ExtendedButtonMessage(int id, int slot, int parameter) {
		this.id = id;
		this.slot = slot;
		this.parameter = parameter;
	}

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot;
	}

	public int getParameter() {
		return parameter;
	}

}
