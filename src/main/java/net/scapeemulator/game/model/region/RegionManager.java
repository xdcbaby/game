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
package net.scapeemulator.game.model.region;

import net.scapeemulator.game.model.Position;

public class RegionManager {
    /**
     * The size of one side of the region array.
     */
    public static final int SIZE = 256;

    /**
     * The regions for the traversal data.
     */
    private final Region[] regions = new Region[SIZE * SIZE];

    public Region getRegion(Position position) {
        return getRegion(position.getX(), position.getY());
    }

    public Region getRegion(int x, int y) {
        return regions[(x >> 6) + (y >> 6) * SIZE];
    }

    /**
     * Initializes the region at the specified coordinates.
     */
    public void initializeRegion(Position position) {
        /* Calculate the coordinates */
        int regionX = position.getX() >> 6, regionY = position.getY() >> 6;

        regions[regionX + regionY * SIZE] = new Region();
    }

    /**
     * Gets if the set contains a region for the specified coordinates.
     */
    public boolean isRegionInitialized(Position position) {
        /* Calculate the coordinates */
        int regionX = position.getX() >> 6, regionY = position.getY() >> 6;

        /* Get if the region is not null */
        return regions[regionX + regionY * SIZE] != null;
    }
}
