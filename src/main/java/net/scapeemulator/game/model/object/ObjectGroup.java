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
public enum ObjectGroup {

    /**
     * Enumation for each group type.
     */
    WALL(0), WALL_DECORATION(1), GROUP_2(2), GROUP_3(3);

    /**
     * The array of object group ids for their type.
     */
    public static final int[] OBJECT_GROUPS = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2,
            2, 2, 2, 2, 2, 2, 2, 3 };

    /**
     * The group id that the type belongs to.
     */
    private final int id;

    ObjectGroup(int id) {
        this.id = id;
    }

    public static ObjectGroup forType(int type) {
        int id = OBJECT_GROUPS[type];
        for(ObjectGroup group : values()) {
            if(group.id == id) {
                return group;
            }
        }
        throw new RuntimeException();
    }
}
