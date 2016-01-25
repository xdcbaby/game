package net.scapeemulator.game.model.skills.herblore;

import net.scapeemulator.game.model.item.Item;

public enum Potions {
	ATTACK_POTION(new Item(121), new Item(91), new Item(221), 1, 25);
	
	private final Item unfinished, potion, requirement;
	private final int levelReq, xp;
	
	Potions(Item potion, Item unfinished, Item requirement, int levelReq, int xp) {
		this.unfinished = unfinished;
		this.potion = potion;
		this.requirement = requirement;
		this.levelReq = levelReq;
		this.xp = xp;
	}
	
	public Item getUnfinished() {
		return unfinished;
	}
	
	public Item getPotion() {
		return potion;
	}
	
	public Item getRequirement() {
		return requirement;
	}
	
	public int getLevelReq() {
		return levelReq;
	}
	
	public int getXp() {
		return xp;
	}
}
