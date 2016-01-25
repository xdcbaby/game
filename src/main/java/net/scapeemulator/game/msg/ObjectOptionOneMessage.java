package net.scapeemulator.game.msg;

public final class ObjectOptionOneMessage extends Message {

	private final int x, y, id;
    private final boolean forceRun;

	public ObjectOptionOneMessage(int x, int y, int id, boolean forceRun) {
		this.x = x;
		this.y = y;
		this.id = id;
        this.forceRun = forceRun;
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

    public boolean isForceRun() {
        return forceRun;
    }

}
