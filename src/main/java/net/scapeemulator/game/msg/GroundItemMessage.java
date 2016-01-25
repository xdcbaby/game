package net.scapeemulator.game.msg;

import net.scapeemulator.game.model.Position;

public class GroundItemMessage extends Message {
	private int id;
	private Position positon;
	
	public GroundItemMessage(int id, Position position) {
		this.id = id;
		this.positon = position;
	}
	
	public int getId() {
		return id;
	}
	
	public Position getPositon() {
		return positon;
	}
}
