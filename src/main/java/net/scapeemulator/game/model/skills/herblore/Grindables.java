package net.scapeemulator.game.model.skills.herblore;

public enum Grindables {
	UNICORN(237, 235),
	CHOCOLATE(1973, 1975),
	BIRD(5075, 6693);
	
	private int item, output;
	
	Grindables(int input, int output) {
		this.item = input;
		this.output = output;
	}
	
	public int getItem() {
		return item;
	}
	
	public int getOutput() {
		return output;
	}
	
}
