package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an abstract base class for a ship in a board game.
 * This ship can be placed vertically or horizontally and has a certain length.
 *
 * @author Johan Ayaz
 */
public abstract class Ship {

    /** Length of the ship */
    private final int length;
    /** Indicates if the ship is placed vertically or horizontally */
    private boolean isVertical;
    /** List of positions the ship occupies on the game board */
    private List<Position> positions = new ArrayList<>();
    /** Indicates if the ship has been sunk */
    private boolean isSunk = false;

    /**
     * Constructs a new ship with a specified length.
     *
     * @param length Length of the ship
     */
    public Ship(int length) {
        this.length = length;
        this.isVertical = true;
    }

    /**
     * Rotates the ship. If it's vertical, it becomes horizontal and vice versa.
     */
    public void rotate() {
        this.isVertical = !this.isVertical;
    }

    /**
     * Checks if the ship is placed vertically.
     *
     * @return true if the ship is vertical, false otherwise
     */
    public boolean isVertical() {
        return isVertical;
    }

    /**
     * Adds a position to the list of positions this ship occupies.
     *
     * @param position The position to add
     */
    public void addPosition(Position position) {
        this.positions.add(position);
    }

    /**
     * Gets the list of positions this ship occupies.
     *
     * @return List of positions
     */
    public List<Position> getPositions() {
        return this.positions;
    }

    /**
     * Checks if the ship is hit at a specific position.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @return true if the ship is hit at the given position, false otherwise
     */
    public boolean isHit(int x, int y) {
        for (Position position : positions) {
            if (position.getX() == x && position.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the ship has been sunk.
     *
     * @return true if the ship is sunk, false otherwise
     */
    public boolean isSunk() {
        return this.isSunk;
    }

    /**
     * Marks the ship as sunk.
     */
    public void markAsSunk() {
        this.isSunk = true;
    }

    /**
     * Gets the length of the ship.
     *
     * @return Length of the ship
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Gets the display name of the ship. Needs to be implemented by subclasses.
     *
     * @return Display name of the ship
     */
    public abstract String getDisplayName();
}


