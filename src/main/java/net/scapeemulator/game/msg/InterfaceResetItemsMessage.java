package net.scapeemulator.game.msg;

public final class InterfaceResetItemsMessage extends Message {

	private final int id, slot;

	public InterfaceResetItemsMessage(int id, int slot) {
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
