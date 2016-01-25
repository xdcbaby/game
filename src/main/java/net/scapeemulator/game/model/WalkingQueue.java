package net.scapeemulator.game.model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public final class WalkingQueue {
    private final static int AMOUNT_POINTS = 100;

	private final Mob mob;
	private final Deque<Position> points = new ArrayDeque<>();
    private final Deque<Position> recentPoints = new LinkedList<>();
	private boolean runningQueue;
	private boolean minimapFlagReset = false;

    private Position destination;

	public WalkingQueue(Mob mob) {
		this.mob = mob;
	}

	public void reset() {
		points.clear();
		runningQueue = false;
		minimapFlagReset = true;
	}

    public void addPoint(Position position) {
        points.add(position);
    }

	public void addFirstStep(Position position) {
		points.clear();
		addStepImpl(position, mob.getPosition());
	}

	public void addStep(Position position) {
		addStepImpl(position, points.peekLast());
	}

	private void addStepImpl(Position position, Position last) {
		int deltaX = position.getX() - last.getX();
		int deltaY = position.getY() - last.getY();

		int max = Math.max(Math.abs(deltaX), Math.abs(deltaY));

		for (int i = 0; i < max; i++) {
			if (deltaX < 0)
				deltaX++;
			else if (deltaX > 0)
				deltaX--;

			if (deltaY < 0)
				deltaY++;
			else if (deltaY > 0)
				deltaY--;

			points.add(new Position(position.getX() - deltaX, position.getY() - deltaY, position.getHeight()));
		}
	}

    public void tick() {
        Position position = mob.getPosition();

        Direction firstDirection = Direction.NONE;
        Direction secondDirection = Direction.NONE;

        Position next = points.poll();
        if (next != null) {
            firstDirection = Direction.between(position, next);
            position = next;

            if (runningQueue || mob.isRunning()) {
                next = points.poll();
                if (next != null) {
                    secondDirection = Direction.between(position, next);
                    position = next;
                }
            }
        }

        mob.setDirections(firstDirection, secondDirection);
        mob.setPosition(position);
    }

    public void addRecentPoint(Position position) {
        if(recentPoints.size() >= AMOUNT_POINTS) {
            recentPoints.poll();
        }
        recentPoints.addLast(position);
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public Deque<Position> getRecentPoints() {
        return recentPoints;
    }

	public boolean isRunningQueue() {
		return runningQueue;
	}

	public void setRunningQueue(boolean runningQueue) {
		this.runningQueue = runningQueue;
	}

    public void setDestination(Position destination) {
        this.destination = destination;
    }

}
