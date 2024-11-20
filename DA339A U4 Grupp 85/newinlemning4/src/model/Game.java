package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the main game logic and its flow, managing player actions, ship placements, and game states.
 * @author Johan Ayaz
 */
public class Game {
    private Board board;
    private List<Ship> ships;
    private Board boardChoice1;
    private Board boardChoice2;
    private Player player;

    /**
     * Initializes a new Game instance, setting up the board, ships, and a default player.
     */
    public Game() {
        this.board = new Board();
        this.ships = new ArrayList<>();
        this.player = new Player("DefaultName");
        initializeShips();
    }

    /**
     * Initializes a set of ships for the game.U4FG3 and U4FG4
     */
    private void initializeShips() {
        ships.add(new Submarine());
        ships.add(new TorpedoBoat());
        ships.add(new Destroyer());
        ships.add(new Cruiser());
        ships.add(new Battleship());
    }



    /**
     * Allows the player to attempt a shot on the board.
     * @param x The x-coordinate of the shot.
     * @param y The y-coordinate of the shot.
     * @return The result of the shot.
     * U4FG13
     */
    public ShotResult attemptShot(int x, int y) {
        return player.takeShot(x, y);
    }

    /**
     * Checks if all ships on the board have been sunk.
     * @return True if all ships have been sunk, false otherwise.
     */
    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!board.isShipSunk(ship)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the string representation of the specified board.
     * @param boardNumber The board number (1 or 2).
     * @return The string representation of the board.
     */
    public String getBoardAsString(int boardNumber) {
        if (boardNumber == 1) {
            return boardChoice1.toString();
        } else {
            return boardChoice2.toString();
        }
    }

    /**
     * Retrieves the name of the ship at the specified coordinates on the board.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The name of the ship, or null if no ship is present.
     * U4FG11
     */
    public String getShipNameAt(int x, int y) {
        Ship ship = board.getShipAt(x, y);
        return ship != null ? ship.getDisplayName() : null;
    }

    /**
     * komplettering
     * Prepares two boards with randomly placed ships for the game.
     */

    public void prepareTwoBoards() {
        this.boardChoice1 = new Board();
        this.boardChoice2 = new Board();

        setupBoard1(boardChoice1);
        setupBoard2(boardChoice2);
    }


    /**
     * komplettering
     * Sets up the game board with an alternative ship arrangement.
     *
     * @param board The game board to be setup
     */
    private void setupBoard1(Board board) {
        board.placeShip(ships.get(0), 0, 0); // Placera Submarine vågrätt
        board.placeShip(ships.get(1), 1, 0); // Placera TorpedoBoat lodrätt
        board.placeShip(ships.get(2), 4, 1); // Placera Destroyer vågrätt
        board.placeShip(ships.get(3), 1, 3); // Placera Cruiser lodrätt
        board.placeShip(ships.get(4), 2, 5); // Placera Battleship vågrätt
    }

    /**
     * komplettering
     * Sets up the game board with an alternative ship arrangement.
     *
     * @param board The game board to be setup
     */

    private void setupBoard2(Board board) {
        board.placeShip(ships.get(0), 0, 0); //placera Submarine lodrätt
        board.placeShip(ships.get(1), 3, 0); // placera TorpedoBoat vågrätt
        board.placeShip(ships.get(2), 4, 1); // placera Destroyer lodrätt
        board.placeShip(ships.get(3), 6, 3); // placera Cruiser vågrätt
        board.placeShip(ships.get(4), 7, 5); // placera Battleship lodrätt
    }

    /**
     * Sets the player for the game.
     * @param player The player to be set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Board getBoardChoice1() {
        return boardChoice1;
    }

    public Board getBoardChoice2() {
        return boardChoice2;
    }

    /**
     * Sets the chosen board for the game.
     * @param chosenBoard The board chosen for the game.
     */
    public void setChosenBoard(Board chosenBoard) {
        this.board = chosenBoard;
    }

}
