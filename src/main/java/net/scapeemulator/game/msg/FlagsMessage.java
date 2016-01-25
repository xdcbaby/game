package net.scapeemulator.game.msg;

public final class FlagsMessage extends Message {

	private final int flags;

	public FlagsMessage(int flags) {
		this.flags = flags;
	}

	public int getFlags() {
		return flags;
	}

}
