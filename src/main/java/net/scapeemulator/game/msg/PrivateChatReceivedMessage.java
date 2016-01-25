package net.scapeemulator.game.msg;

public final class PrivateChatReceivedMessage extends Message {

	private final long name;
	private final byte rights;
	private final byte[] packed;
	private final int messageCounter;
	
	public PrivateChatReceivedMessage(long name, byte rights, byte[] packed, int messageCounter) {
		this.name = name;
		this.rights = rights;
		this.packed = packed;
		this.messageCounter = messageCounter;
	}

	public long getName() {
		return name;
	}

	public byte getRights() {
		return rights;
	}

	public byte[] getPacked() {
		return packed;
	}

	public int getMessageCounter() {
		return messageCounter;
	}

}