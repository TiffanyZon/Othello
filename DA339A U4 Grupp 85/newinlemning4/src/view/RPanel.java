//Johan Ayaz


package view;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the right panel of the Battleship game, which acts as the game board.
 */
public class RPanel extends JPanel {

    private final MainFrame mainFrame;
    private final JButton[][] boardButtons;
    private final JLabel lblTitle;
    private final JLabel lblStatus;
    private final int width;
    private final int height;

    /**
     * Initializes the RPanel.
     *
     * @param width     Width of the panel.
     * @param height    Height of the panel.
     * @param mainFrame Reference to the main frame for callback actions.
     */
    public RPanel(int width, int height, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.width = width;
        this.height = height;

        this.setLayout(null);
        this.setSize(width, height);
        setLocation(width, 0);

        boardButtons = new JButton[4][4]; //U4FG2:


        lblTitle = new JLabel();
        lblStatus = new JLabel();

        setUp();
    }

    /**
     * Sets up the GUI elements for the RPanel.
     */
    private void setUp() {
        lblTitle.setText("GAME BOARD");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 1, width / 2, 20);
        this.add(lblTitle);

        lblStatus.setText("STATUS: Awaiting First Move");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(width / 2, 5, width / 2, 20);
        this.add(lblStatus);

        int btnSize = (this.getWidth() - 20) / 5;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton();
                button.setBackground(Color.green);
                button.setSize(btnSize, btnSize);
                button.setLocation(i * btnSize + 10, 30 + j * btnSize);
                final int x = i;
                final int y = j;
                button.addActionListener(e -> mainFrame.buttonPressed(ButtonType.BoardButton, x, y));
                this.add(button);
                boardButtons[i][j] = button;
            }
        }
    }

    /**
     * Updates the state of a specific button on the game board.
     *
     * @param x    X-coordinate of the button.
     * @param y    Y-coordinate of the button.
     * @param hit  Whether the shot was a hit or miss.
     */
    public void updateButtonState(int x, int y, boolean hit) {
        JButton button = boardButtons[x][y];
        button.setBackground(Color.green);
        if (hit) {
            button.setBackground(Color.white);
        } else {
            button.setBackground(Color.black);
        }
        button.setEnabled(false);
    }

    /**
     * Sets the text for the status label.
     *
     * @param statusText The status message to be displayed.
     */
    public void setTextStatusLabel(String statusText) {
        lblStatus.setText(statusText);
    }

    /**
     * Disables all buttons on the game board.
     */
    public void disableAllButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                boardButtons[i][j].setEnabled(false);
            }
        }
    }

    /**
     * Enables all buttons on the game board.
     */
    public void enableAllButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                boardButtons[i][j].setEnabled(true);
            }
        }
    }

    /**
     * Resets a specific button's state on the game board.
     *
     * @param x X-coordinate of the button.
     * @param y Y-coordinate of the button.
     */
    public void resetButton(int x, int y) {
        JButton button = boardButtons[x][y];
        button.setBackground(null);
        button.setEnabled(true);
    }

    /**
     * Displays a popup to let the user choose a game board.
     *
     * @return The choice of the user.
     */
    public int displayBoardChoicePopup() {
        Object[] options = {"Brädval 1", "Brädval 2"};
        int choice = JOptionPane.showOptionDialog(this,
                "Vilket bräd vill du spela med?",
                "Välj bräd",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return (choice == 0) ? 1 : 2;
    }
}
