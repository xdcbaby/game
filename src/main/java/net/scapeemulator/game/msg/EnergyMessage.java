package net.scapeemulator.game.msg;

public final class EnergyMessage extends Message {

	private final int energy;

	public EnergyMessage(int energy) {
		this.energy = energy;
	}

	public int getEnergy() {
		return energy;
	}

}
