package net.scapeemulator.game.model.skills.herblore;

import net.scapeemulator.game.model.item.Item;

public enum UnfinishedPotion {
	GUAM_POTION(new Item(91), new Item(249), 1, 1);
	
	private final Item unfinished, herb;
	private final int levelReq, xp;
	
	UnfinishedPotion(Item unfinished, Item herb, int levelReq, int xp) {
		this.unfinished = unfinished;
		this.herb = herb;
		this.levelReq = levelReq;
		this.xp = xp;
	}
	
	public Item getUnfinished() {
		return unfinished;
	}
	
	public Item getHerb() {
		return herb;
	}
	
	public int getLevelReq() {
		return levelReq;
	}
	
	public int getXp() {
		return xp;
	}
}
