package net.scapeemulator.game.msg;

public final class InterfaceOpenMessage extends Message {

	private final int id, slot, childId, type;

	public InterfaceOpenMessage(int id, int slot, int childId, int type) {
		this.type = type;
		this.id = id;
		this.slot = slot;
		this.childId = childId;
	}

	public int getId() {
		return id;
	}

	public int getSlot() {
		return slot;
	}

	public int getChildId() {
		return childId;
	}

	public int getType() {
		return type;
	}

}
