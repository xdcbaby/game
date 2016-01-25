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

/**
 * Created by Hadyn Richard
 */
public enum ObjectType {

    /* Each of the types that have been identified */
    DIAGONAL_WALL(9), PROP(10), DIAGONAL_PROP(11),

    /* Each of the yet to be identified types, some arent really important */
    TYPE_0(0), TYPE_1(1), TYPE_2(2), TYPE_3(3), TYPE_4(4), TYPE_5(5), TYPE_6(6), TYPE_7(7), TYPE_8(8),
    TYPE_12(12), TYPE_13(13), TYPE_14(14), TYPE_15(15), TYPE_16(16), TYPE_17(17), TYPE_18(18), TYPE_19(19),
    TYPE_20(20), TYPE_21(21), TYPE_22(22);

    private final int id;

    ObjectType(int id) {
        this.id = id;
    }

    /**
     * Gets if the object type is apart of the wall group.
     */
    public boolean isWall() {
        return getObjectGroup() == ObjectGroup.WALL;
    }

    /**
     * Gets the type id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the object group this type belongs to.
     */
    public ObjectGroup getObjectGroup() {
        return ObjectGroup.forType(id);
    }

    /**
     * Gets the object type for the specified id.
     */
    public static ObjectType forId(int id) {
        for(ObjectType type : values()) {
            if(type.id == id) {
                return type;
            }
        }
        throw new RuntimeException("unknown object type for id: " + id);
    }
}
