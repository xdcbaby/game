package net.scapeemulator.game.msg;

public final class SequenceNumberMessage extends Message {

	private final int sequenceNumber;

	public SequenceNumberMessage(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

}
