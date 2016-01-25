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

/**
 * Created by Hadyn Richard
 */
public final class Tile {

    /**
     * The flags for each of the traversals.
     */
    public static final int /* Each of the flags for walls */
            WALL_NORTH       = 0x1,  WALL_SOUTH      = 0x2,
            WALL_EAST        = 0x4,  WALL_WEST       = 0x8,
            WALL_NORTH_EAST  = 0x10, WALL_NORTH_WEST = 0x20,
            WALL_SOUTH_EAST  = 0x40, WALL_SOUTH_WEST = 0x80,

    /* Each of the occupant flags for both impenetrable and normal */
    OCCUPANT = 0x8000, IMPENETRABLE_OCCUPANT = 0x10000,

    /* Each of the impenetrable wall flags, meaning projectiles cannot fly over these */
    IMPENETRABLE_WALL_NORTH       = 0x100,  IMPENETRABLE_WALL_SOUTH      = 0x200,
            IMPENETRABLE_WALL_EAST        = 0x400,  IMPENETRABLE_WALL_WEST       = 0x800,
            IMPENETRABLE_WALL_NORTH_EAST  = 0x800,  IMPENETRABLE_WALL_NORTH_WEST = 0x1000,
            IMPENETRABLE_WALL_SOUTH_EAST  = 0x2000, IMPENETRABLE_WALL_SOUTH_WEST = 0x4000,

    /* The other flags */
    BLOCKED = 0x20000, BRIDGE = 0x40000, NONE = 0x0;

    /**
     * The flags for the tile.
     */
    private int flags;

    /**
     * Constructs a new {@link net.scapeemulator.game.pf.Tile};
     */
    public Tile() {
        this(NONE);
    }

    /**
     * Constructs a new {@link net.scapeemulator.game.pf.Tile};
     */
    public Tile(int flags) {
        this.flags = flags;
    }

    /**
     * Sets a flag for the tile.
     */
    public void set(int flag) {
        flags |= flag;
    }

    /**
     * Unsets a flag for the tile.
     */
    public void unset(int flag) {
        flags &= ~flag;
    }

    /**
     * Gets if a flag is active.
     * @param flag The flag to check for if it is active.
     * @return If the flag is active.
     */
    public boolean isActive(int flag) {
        return (flags & flag) != 0;
    }

    /**
     * Gets if a flag is inactive.
     * @param flag The flag to check for if it is inactive.
     * @return If the flag is inactive.
     */
    public boolean isInactive(int flag) {
        return (flags & flag) == 0;
    }

    /**
     * Gets the flags for the tile.
     */
    public int flags() {
        return flags;
    }
}
