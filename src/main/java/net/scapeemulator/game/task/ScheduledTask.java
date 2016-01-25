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

/**
 * A game-related task that is scheduled to run in the future.
 *
 * @author Graham
 */
public abstract class ScheduledTask {

    /**
     * The delay between executions of the task, in pulses.
     */
    private int delay;

    /**
     * The number of pulses remaining until the task is next executed.
     */
    private int pulses;

    /**
     * A flag indicating if the task is running.
     */
    private boolean running = true;

    /**
     * Creates a new scheduled task.
     *
     * @param delay The delay between executions of the task, in pulses.
     * @param immediate A flag indicating if this task should (for the first execution) be ran immediately, or after the
     *            {@code delay}.
     * @throws IllegalArgumentException If the delay is less than or equal to zero.
     */
    public ScheduledTask(int delay, boolean immediate) {
        setDelay(delay);
        pulses = immediate ? 0 : delay;
    }

    /**
     * Executes this task.
     */
    public abstract void execute();

    /**
     * Checks if this task is running.
     *
     * @return {@code true} if so, {@code false} if not.
     */
    public final boolean isRunning() {
        return running;
    }

    /**
     * Pulses this task: updates the delay and calls {@link #execute()} if necessary.
     */
    final void pulse() {
        if (running && pulses-- == 0) {
            execute();
            pulses = delay;
        }
    }

    /**
     * Sets the delay.
     *
     * @param delay The delay.
     * @throws IllegalArgumentException If the delay is less than or equal to zero.
     */
    public void setDelay(int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay cannot be less than 0.");
        }
        this.delay = delay;
    }

    /**
     * Stops the task.
     */
    public void stop() {
        running = false;
    }

}
