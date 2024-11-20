package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the game board where ships are placed and shots are taken.
 * @author Johan Ayaz
 */
public class Board {

    /** The size of the game board. U4FG2:*/
    public final int BOARD_SIZE = 10;

    /** A 2D array representing the cells of the board where ships are placed. */
    private Ship[][] cells;

    /** A 2D array representing the cells of the board where shots are recorded. */
    private ShotResult[][] shots;

    /** A set of ships that have been placed on the board. */
    private Set<Ship> placedShips = new HashSet<>();

    /**
     * Constructs a new Board object.
     */
    public Board() {
        this.cells = new Ship[BOARD_SIZE][BOARD_SIZE];
        this.shots = new ShotResult[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Checks if a ship is sunk on the board.
     * @param ship The ship to be checked.
     * @return true if the ship is sunk, false otherwise.
     * U4FG12
     */
    public boolean isShipSunk(Ship ship) {
        // Check if the ship is already marked as sunk
        if(ship.isSunk()) {
            return true;
        }
        for (Position position  : ship.getPositions()) {
            int x = position.getX();
            int y = position.getY();
            if (cells[y][x] == ship && (shots[y][x] == null || shots[y][x] != ShotResult.HIT)) {
                return false;
            }
        }
        ship.markAsSunk();
        for (Position position : ship.getPositions()) {
            int x = position.getX();
            int y = position.getY();
            shots[y][x] = ShotResult.SUNK;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (cells[y][x] != null) {
                    if (shots[y][x] == ShotResult.HIT) {
                        sb.append('H');  // Det finns ett skepp här som har träffats.
                    } else if (shots[y][x] == ShotResult.SUNK) {
                        sb.append('X');  // Skeppet här har sjunkit.
                    } else {
                        sb.append('S');  // Det finns ett skepp här som inte har träffats.
                    }
                } else if (shots[y][x] == ShotResult.MISS) {
                    sb.append('O');  // Ett skott har tagits här, men inget skepp.
                } else {
                    sb.append('.');  // Ingen skott tagits och inget skepp här.
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Gets the ship present at a given position.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The ship at the specified position, or null if there's no ship.
     */
    public Ship getShipAt(int x, int y) {
        return cells[y][x];
    }

    /**
     * Clears the board of ships and shots.
     */
    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = null;
                shots[i][j] = null;
            }
        }
    }

    /**
     * Places a ship on the board.
     * @param ship The ship to be placed.
     * @param x The starting x-coordinate.
     * @param y The starting y-coordinate.
     * @return true if the ship was successfully placed, false otherwise.
     * U4FG8
     */
    public boolean placeShip(Ship ship, int x, int y) {
        if (ship.isVertical() && y + ship.getLength() > BOARD_SIZE) return false;
        if (!ship.isVertical() && x + ship.getLength() > BOARD_SIZE) return false;

        if (isValidPlacement(ship, x, y)) {
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.isVertical()) {
                    cells[y + i][x] = ship;
                    ship.addPosition(new Position(x, y + i));
                } else {
                    cells[y][x + i] = ship;
                    ship.addPosition(new Position(x + i, y));
                }
            }
            placedShips.add(ship);  // Lägg till skeppet i 'placedShips'
            return true;
        }
        return false;
    }

    /**
     * Checks if a ship has been placed on the board.
     * @param ship The ship to be checked.
     * @return true if the ship has been placed, false otherwise.
     */
    public boolean hasShip(Ship ship) {
        return placedShips.contains(ship);
    }

    /**
     * Shoots at a given position on the board.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The result of the shot (HIT, SUNK, MISS).
     * U4FG9 och U4FG11
     */
    public ShotResult shoot(int x, int y) {

        if (cells[y][x] == null) {
            shots[y][x] = ShotResult.MISS;
            return ShotResult.MISS;
        } else {
            Ship ship = cells[y][x];
            if (ship.isHit(x, y)) {
                shots[y][x] = ShotResult.HIT;

                if(isShipSunk(ship)) {
                    return ShotResult.SUNK;
                } else {
                    return ShotResult.HIT;
                }
            } else {
                shots[y][x] = ShotResult.MISS;
                return ShotResult.MISS;
            }
        }
    }

    /**
     * Validates if a ship can be placed at a given starting position.
     * @param ship The ship to be placed.
     * @param startX The starting x-coordinate.
     * @param startY The starting y-coordinate.
     * @return true if the ship can be placed, false otherwise.
     * U4FG5
     */
    private boolean isValidPlacement(Ship ship, int startX, int startY) {
        for (int i = 0; i < ship.getLength(); i++) {
            int x, y;

            if (ship.isVertical()) {
                x = startX;
                y = startY + i;
            } else {
                x = startX + i;
                y = startY;
            }

            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
                return false;
            }

            if (cells[y][x] != null) {
                return false;
            }
        }
        return true;
    }

}

