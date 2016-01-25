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
package net.scapeemulator.game.pf;

import net.scapeemulator.game.model.Position;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Represents a path found by a <code>PathFinder</code> between two points.
 * @author Graham Edgecombe
 *
 */
public final class Path {

    /**
     * The queue of positions.
     */
    private Deque<Position> tiles = new LinkedList<>();

    /**
     * Creates an empty path.
     */
    public Path() {}

    /**
     * Adds a Position onto the queue.
     * @param p The Position to add.
     */
    public void addFirst(Position p) {
        tiles.addFirst(p);
    }

    /**
     * Adds a position onto the queue.
     * @param p The position to add.
     */
    public void addLast(Position p) {
        tiles.addLast(p);
    }

    /**
     * Peeks at the next tile in the path.
     * @return The next tile.
     */
    public Position peek() {
        return tiles.peek();
    }

    /**
     * Polls a position from the path.
     * @return The polled position.
     */
    public Position poll() {
        return tiles.poll();
    }

    /**
     * Gets if the path is empty.
     * @return If the tile deque is empty.
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    /**
     * Gets the deque backing this path.
     * @return The deque backing this path.
     */
    public Deque<Position> getPoints() {
        return tiles;
    }
}

