package net.scapeemulator.game.msg;

public final class ScriptIntMessage extends Message {

	private final int id, value;

	public ScriptIntMessage(int id, int value) {
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public int getValue() {
		return value;
	}

}
