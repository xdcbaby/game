package net.scapeemulator.game.msg;

public final class ObjectOptionTwoMessage extends Message {

	private final int x, y, id;

	public ObjectOptionTwoMessage(int x, int y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

}
