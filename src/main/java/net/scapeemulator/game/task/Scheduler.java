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
package net.scapeemulator.game.task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * A class which manages {@link ScheduledTask}s.
 *
 * @author Graham
 */
public final class Scheduler {

    /**
     * A queue of new tasks that should be added.
     */
    private Queue<ScheduledTask> newTasks = new ArrayDeque<ScheduledTask>();

    /**
     * A list of currently active tasks.
     */
    private List<ScheduledTask> tasks = new ArrayList<ScheduledTask>();

    /**
     * Called every pulse: executes tasks that are still pending, adds new tasks and stops old tasks.
     */
    public void pulse() {
        ScheduledTask task;
        while ((task = newTasks.poll()) != null) {
            tasks.add(task);
        }

        for (Iterator<ScheduledTask> it = tasks.iterator(); it.hasNext();) {
            task = it.next();
            task.pulse();
            if (!task.isRunning()) {
                it.remove();
            }
        }
    }

    /**
     * Schedules a new task.
     *
     * @param task The task to schedule.
     */
    public boolean schedule(ScheduledTask task) {
        return newTasks.add(task);
    }

}
