package net.scapeemulator.game.msg;

public class ObjectExamineMessage extends Message {
	private int id;
	
	public ObjectExamineMessage(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

}
