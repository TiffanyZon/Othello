// Johan Ayaz
package view;

import javax.swing.*;
import java.util.List;

/**
 * ScoreBoardPanel displays the top 10 players' scores in the Battleship game.
 * This panel is integrated within the MainFrame to provide a visual high score list.
 *
 * @author Johan Ayaz
 */
public class ScoreBoardPanel extends JPanel {
    private JList<String> playerList;
    private int width;
    private int height;
    private MainFrame mainFrame;
    private DefaultListModel<String> listView;

    /**
     * Initializes a new ScoreBoardPanel with the specified dimensions and parent frame.
     *
     * @param width Width of the panel
     * @param height Height of the panel
     * @param mainFrame The main frame to which this panel belongs
     */
    public ScoreBoardPanel(int width, int height, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocation(0, 0);
        setUp();
    }

    /**
     * Sets up the panel's components, including labels, list views, and scroll panes.
     */
    private void setUp() {
        this.setLayout(null);
        JLabel lblTitle = new JLabel("Top 10 Players", SwingConstants.CENTER);
        lblTitle.setBounds(0, 5, width, 20);

        listView = new DefaultListModel<>();
        playerList = new JList<>(listView);
        playerList.setBounds(10, 30, width - 20, height - 40);

        JScrollPane scrollPane = new JScrollPane(playerList);
        scrollPane.setBounds(80, 30, width - 100, 400);

        this.add(lblTitle);
        this.add(scrollPane);
    }

    /**
     * Updates the high score display with the provided list of scores.
     *
     * @param topScores List of player scores to display
     */
    public void updateHighScoreDisplay(List<String> topScores) {
        listView.removeAllElements();
        for (String score : topScores) {
            listView.addElement(score);
        }
        System.out.println("High scores updated: " + topScores);
    }

    /**
     * Clears the list of scores from the display.
     */
    public void clearScores() {
        listView.clear();
    }
}

