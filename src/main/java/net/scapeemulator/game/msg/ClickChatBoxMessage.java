package net.scapeemulator.game.msg;

public class ClickChatBoxMessage extends Message {
	private int type, localPlayerId;
	
	public ClickChatBoxMessage(int type, int localPlayerId) {
		this.type = type;
		this.localPlayerId = localPlayerId;
	}
	
	public int getType() {
		return type;
	}

	public int getLocalPlayerId() {
		return localPlayerId;
	}
}
