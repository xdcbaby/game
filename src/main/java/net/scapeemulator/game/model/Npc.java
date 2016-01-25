package net.scapeemulator.game.model;

public final class Npc extends Mob {

	private int type;

	public Npc(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public boolean isRunning() {
		return false;
	}

}
