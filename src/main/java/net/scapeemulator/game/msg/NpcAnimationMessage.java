package net.scapeemulator.game.msg;

public class NpcAnimationMessage extends Message {
	private int npcId, animId, delay;
	
	public NpcAnimationMessage(int npcId, int animId, int delay) {
		this.npcId = npcId;
		this.animId = animId;
		this.delay = delay;
	}
	
	public int getNpcId() {
		return npcId;
	}
	
	public int getAnimationId() {
		return animId;
	}
	
	public int getDelay() {
		return delay;
	}
}
