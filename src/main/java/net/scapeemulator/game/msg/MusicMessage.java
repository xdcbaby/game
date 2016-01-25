package net.scapeemulator.game.msg;

public class MusicMessage extends Message {
	private int id;
	
	public MusicMessage(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
