package net.scapeemulator.game.msg;

public final class WalkMessage extends Message {

	public static final class Step {

		private final int x, y;

		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

	private final Step destination;
	private final boolean running;
    private int flagId;

	public WalkMessage(Step dest, boolean running, int flagId) {
		this.destination = dest;
		this.running = running;
        this.flagId = flagId;
	}

	public Step getDestination() {
		return destination;
	}

	public boolean isRunning() {
		return running;
	}

    public int getFlagId() {
        return flagId;
    }
}
