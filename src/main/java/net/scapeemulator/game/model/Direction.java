package net.scapeemulator.game.model;

public enum Direction {
	NONE(-1), NORTH(1), NORTH_EAST(2), EAST(4), SOUTH_EAST(7), SOUTH(6), SOUTH_WEST(5), WEST(3), NORTH_WEST(0);

	private final int intValue;

	private Direction(int intValue) {
		this.intValue = intValue;
	}

	public int toInteger() {
		return intValue;
	}

	public static Direction between(Position cur, Position next) {
		int deltaX = next.getX() - cur.getX();
		int deltaY = next.getY() - cur.getY();

		if (deltaY == 1) {
			if (deltaX == 1)
				return NORTH_EAST;
			else if (deltaX == 0)
				return NORTH;
			else if (deltaX == -1)
				return NORTH_WEST;
		} else if (deltaY == -1) {
			if (deltaX == 1)
				return SOUTH_EAST;
			else if (deltaX == 0)
				return SOUTH;
			else if (deltaX == -1)
				return SOUTH_WEST;
		} else if (deltaY == 0) {
			if (deltaX == 1)
				return EAST;
			else if (deltaX == 0)
				return NONE;
			else if (deltaX == -1)
				return WEST;
		}

		throw new IllegalArgumentException();
	}
}
