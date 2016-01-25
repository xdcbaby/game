package net.scapeemulator.game.msg;

public final class InterfaceRootMessage extends Message {

	private final int id;

	public InterfaceRootMessage(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
