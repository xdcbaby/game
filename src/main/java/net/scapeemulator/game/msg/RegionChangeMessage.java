package net.scapeemulator.game.msg;

import net.scapeemulator.game.model.Position;

public final class RegionChangeMessage extends Message {

	private final Position position;

	public RegionChangeMessage(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

}
