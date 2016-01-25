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
import net.scapeemulator.game.model.object.GameObject;
import net.scapeemulator.game.pf.Tile;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hadyn Richard
 */
public class Region {

    public static final int REGION_SIZE = 64;

    /**
     * The maximum plane.
     */
    public static final int MAXIMUM_PLANE = 4;

    /**
     * The flags within the region.
     */
    private Tile[][] tiles;

    /**
     * The objects within the region.
     */
    private Map<Position, GameObject> objects;

    /**
     * Constructs a new {@link net.scapeemulator.game.model.region.Region};
     */
    public Region() {
        this.objects = new HashMap<>();

        this.tiles = new Tile[MAXIMUM_PLANE][REGION_SIZE * REGION_SIZE];
        for(int i = 0; i < MAXIMUM_PLANE; i++) {
            for(int j = 0; j < REGION_SIZE * REGION_SIZE; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public Tile getTile(int plane, int x, int y) {
        return tiles[plane][x + y * REGION_SIZE];
    }

    public void addObject(final GameObject object) {
        objects.put(object.getPosition(), object);
    }

    public GameObject getObject(final Position position) {
        return objects.get(position);
    }

    public Collection<GameObject> getObjects() {
        return Collections.unmodifiableCollection(objects.values());
    }
}
