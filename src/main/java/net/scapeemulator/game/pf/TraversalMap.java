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

import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.object.ObjectType;
import net.scapeemulator.game.model.region.Region;
import net.scapeemulator.game.model.region.RegionManager;

import static net.scapeemulator.game.model.object.ObjectOrientation.*;
import static net.scapeemulator.game.pf.Tile.*;

/**
 * Created by Hadyn Richard
 */
public final class TraversalMap {

    private final RegionManager regionManager;

    public TraversalMap(RegionManager manager) {
        this.regionManager = manager;
    }

    public void markWall(Position position, int rotation, ObjectType type, boolean impenetrable) {
        int plane = position.getHeight(), x = position.getX(), y = position.getY();
        switch(type) {
            case TYPE_0:
                if (rotation == WEST) {
                    set(plane, x, y, WALL_WEST);
                    set(plane, x - 1, y, WALL_EAST);
                }
                if (rotation == NORTH) {
                    set(plane, x, y, WALL_NORTH);
                    set(plane, x, y + 1, WALL_SOUTH);
                }
                if (rotation == EAST) {
                    set(plane, x, y, WALL_EAST);
                    set(plane, x + 1, y, WALL_WEST);
                }
                if (rotation == SOUTH) {
                    set(plane, x, y, WALL_SOUTH);
                    set(plane, x, y - 1, WALL_NORTH);
                }
                break;

            case TYPE_2:
                if (rotation == WEST) {
                    set(plane, x, y, WALL_WEST | WALL_NORTH);
                    set(plane, x - 1, y, WALL_EAST);
                    set(plane, x, y + 1, WALL_SOUTH);
                }
                if (rotation == NORTH) {
                    set(plane, x, y, WALL_EAST | WALL_NORTH);
                    set(plane, x, y + 1, WALL_SOUTH);
                    set(plane, x + 1, y, WALL_WEST);
                }
                if (rotation == EAST) {
                    set(plane, x, y, WALL_EAST | WALL_SOUTH);
                    set(plane, x + 1, y, WALL_WEST);
                    set(plane, x, y - 1, WALL_NORTH);
                }
                if (rotation == SOUTH) {
                    set(plane, x, y, WALL_WEST | WALL_SOUTH);
                    set(plane, x, y - 1, WALL_NORTH);
                    set(plane, x - 1, y, WALL_EAST);
                }
                break;

            case TYPE_1:
            case TYPE_3:
                if (rotation == WEST) {
                    set(plane, x, y, WALL_NORTH_WEST);
                    set(plane, x - 1, y + 1, WALL_SOUTH_EAST);
                }
                if (rotation == NORTH) {
                    set(plane, x, y, WALL_NORTH_EAST);
                    set(plane, x + 1, y + 1, WALL_SOUTH_WEST);
                }
                if (rotation == EAST) {
                    set(plane, x, y, WALL_SOUTH_EAST);
                    set(plane, x + 1, y - 1, WALL_NORTH_WEST);
                }
                if (rotation == SOUTH) {
                    set(plane, x, y, WALL_SOUTH_WEST);
                    set(plane, x - 1, y - 1, WALL_NORTH_EAST);
                }
                break;
        }
    }

    public void unmarkWall(int rotation, int plane, int x, int y, ObjectType type, boolean impenetrable) {
        switch(type) {
            case TYPE_0:
                if(rotation == WEST) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_WEST : WALL_WEST);
                    unset(plane, x - 1, y, impenetrable ? IMPENETRABLE_WALL_EAST : WALL_EAST);
                }
                if(rotation == NORTH) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_NORTH : WALL_NORTH);
                    unset(plane, x, y + 1, impenetrable ? IMPENETRABLE_WALL_SOUTH : WALL_SOUTH);
                }
                if(rotation == EAST) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_EAST : WALL_EAST);
                    unset(plane, x + 1, y, impenetrable ? IMPENETRABLE_WALL_WEST : WALL_WEST);
                }
                if(rotation == SOUTH) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_SOUTH : WALL_SOUTH);
                    unset(plane, x, y - 1, impenetrable ? IMPENETRABLE_WALL_NORTH : WALL_NORTH);
                }
                break;

            case TYPE_2:
                if(rotation == WEST) {
                    if(impenetrable)
                        unset(plane, x, y, IMPENETRABLE_WALL_WEST | IMPENETRABLE_WALL_NORTH);
                    else
                        unset(plane, x, y, WALL_WEST | WALL_NORTH);
                    unset(plane, x - 1, y, impenetrable ? IMPENETRABLE_WALL_EAST : WALL_EAST);
                    unset(plane, x, y + 1, impenetrable ? IMPENETRABLE_WALL_SOUTH : WALL_SOUTH);
                }
                if(rotation == NORTH) {
                    if(impenetrable)
                        unset(plane, x, y, IMPENETRABLE_WALL_EAST | IMPENETRABLE_WALL_NORTH);
                    else
                        unset(plane, x, y, WALL_EAST | WALL_NORTH);
                    unset(plane, x, y + 1, impenetrable ? IMPENETRABLE_WALL_SOUTH : WALL_SOUTH);
                    unset(plane, x + 1, y, impenetrable ? IMPENETRABLE_WALL_WEST : WALL_WEST);
                }
                if(rotation == EAST) {
                    if(impenetrable)
                        unset(plane, x, y, IMPENETRABLE_WALL_EAST | IMPENETRABLE_WALL_SOUTH);
                    else
                        unset(plane, x, y, WALL_EAST | WALL_SOUTH);
                    unset(plane, x + 1, y, impenetrable ? IMPENETRABLE_WALL_WEST : WALL_WEST);
                    unset(plane, x, y - 1, impenetrable ? IMPENETRABLE_WALL_NORTH : WALL_NORTH);
                }
                if(rotation == SOUTH) {
                    if(impenetrable)
                        unset(plane, x, y, IMPENETRABLE_WALL_WEST | IMPENETRABLE_WALL_SOUTH);
                    else
                        unset(plane, x, y, WALL_WEST | WALL_SOUTH);
                    unset(plane, x, y - 1, impenetrable ? IMPENETRABLE_WALL_WEST : WALL_WEST);
                    unset(plane, x - 1, y, impenetrable ? IMPENETRABLE_WALL_NORTH : WALL_NORTH);
                }
                break;

            case TYPE_1:
            case TYPE_3:
                if(rotation == WEST) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_NORTH_WEST : WALL_NORTH_WEST);
                    unset(plane, x - 1, y + 1, impenetrable ? IMPENETRABLE_WALL_SOUTH_EAST : WALL_SOUTH_EAST);
                }
                if(rotation == NORTH) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_NORTH_EAST : WALL_NORTH_EAST);
                    unset(plane, x + 1, y + 1, impenetrable ? IMPENETRABLE_WALL_SOUTH_WEST : WALL_SOUTH_WEST);
                }
                if(rotation == EAST) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_SOUTH_EAST : WALL_SOUTH_EAST);
                    unset(plane, x + 1, y - 1, impenetrable ? IMPENETRABLE_WALL_NORTH_WEST : WALL_NORTH_WEST);
                }
                if(rotation == SOUTH) {
                    unset(plane, x, y, impenetrable ? IMPENETRABLE_WALL_SOUTH_WEST : WALL_SOUTH_WEST);
                    unset(plane, x - 1, y - 1, impenetrable ? IMPENETRABLE_WALL_SOUTH_WEST : WALL_SOUTH_WEST);
                }
                break;
        }
    }

    public void markBlocked(Position position) {
        int plane = position.getHeight();
        int x = position.getX();
        int y = position.getY();

        /* Calculate the local coordinates */
        int localX = x & 0x3f, localY = y & 0x3f;

        Region region = regionManager.getRegion(position);
        if(region == null) {
            return;
        }

        int modifiedPlane = plane;
        if((region.getTile(1, localX, localY).flags() & BRIDGE) != 0) {
            modifiedPlane = plane - 1;
        }

        region.getTile(modifiedPlane, x & 0x3f, y & 0x3f).set(BLOCKED);
    }

    public void markOccupant(Position position, int width, int length) {
        int plane = position.getHeight(), x = position.getX(), y = position.getY();
        for(int offsetX = 0; offsetX < width; offsetX++) {
            for(int offsetY = 0; offsetY < length; offsetY++) {
                set(plane, x + offsetX, y + offsetY, OCCUPANT);
            }
        }
    }

    public void markBridge(Position position) {
        set(position.getHeight(), position.getX(), position.getY(), BRIDGE);
    }

    public boolean isTraversableNorth(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableNorth(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableNorth(int plane, int x, int y) {
        return isTraversableNorth(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed north.
     */
    public boolean isTraversableNorth(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x, y + 1, IMPENETRABLE_OCCUPANT | IMPENETRABLE_WALL_SOUTH);
        }
        return isInactive(plane, x, y + 1, WALL_SOUTH | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableSouth(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableSouth(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableSouth(int plane, int x, int y) {
        return isTraversableSouth(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableSouth(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x, y - 1, IMPENETRABLE_OCCUPANT | IMPENETRABLE_WALL_NORTH);
        }
        return isInactive(plane, x, y - 1, WALL_NORTH | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableEast(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableEast(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableEast(int plane, int x, int y) {
        return isTraversableEast(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableEast(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x + 1, y, IMPENETRABLE_OCCUPANT | IMPENETRABLE_WALL_WEST);
        }
        return isInactive(plane, x + 1, y, WALL_WEST | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableWest(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableWest(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableWest(int plane, int x, int y) {
        return isTraversableWest(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableWest(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x - 1, y, IMPENETRABLE_OCCUPANT | IMPENETRABLE_WALL_EAST);
        }
        return isInactive(plane, x - 1, y, WALL_EAST | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableNorthEast(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableNorthEast(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableNorthEast(int plane, int x, int y) {
        return isTraversableNorthEast(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableNorthEast(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x + 1, y + 1, IMPENETRABLE_WALL_WEST | IMPENETRABLE_WALL_SOUTH | IMPENETRABLE_WALL_SOUTH_WEST | OCCUPANT) && isInactive(plane, x + 1, y, IMPENETRABLE_WALL_WEST | IMPENETRABLE_OCCUPANT) && isInactive(plane, x, y + 1, IMPENETRABLE_WALL_SOUTH | IMPENETRABLE_OCCUPANT);
        }
        return isInactive(plane, x + 1, y + 1, WALL_WEST | WALL_SOUTH | WALL_SOUTH_WEST | OCCUPANT | BLOCKED) && isInactive(plane, x + 1, y, WALL_WEST | OCCUPANT | BLOCKED) && isInactive(plane, x, y + 1, WALL_SOUTH | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableNorthWest(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableNorthWest(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableNorthWest(int plane, int x, int y) {
        return isTraversableNorthWest(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableNorthWest(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x - 1, y + 1, IMPENETRABLE_WALL_EAST | IMPENETRABLE_WALL_SOUTH | IMPENETRABLE_WALL_SOUTH_EAST | OCCUPANT) && isInactive(plane, x - 1, y, IMPENETRABLE_WALL_EAST | IMPENETRABLE_OCCUPANT) && isInactive(plane, x, y + 1, IMPENETRABLE_WALL_SOUTH | IMPENETRABLE_OCCUPANT);
        }
        return isInactive(plane, x - 1, y + 1, WALL_EAST | WALL_SOUTH | WALL_SOUTH_EAST | OCCUPANT | BLOCKED) && isInactive(plane, x - 1, y, WALL_EAST | OCCUPANT | BLOCKED) && isInactive(plane, x, y + 1, WALL_SOUTH | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableSouthEast(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableSouthEast(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableSouthEast(int plane, int x, int y) {
        return isTraversableSouthEast(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableSouthEast(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x + 1, y - 1, IMPENETRABLE_WALL_WEST | IMPENETRABLE_WALL_NORTH | IMPENETRABLE_WALL_NORTH_WEST | OCCUPANT) && isInactive(plane, x + 1, y, IMPENETRABLE_WALL_WEST | IMPENETRABLE_OCCUPANT) && isInactive(plane, x, y - 1, IMPENETRABLE_WALL_NORTH | IMPENETRABLE_OCCUPANT);
        }
        return isInactive(plane, x + 1, y - 1, WALL_WEST | WALL_NORTH | WALL_NORTH_WEST | OCCUPANT | BLOCKED) && isInactive(plane, x + 1, y, WALL_WEST | OCCUPANT | BLOCKED) && isInactive(plane, x, y - 1, WALL_NORTH | OCCUPANT | BLOCKED);
    }

    public boolean isTraversableSouthWest(int plane, int x, int y, int size) {
        for(int offsetX = 0; offsetX < size; offsetX++) {
            for(int offsetY = 0; offsetY < size; offsetY++) {
                if(!isTraversableSouthWest(plane, x + offsetX, y + offsetY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTraversableSouthWest(int plane, int x, int y) {
        return isTraversableSouthWest(plane, x, y, false);
    }

    /**
     * Tests if from the specified position it can be traversed south.
     */
    public boolean isTraversableSouthWest(int plane, int x, int y, boolean impenetrable) {
        if(impenetrable) {
            return isInactive(plane, x - 1, y - 1, IMPENETRABLE_WALL_EAST | IMPENETRABLE_WALL_NORTH | IMPENETRABLE_WALL_NORTH_EAST | OCCUPANT) && isInactive(plane, x - 1, y, IMPENETRABLE_WALL_EAST | IMPENETRABLE_OCCUPANT) && isInactive(plane, x, y - 1, IMPENETRABLE_WALL_NORTH | IMPENETRABLE_OCCUPANT);
        }
        return isInactive(plane, x - 1, y - 1, WALL_EAST | WALL_NORTH | WALL_NORTH_EAST | OCCUPANT | BLOCKED) && isInactive(plane, x - 1, y, WALL_EAST | OCCUPANT | BLOCKED) && isInactive(plane, x, y - 1, WALL_NORTH | OCCUPANT | BLOCKED);
    }

    public void set(int plane, int x, int y, int flag) {
        Region region = regionManager.getRegion(x, y);
        if(region == null) {
            return;
        }

        region.getTile(plane, x & 0x3f, y & 0x3f).set(flag);
    }

    public boolean isInactive(int plane, int x, int y, int flag) {
        /* Calculate the local region coordinates */
        int localX = x & 0x3f, localY = y & 0x3f;

        Region region = regionManager.getRegion(x, y);
        if(region == null) {
            return false;
        }

        int modifiedPlane = plane;
        if(region.getTile(1, localX, localY).isActive(BRIDGE)) {
            modifiedPlane = plane + 1;
        }

        return region.getTile(modifiedPlane, localX, localY).isInactive(flag);
    }

    public void unset(int plane, int x, int y, int flag) {
        Region region = regionManager.getRegion(x, y);
        if(region == null) {
            return;
        }

        region.getTile(plane, x & 0x3f, y & 0x3f).unset(flag);
    }
}
