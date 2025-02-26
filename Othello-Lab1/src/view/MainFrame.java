package view;

import javax.swing.*;
import java.awt.*;
import controller.Controller;

public class MainFrame extends JFrame {

    private JButton[][] boardButtons;
    private Controller controller;

    public MainFrame(Controller controller) {
        super("Othello");
        this.controller = controller;
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);


        boardButtons = new JButton[4][4];
        int btnSize = (this.getWidth() - 50) / 4;
        int padding = 20;
        int totalBoardWidth = btnSize * 4;
        int totalBoardHeight = btnSize * 4 + padding;
        int xOffset = (this.getWidth() - totalBoardWidth) / 2;
        int yOffset = (this.getHeight() - totalBoardHeight) / 2;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = new JButton();
                button.setBackground(Color.getHSBColor(0.33f,0.9f,0.45f));
                button.setSize(btnSize, btnSize);
                button.setLocation(j * btnSize + xOffset, i * btnSize + yOffset);
                final int x = i;
                final int y = j;

                button.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent e) {
                        controller.buttonPressed(ButtonType.BoardButton, x, y);
                    }
                });

                this.add(button);
                boardButtons[i][j] = button;
            }
        }

        this.setVisible(true);
    }

    public void updateButtonState(int x, int y, String player) {
        JButton button = boardButtons[x][y];
        if (player.equals("Human")) {
            button.setBackground(Color.white);
        } else {
            button.setBackground(Color.black);
        }
        button.setEnabled(false);
    }

    public int[] getButtonColor(){

        int whiteDiscs = 0;
        int blackDiscs = 0;

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Color color = boardButtons[i][j].getBackground();

                if (color.equals(Color.white)) {
                    whiteDiscs++;
                } else if (color.equals(Color.black)) {
                    blackDiscs++;
                }
            }
        }
        return new int[] {whiteDiscs,blackDiscs};
    }
}
