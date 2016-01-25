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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.istack.internal.NotNull;

import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.World;

/**
 * @author Graham Edgecombe
 */
public final class AStarPathFinder extends PathFinder {

    /**
     * The cost of moving in a straight line.
     */
    private static final int COST_STRAIGHT = 10;

    /**
     * The radius to search if we can't find a path to our tile.
     */
    private static final int UNREACHABLE_SEARCH_DISTANCE = 10;

    /**
     * Represents a node used by the A* algorithm.
     *
     * @author Graham Edgecombe
     */
    private static class Node implements Comparable<Node> {

        /**
         * The parent node.
         */
        private Node parent;

        /**
         * The cost.
         */
        private int cost;

        /**
         * The heuristic.
         */
        private int heuristic;

        /**
         * The depth.
         */
        private int depth;

        /**
         * The x coordinate.
         */
        private final int x;

        /**
         * The y coordinate.
         */
        private final int y;

        /**
         * Creates a node.
         *
         * @param x
         *         The x coordinate.
         * @param y
         *         The y coordinate.
         */
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Sets the parent.
         *
         * @param parent
         *         The parent.
         */
        public void setParent(Node parent) {
            this.parent = parent;
        }

        /**
         * Gets the parent node.
         *
         * @return The parent node.
         */
        public Node getParent() {
            return parent;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        /**
         * Gets the X coordinate.
         *
         * @return The X coordinate.
         */
        public int getX() {
            return x;
        }

        /**
         * Gets the Y coordinate.
         *
         * @return The Y coordinate.
         */
        public int getY() {
            return y;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + cost;
            result = prime * result + depth;
            result = prime * result + heuristic;
            result = prime * result + ((parent == null) ? 0 : parent.hashCode());
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if(cost != other.cost)
                return false;
            if(depth != other.depth)
                return false;
            if(heuristic != other.heuristic)
                return false;
            if(parent == null) {
                if(other.parent != null)
                    return false;
            } else if(!parent.equals(other.parent))
                return false;
            if(x != other.x)
                return false;
            if(y != other.y)
                return false;
            return true;
        }

        @Override
        public int compareTo(@NotNull Node other) {
            return cost - other.cost;
        }

    }

    private Node current;
    private Node[][] nodes;
    private Set<Node> closed = new HashSet<>();
    private Set<Node> open = new HashSet<>();

    @Override
    public Path find(Position position, int radius, int srcX, int srcY, int dstX, int dstY, int objWidth, int objLength, int size) {
    	/**
         * Okay, the radius @2 is quick but it does not cover the whole map.
         * The larger the radius is times by, the slower this PF becomes.
         */
    	int width = radius * 2;
    	int length = width * 2;
    	
        /* Move our coordinates to the center so we don't run into bounds issues */
        srcX += radius; srcY += radius;
        dstX += radius; dstY += radius;

        if(dstX < 0 || dstY < 0 || dstX >= width || dstY >= length) {
            return null; // out of range
        }
        
        TraversalMap map = World.getWorld().getTraversalMap();

        open.clear();
        closed.clear();
        
        nodes = new Node[width][length];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < length; y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
        
        open.add(nodes[srcX][srcY]);
        while(open.size() > 0) {
            current = getLowestCost();
            if(current == nodes[dstX][dstY]) {
                break;
            }
            open.remove(current);
            closed.add(current);

            int x = current.getX(), y = current.getY();

            // south
            if(y > 0 && map.isTraversableSouth(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x][y - 1];
                examineNode(n);
            }
            // west
            if(x > 0 && map.isTraversableWest(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x - 1][y];
                examineNode(n);
            }
            // north
            if(y < length - 1 && map.isTraversableNorth(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x][y + 1];
                examineNode(n);
            }
            // east
            if(x < width - 1 && map.isTraversableEast(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x + 1][y];
                examineNode(n);
            }
            // south west
            if(x > 0 && y > 0 && map.isTraversableSouthWest(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x - 1][y - 1];
                examineNode(n);
            }
            // north west
            if(x > 0 && y < length - 1 && map.isTraversableNorthWest(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x - 1][y + 1];
                examineNode(n);
            }

            // south east
            if(x < width - 1 && y > 0 && map.isTraversableSouthEast(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x + 1][y - 1];
                examineNode(n);
            }
            // north east
            if(x < width - 1 && y < length - 1 && map.isTraversableNorthEast(position.getHeight(), position.getX() + x - radius, position.getY() + y - radius, size)) {
                Node n = nodes[x + 1][y + 1];
                examineNode(n);
            }
        }
        
        if(nodes[dstX][dstY].getParent() == null) {
            int newX = dstX;
            int newY = dstY;

            int minDistance = 999;
            int minCost = 999_999;
            for(int x = dstX - UNREACHABLE_SEARCH_DISTANCE; x <= dstX + UNREACHABLE_SEARCH_DISTANCE; x++) {
                for(int y = dstY - UNREACHABLE_SEARCH_DISTANCE; y <= dstY + UNREACHABLE_SEARCH_DISTANCE; y++) {
                	if((x >= 0 && x < width) && (y >= 0 && y < length) && nodes[x][y].parent != null) {
                        int deltaX = 0;
                        int deltaY = 0;
                        if(y < dstY) {
                            deltaY = dstY - y;
                        } else if(y > (dstY + objLength - 1)) {
                            deltaY = y + 1 - (dstY + objLength);
                        }
                        if(x >= dstX) {
                            if(x > (dstX + objWidth - 1)) {
                                deltaX = 1 + (x - dstX - objWidth);
                            }
                        } else {
                            deltaX = dstX - x;
                        }
                        int dist = (int)Math.sqrt(deltaX * deltaX + deltaY + deltaY);
                        int cost = nodes[x][y].cost;
                        if(dist < minDistance || (dist == minDistance && cost < minCost)) {
                            minDistance = dist;
                            minCost = cost;
                            newX = x;
                            newY = y;
                        }
                    }
                }
            }

            if(nodes[newX][newY].getParent() == null) {
                System.out.println("Still no path");
                return null;
            }

            dstX = newX; dstY = newY;
        }

        Path p = new Path();
        Node n = nodes[dstX][dstY];
        while(n != nodes[srcX][srcY]) {
            p.addFirst(new Position(n.getX() + position.getX() - radius, n.getY() + position.getY() - radius));
            n = n.getParent();
        }
        return p;
    }

    private Node getLowestCost() {
        Node curLowest = null;
        for(Node n : open) {
            if(curLowest == null) {
                curLowest = n;
            } else {
                if(n.getCost() < curLowest.getCost()) {
                    curLowest = n;
                }
            }
        }
        return curLowest;
    }

    private void examineNode(Node n) {
        int heuristic = estimateDistance(current, n);
        int nextStepCost = current.getCost() + heuristic;
        if(nextStepCost < n.getCost()) {
            open.remove(n);
            closed.remove(n);
        }
        if(!open.contains(n) && !closed.contains(n)) {
            n.setParent(current);
            n.setCost(nextStepCost);
            open.add(n);
        }
    }

    /**
     * Estimates a distance between the two points.
     *
     * @param src
     *         The source node.
     * @param dst
     *         The distance node.
     *
     * @return The distance.
     */
    public int estimateDistance(Node src, Node dst) {
        int deltaX = src.getX() - dst.getX();
        int deltaY = src.getY() - dst.getY();
        return (Math.abs(deltaX) + Math.abs(deltaY)) * COST_STRAIGHT;
    }

}
