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

import net.scapeemulator.cache.util.ByteBufferUtils;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.object.GameObject;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class Landscape {

    public static Landscape decode(int x, int y, ByteBuffer buffer) {
        Landscape landscape = new Landscape(x, y);

        int id = -1;
        int deltaId;

        while ((deltaId = ByteBufferUtils.getSmart(buffer)) != 0) {
            id += deltaId;

            int pos = 0;
            int deltaPos;

            while ((deltaPos = ByteBufferUtils.getSmart(buffer)) != 0) {
                pos += deltaPos - 1;

                int localX = (pos >> 6) & 0x3F;
                int localY = pos & 0x3F;
                int height = (pos >> 12) & 0x3;

                int temp = buffer.get() & 0xFF;
                int type = (temp >> 2) & 0x3F;
                int rotation = temp & 0x3;

                Position position = new Position(x * 64 + localX, y * 64 + localY, height);
                landscape.objects.add(new GameObject(id, type, position, rotation));
            }
        }

        return landscape;
    }

    private final int x, y;
    private final List<GameObject> objects = new ArrayList<>();

    private Landscape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Landscape) {
            Landscape other = (Landscape) o;
            return other.x == x && other.y == y;
        }
        return false;
    }
}
