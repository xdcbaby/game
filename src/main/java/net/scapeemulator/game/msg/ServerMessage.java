package net.scapeemulator.game.msg;

public final class ServerMessage extends Message {

	private final String text;

	public ServerMessage(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
