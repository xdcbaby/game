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
package net.scapeemulator.game.cache;

import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.object.ObjectType;

public interface MapListener {
    /**
     * Called after every tile is decoded.
     *
     * @param flags The tile's bitmask.
     * @param position The position of the tile.
     */
    public void tileDecoded(int flags, Position position);

    /**
     * Called after every object is decoded.
     *
     * @param id The object's id.
     * @param rotation The rotation of the object.
     * @param type The type of the object.
     * @param position The position of the object.
     */
    public void objectDecoded(int id, int rotation, ObjectType type, Position position);
}
