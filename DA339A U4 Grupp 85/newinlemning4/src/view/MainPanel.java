// Johan Ayaz
package view;

import javax.swing.*;

/**
 * MainPanel serves as the primary container for other panels in the Battleship game's UI.
 * It divides the main display into left (LPanel), right (RPanel), and ScoreBoard panels.
 *
 * @author Johan Ayaz
 */
public class MainPanel extends JPanel {
    private LPanel lPanel;
    private RPanel rPanel;
    private ScoreBoardPanel scorePanel;

    /**
     * Initializes a new MainPanel with the specified dimensions and parent frame.
     *
     * @param width Width of the main panel
     * @param height Height of the main panel
     * @param mainFrame The main frame to which this panel belongs
     */
    public MainPanel(int width, int height, MainFrame mainFrame) {
        super(null); // Using a null layout as specified
        this.setSize(width, height);

        lPanel = new LPanel(width / 3 , height, mainFrame);
        lPanel.setBounds(0, 0, width / 3, height); // Set position and size for lPanel
        add(lPanel);

        rPanel = new RPanel(width / 3 + 50, height, mainFrame);
        rPanel.setBounds(width / 3 , 0, width / 3 +50, height); // Set position and size for rPanel
        add(rPanel);


    }

    /**
     * Retrieves the LPanel (left panel) from the main panel.
     *
     * @return The left panel (LPanel)
     */
    protected LPanel getLeftPanel() {
        return lPanel;
    }

    /**
     * Retrieves the RPanel (right panel) from the main panel.
     *
     * @return The right panel (RPanel)
     */
    protected RPanel getRightPanel() {
        return rPanel;
    }


}
