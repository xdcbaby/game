/**
 * scape-emulator-final
 * Copyright (c) 2014 Justin Conner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in  the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/license/>.
 */
package net.scapeemulator.game.action;

import net.scapeemulator.game.model.Mob;
import net.scapeemulator.game.model.Position;

/**
 * An @{link Action} which fires when a distance requirement is met.
 *
 * @author Blake
 * @author Graham
 */
public abstract class DistancedAction<T extends Mob> extends Action<T> {

    /**
     * The delay once the threshold is reached.
     */
    private final int delay;

    /**
     * The minimum distance before the action fires.
     */
    private final int distance;

    /**
     * A flag indicating if this action fires immediately after the threshold is reached.
     */
    private final boolean immediate;

    /**
     * The target to distance check with.
     */
    private final Position target;

    /**
     * A flag indicating if the distance has been reached yet.
     */
    private boolean reached = false;

    /**
     * Creates a new DistancedAction.
     *
     * @param delay The delay between executions once the distance threshold is reached.
     * @param immediate Whether or not this action fires immediately after the distance threshold is reached.
     * @param mob The mob.
     * @param target The target position.
     * @param distance The distance.
     */
    public DistancedAction(int delay, boolean immediate, T mob, Position target, int distance) {
        super(0, true, mob);
        this.target = target;
        this.distance = distance;
        this.delay = delay;
        this.immediate = immediate;
    }

    @Override
    public void execute() {
        if (reached) {
            // some actions (e.g. agility) will cause the player to move away again
            // so we don't check once the player got close enough once
            executeAction();
        } else if (mob.getPosition().distanceTo(target) <= distance) {
            reached = true;
            setDelay(delay);
            if (immediate) {
                executeAction();
            }
        }
    }

    /**
     * Executes the actual action. Called when the distance requirement is met.
     */
    public abstract void executeAction();

}
