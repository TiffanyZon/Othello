//Johan Ayaz

package view;

import controller.Controller;
import javax.swing.*;
import java.util.List;


/**
 * Represents the main frame of the Battleship game which encompasses all the GUI elements.
 * U4FG1
 */
public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private Controller controller;

    /**
     * Constructor to initialize the main frame of the game.
     *
     * @param width Width of the frame.
     * @param height Height of the frame.
     * @param controller Reference to the game controller.
     */
    public MainFrame(int width, int height, Controller controller) {
        super("Battleship");
        this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, this);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Updates the state of a specific button on the game board.
     *
     * @param x X-coordinate of the button.
     * @param y Y-coordinate of the button.
     * @param hit Whether the shot was a hit or miss.
     */
    public void updateButtonStateOnGameBoard(int x, int y, boolean hit) {
        mainPanel.getRightPanel().updateButtonState(x, y, hit);
    }

    /**
     * Enables the "Start Game" button.
     */
    public void enableAllButtons() {
        mainPanel.getLeftPanel().getBtnStartGame().setEnabled(true);
    }

    /**
     * Resets a specific button's state on the game board.
     *
     * @param x X-coordinate of the button.
     * @param y Y-coordinate of the button.
     */
    public void resetGameBoardButton(int x, int y) {
        mainPanel.getRightPanel().resetButton(x, y);
    }

    /**
     * Fetches the player's name.
     *
     * @return Player's name.
     */
    public String playerName() {
        return mainPanel.getLeftPanel().getPlayerName();
    }

    /**
     * Displays a popup when the game is over.
     *
     * @return User's choice whether to play again or end the game.
     * U4FG19
     */
    public int displayEndGamePopup() {
        String[] options = {"Play Again", "End Game"};
        return JOptionPane.showOptionDialog(this, "Grattis! Alla skepp är sänkta!", "Spelet är slut", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    /**
     * Updates the status label with a provided message.
     *
     * @param status Status message to be displayed.
     */
    public void statusLabel(String status) {
        mainPanel.getRightPanel().setTextStatusLabel(status);
    }





    /**
     * Calls the controller when a button is pressed with its coordinates.
     *
     * @param pressedButton Type of the button pressed.
     * @param x X-coordinate of the button.
     * @param y Y-coordinate of the button.
     */
    public void buttonPressed(ButtonType pressedButton, int x, int y) {
        controller.buttonPressed(pressedButton, x, y);
    }

    /**
     * Calls the controller when a button is pressed without coordinates.
     *
     * @param pressedButton Type of the button pressed.
     */
    public void buttonPressed(ButtonType pressedButton) {
        controller.buttonPressed(pressedButton);
    }

    /**
     * Enables the input field for the player's name.
     */
    public void enableNameInput() {
        mainPanel.getLeftPanel().enableNameInput();
    }

    /**
     * Clears the input field of the player's name.
     */
    public void clearPlayerNameInput() {
        mainPanel.getLeftPanel().clearPlayerNameInput();
    }

    /**
     * Displays a popup to let the user choose a game board.
     *
     * @return The choice of the user.
     */
    public int displayBoardChoicePopup() {
        return mainPanel.getRightPanel().displayBoardChoicePopup();
    }

    /**
     * Disables the input field for the player's name.
     */
    public void disableNameInput() {
        mainPanel.getLeftPanel().disableNameInput();
    }

    /**
     * Enables the "Clear Scores" button.
     */
    public void clearScore() {
        mainPanel.getLeftPanel().getBtnClearScores().setEnabled(true);
    }

    /**
     * Enables all buttons on the game board.
     */
    public void enableGameBoardButtons() {
        mainPanel.getRightPanel().enableAllButtons();
    }

    /**
     * Disables all buttons on the game board.
     */
    public void disableGameBoardButtons() {
        mainPanel.getRightPanel().disableAllButtons();
    }
}


