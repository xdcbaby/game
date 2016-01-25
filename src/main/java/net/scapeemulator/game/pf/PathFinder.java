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

import net.scapeemulator.game.model.Mob;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.object.GameObject;

/**
 * Created by Hadyn Richard
 */
public abstract class PathFinder {

    public Path find(Mob mob, GameObject object) {
        /* Get the current position of the player */
        Position position = mob.getPosition();

        /* Get the position of the object */
        Position obj = object.getPosition();

        /* Get the object's width and length */
        int objWidth = object.getDefinition().getWidth(), objLength = object.getDefinition().getLength();
        if(object.getRotation() == 1 || object.getRotation() == 3) {
            objWidth = object.getDefinition().getLength();
            objLength = object.getDefinition().getWidth();
        }

        if(object.getType() != 10 && object.getType() != 11 && object.getType() != 22) {
            objWidth = objLength = 0;
        }

        /* Get the scene base x and y coordinates */
        int baseLocalX = position.getBaseLocalX(), baseLocalY = position.getBaseLocalY();

        /* Calculate the local x and y coordinates */
        int destLocalX = obj.getX() - baseLocalX;
        int destLocalY = obj.getY() - baseLocalY;

        Path path = find(new Position(baseLocalX, baseLocalY, position.getHeight()), 64, position.getLocalX(), position.getLocalY(), destLocalX, destLocalY, objWidth, objLength, mob.getSize());
        return path;
    }

    public Path find(Mob mob, int destX, int destY) {

        /* Get the current position of the player */
        Position position = mob.getPosition();

        /* Get the scene base x and y coordinates */
        int baseLocalX = position.getBaseLocalX(), baseLocalY = position.getBaseLocalY();

        /* Calculate the local x and y coordinates */
        int destLocalX = destX - baseLocalX, destLocalY = destY - baseLocalY;

        return find(new Position(baseLocalX, baseLocalY, position.getHeight()), 64, position.getLocalX(), position.getLocalY(), destLocalX, destLocalY, 0, 0, mob.getSize());
    }

    public Path find(Position position, int radius, int srcX, int srcY, int destX, int destY) {
        return find(position, radius, srcX, srcY, destX, destY, 0, 0, 1);
    }

    public abstract Path find(Position position, int radius, int srcX, int srcY, int destX, int destY, int objWidth, int objHeight, int size);
}
