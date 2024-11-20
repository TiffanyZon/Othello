// Johan Ayaz
package controller;

import model.*;
import view.ButtonType;
import view.MainFrame;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Battleship game. Handles the interaction between the
 * game's model and view components.
 *
 * @author Johan Ayaz
 */
public class Controller {

    private MainFrame view;
    private Game game;
    private Player player;
    private HighScore highScore;
    private final String HIGHSCORE_FILE = "highscores.txt";

    /**
     * Initializes the controller, sets up the game defaults, and prepares the UI.
     */
    public Controller() {
        System.out.println("Controller initieras");
        view = new MainFrame(1000, 500, this);
        initializeGameDefaults();
        view.enableAllButtons();
        highScore = new HighScore();

        loadHighScores();

        // Feedback till användaren vid start
        setStatusMessage("Skriv in ditt namn för att börja spelet!");

    }


    /**
     * Initializes default game settings and prepares the game board UI.
     */
    private void initializeGameDefaults() {
        view.disableGameBoardButtons();
        game = new Game();
    }

    /**
     * Handles end of game events, such as presenting the user with options to restart or quit.
     */
    private void handleEndOfGame() {
        view.disableGameBoardButtons();
        int selectedOption = view.displayEndGamePopup();

        if (selectedOption == JOptionPane.YES_OPTION) {
            if (player != null) {
                highScore.addEntry(new HighScoreEntry(player.getName(), player.getShotsTaken()));
                saveHighScores();

            }
            resetToStartScreen();
            resetGameBoardButtons();
        } else if (selectedOption == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Loads high scores from a predefined file.
     */
    private void loadHighScores() {
        try {
            highScore.loadFromFile(HIGHSCORE_FILE);
        } catch (IOException e) {
            displayMessage("Problem med att ladda highscores: " + e.getMessage());
        }
    }

    /**
     * Saves the high scores to a predefined file.
     *
     * @return false since the method does not handle a successful save case.
     */

    private void saveHighScores() {
        try {
            highScore.saveToFile(HIGHSCORE_FILE);
        } catch (IOException e) {
            displayMessage("Problem med att spara highscores: " + e.getMessage());
        }
    }

    /**
     * Prompts the player for their name, and initializes the game with the provided name.
     */
    private void promptForPlayerName() {
        String name = view.playerName();
        if (!name.isEmpty()) {
            player = new Player(name);
            game.setPlayer(player);
            System.out.println("Ny spelare skapad: " + player.getName());
            displayMessage("Hej " + name + "! Välkommen till spelet!");
            view.disableNameInput();
        } else {
            displayMessage("Vänligen skriv in ditt namn!");
        }
    }


    private void startNewGame() {

        game.prepareTwoBoards();
        displayBoards();
        int boardChoice = view.displayBoardChoicePopup();
        if(boardChoice == 1) {
            game.setChosenBoard(game.getBoardChoice1());
            player.setBoard(game.getBoardChoice1());
            setStatusMessage("Du har valt bräde 1!");
        } else {
            game.setChosenBoard(game.getBoardChoice2());
            player.setBoard(game.getBoardChoice2());
            setStatusMessage("Du har valt bräde 2!");
        }

        resetGameBoardButtons();
        view.enableGameBoardButtons();
    }

    /**
     * Resets the game board buttons to their default state.
     */
    private void resetGameBoardButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                view.resetGameBoardButton(i, j);
            }
        }
    }

    /**
     * Resets the game to the start screen, clearing player details and initializing a new game instance.
     */
    private void resetToStartScreen() {
        view.clearPlayerNameInput();
        view.enableNameInput();
        player = null;
        game = new Game();
    }

    /**
     * Displays the prepared boards in the console.
     */
    private void displayBoards() {
        String board1 = game.getBoardAsString(1);
        String board2 = game.getBoardAsString(2);

        System.out.println("Brädval 1:");
        System.out.println(board1);
        System.out.println(" ");
        System.out.println("Brädval 2:");
        System.out.println(board2);
    }

    /**
     * Processes a board button click, determining if it's a hit, miss, or if a ship is sunk.
     *
     * @param x X-coordinate of the board position
     * @param y Y-coordinate of the board position
     */
    private void handleBoardButtonClick(int x, int y) {
        ShotResult result = game.attemptShot(x, y);
        String shipName;
        switch (result) {
            case HIT:
                shipName = game.getShipNameAt(x, y);
                setStatusMessage(shipName + " träffad!");
                updateGameBoardButton(x, y, true);
                break;
            case SUNK:
                shipName = game.getShipNameAt(x, y);
                setStatusMessage(shipName + " sänkt!");
                updateGameBoardButton(x, y, true);
                break;
            case MISS:
                setStatusMessage("Miss!");
                updateGameBoardButton(x, y, false);
                break;
        }
        if (game.allShipsSunk()) {
            handleEndOfGame();
        }
    }

    /**
     * Handles actions based on the type of button pressed on the UI.
     *
     * @param buttonType Enum indicating which type of button was pressed
     */
    public void buttonPressed(ButtonType buttonType) {
        switch (buttonType) {
            case StartNewGame:
                promptForPlayerName();
                if (player != null) {
                    startNewGame();
                }
                break;
            case ClearScores:
                view.clearScore();
                break;
            default:
                break;
        }
    }

    /**
     * Updates the visual state of a button on the game board.
     *
     * @param x X-coordinate of the button position
     * @param y Y-coordinate of the button position
     * @param hit Boolean indicating if the shot was a hit or miss
     */
    public void updateGameBoardButton(int x, int y, boolean hit) {
        view.updateButtonStateOnGameBoard(x, y, hit);
    }

    /**
     * Handles the actions when a board button is pressed.
     *
     * @param pressedButton Enum indicating the type of button pressed
     * @param x X-coordinate of the button position
     * @param y Y-coordinate of the button position
     */
    public void buttonPressed(ButtonType pressedButton, int x, int y) {
        if (pressedButton == ButtonType.BoardButton) {
            handleBoardButtonClick(x, y);
        }
    }

    /**
     * Displays a pop-up message on the UI.
     *
     * @param message The message to display
     */
    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }

    /**
     * Sets a status message on the main UI.
     *
     * @param message The status message to display
     */
    public void setStatusMessage(String message) {
        view.statusLabel(message);
    }
}
