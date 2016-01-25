package net.scapeemulator.game.model.skills.herblore;

import net.scapeemulator.game.model.item.Item;

public enum Leaf {
	GUAM(new Item(199), new Item(249), 1, 3),
	MARRENTILL(new Item(201), new Item(251), 5, 4),
	TARROMIN(new Item(203), new Item(253), 11, 5),
	HARRALANDER(new Item(205), new Item(255), 20, 6),
	RANARR(new Item(207), new Item(257), 25, 8),
	TOADFLAX(new Item(3049), new Item(2998), 30, 8),
	SPIRITWEED(new Item(12174), new Item(12172), 35, 8),
	IRIT(new Item(209), new Item(259), 40, 9),
	WERGALI(new Item(14836), new Item(14854), 30, 8),
	AVANTOE(new Item(211), new Item(261), 48, 10),
	KWUARM(new Item(213), new Item(263), 54, 11),
	SNAPDRAGON(new Item(3051), new Item(3000), 59, 12),
	CADANTINE(new Item(215), new Item(265), 65, 13),
	LANTADYME(new Item(2485), new Item(2481), 67, 13),
	DWARFWEED(new Item(217), new Item(267), 70, 14),
	TORSTOL(new Item(219), new Item(269), 75, 15);
	
	private final Item herb, clean;
	private final int levelReq, xp;
	
	Leaf(Item herb, Item clean, int levelReq, int xp) {
		this.herb = herb;
		this.clean = clean;
		this.levelReq = levelReq;
		this.xp = xp;
	}
	
	public Item getHerb() {
		return herb;
	}
	
	public Item getClean() {
		return clean;
	}
	
	public int getLevelReq() {
		return levelReq;
	}
	
	public int getXp() {
		return xp;
	}
}
