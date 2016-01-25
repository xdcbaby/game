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

public final class ObjectOrientation {

    public static final int WEST       = 0;
    public static final int NORTH      = 1;
    public static final int EAST       = 2;
    public static final int SOUTH      = 3;

    public static final int ROTATE_CW  = 1;
    public static final int ROTATE_CCW = -1;

    public static final int HALF_TURN  = 2;

    private ObjectOrientation() {}
}
