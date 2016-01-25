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

import net.scapeemulator.cache.def.ObjectDefinition;
import net.scapeemulator.game.cache.MapListener;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.definition.ObjectDefinitions;
import net.scapeemulator.game.model.object.ObjectType;

public class TraversalMapListener implements MapListener {
    private static final int FLAG_CLIP = 0x1;
    private static final int FLAG_BRIDGE = 0x2;

    private final TraversalMap map;

    public TraversalMapListener(TraversalMap map) {
        this.map = map;
    }

    @Override
    public void tileDecoded(int flags, Position position) {
        if((flags & FLAG_BRIDGE) != 0) {
            map.markBridge(position);
        }

        if((flags & FLAG_CLIP) != 0) {
            map.markBlocked(position);
        }
    }

    @Override
    public void objectDecoded(int id, int rotation, ObjectType type, Position position) {
        ObjectDefinition def = ObjectDefinitions.forId(id);
        if(!def.isSolid())
            return;

        if(type.isWall()) { // type 0-3
            map.markWall(position, rotation, type, def.isImpenetrable());
        }

        if(type.getId() >= 9 && type.getId() <= 12) {
            /* Flip the length and width if the object is rotated */
            int width = def.getWidth();
            int length = def.getLength();
            if(rotation == 1 || rotation == 3) {
                width = def.getLength();
                length = def.getWidth();
            }
            map.markOccupant(position, width, length);
        }
    }
}
