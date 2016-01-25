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
import net.scapeemulator.game.task.ScheduledTask;

/**
 * An action is a specialised {@link ScheduledTask} which is specific to a mob.
 * <p>
 * <strong>ALL</strong> actions <strong>MUST</strong> implement the {@link #equals(Object)} method. This is to check if
 * two actions are identical: if they are, then the new action does not replace the old one (so spam/accidental clicking
 * won't cancel your action, and start another from scratch).
 *
 * @author Graham
 */
public abstract class Action<T extends Mob> extends ScheduledTask {

    /**
     * The mob performing the action.
     */
    protected final T mob;

    /**
     * A flag indicating if this action is stopping.
     */
    private boolean stopping = false;

    /**
     * Creates a new action.
     *
     * @param delay The delay in pulses.
     * @param immediate A flag indicating if the action should happen immediately.
     * @param mob The mob performing the action.
     */
    public Action(int delay, boolean immediate, T mob) {
        super(delay, immediate);
        this.mob = mob;
    }

    /**
     * Gets the mob which performed the action.
     *
     * @return The mob.
     */
    public T getMob() {
        return mob;
    }

    @Override
    public void stop() {
        super.stop();
        if (!stopping) {
            stopping = true;
            mob.stopAction();
        }
    }

}
