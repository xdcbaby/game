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
package net.scapeemulator.game.model.object;

import net.scapeemulator.cache.def.ObjectDefinition;
import net.scapeemulator.game.model.Entity;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.definition.ObjectDefinitions;

public final class GameObject extends Entity {

    private int id, type, rotation;

    public GameObject(int id, int type, Position position, int rotation) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.rotation = rotation;
    }

    public Position getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getType() {
        return type;
    }

    public int getRotation() {
        return rotation;
    }

    public ObjectDefinition getDefinition() {
        return ObjectDefinitions.forId(id);
    }

    @Override
    public String toString() {
        ObjectDefinition def = getDefinition();

        StringBuilder builder = new StringBuilder("GameObject[");
        builder.append("id=");
        builder.append(id);
        builder.append(", type=");
        builder.append(type);
        builder.append(", width=");
        builder.append(def.getWidth());
        builder.append(", length=");
        builder.append(def.getLength());
        builder.append("]");
        return builder.toString();
    }
}
