package net.scapeemulator.game.msg;

public class ItemExamineMessage extends Message {
	private int id;
	
	public ItemExamineMessage(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
