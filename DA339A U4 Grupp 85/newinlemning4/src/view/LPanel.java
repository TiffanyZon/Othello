//Johan Ayaz

package view;

import javax.swing.*;

/**
 * Represents the left panel of the Battleship game which encompasses GUI elements such as player name input and game controls.
 */
public class LPanel extends JPanel {

    private JButton btnStartGame;
    private JTextField playerNameField;
    private JButton btnClearScores;
    private int width;
    private int height;
    private MainFrame mainFrame;

    /**
     * Constructor to initialize the left panel of the game.
     *
     * @param width Width of the panel.
     * @param height Height of the panel.
     * @param mainFrame Reference to the main frame.
     */
    public LPanel(int width, int height, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocation(0, 0);
        setUp();
    }

    /**
     * Sets up the GUI components for this panel.
     */
    private void setUp() {
        this.setLayout(null);  // Using null layout

        // Configuring playerNameField
        playerNameField = new JTextField("Player Name");
        playerNameField.setBounds(70, 30, width - 100, 30);  // Margins on the sides
        this.add(playerNameField);

        // Configuring btnStartGame
        btnStartGame = new JButton("Start Game");
        btnStartGame.setEnabled(false);
        btnStartGame.setBounds((width - (width / 5)) / 2, 65, width / 3 + 10, 30);  // Centered horizontally
        btnStartGame.addActionListener(l -> mainFrame.buttonPressed(ButtonType.StartNewGame));
        this.add(btnStartGame);


    }

    /**
     * Fetches the player's name from the input field.
     *
     * @return Player's name.
     */
    public String getPlayerName() {
        return playerNameField.getText().trim();
    }

    /**
     * Clears the input field of the player's name.
     */
    public void clearPlayerNameInput() {
        playerNameField.setText("");
    }

    /**
     * Enables the input field for the player's name.
     */
    public void enableNameInput() {
        playerNameField.setEditable(true);
    }

    /**
     * Fetches the "Clear Scores" button.
     *
     * @return "Clear Scores" button.
     */
    public JButton getBtnClearScores() {
        return btnClearScores;
    }

    /**
     * Fetches the "Start Game" button.
     *
     * @return "Start Game" button.
     */
    public JButton getBtnStartGame() {
        return btnStartGame;
    }

    /**
     * Disables the input field for the player's name.
     */
    public void disableNameInput() {
        playerNameField.setEditable(false);
    }
}
