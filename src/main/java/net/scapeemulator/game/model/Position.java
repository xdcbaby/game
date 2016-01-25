package net.scapeemulator.game.model;

public final class Position {

	private final int x, y, height;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.height = 0;
	}

	public Position(int x, int y, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

    public int getBaseLocalX() {
        return getBaseLocalX(getCentralRegionX());
    }

    public int getBaseLocalY() {
        return getBaseLocalY(getCentralRegionX());
    }

    public int getBaseLocalX(int centralRegionX) {
        return (centralRegionX - 6) * 8;
    }

    public int getBaseLocalY(int centralRegionY) {
        return (centralRegionY - 6) * 8;
    }

    public int getLocalX() {
        return getLocalX(getCentralRegionX());
    }

    public int getLocalY() {
        return getLocalY(getCentralRegionX());
    }

	public int getLocalX(int centralRegionX) {
		return x - ((centralRegionX - 6) * 8);
	}

	public int getLocalY(int centralRegionY) {
		return y - ((centralRegionY - 6) * 8);
	}

	public int getCentralRegionX() {
		return x / 8;
	}

	public int getCentralRegionY() {
		return y / 8;
	}

	public int getHeight() {
		return height;
	}

	public boolean isWithinDistance(Position position) {
		return isWithinDistance(position, 15);
	}

    public boolean isWithinDistance(Position position, int distance) {
        int deltaX = position.getX() - x;
        int deltaY = position.getY() - y;
        return deltaX >= -(distance + 1) && deltaX <= distance && deltaY >= -(distance + 1) && deltaY <= distance;
    }

    public int distanceTo(Position other) {
        int deltaX = other.getX() - x;
        int deltaY = other.getY() - y;

        if(deltaX == deltaY) {
            deltaX *= 2;
            deltaY *= 2;
        }
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public Position transform(int diffX, int diffY, int diffZ) {
        return new Position(x + diffX, y + diffY, height + diffZ);
    }

	public int toPackedInt() {
		return (height << 28) | (x << 14) | y;
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Position[");

        builder.append("X=");
        builder.append(x);
        builder.append(", Y=");
        builder.append(y);
        builder.append(", Z=");
        builder.append(height);
        builder.append("]");

        return builder.toString();
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (height != other.height)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
