package controller;

import game.GameLogic;
import view.ButtonType;
import view.MainFrame;

import javax.swing.*;

public class Controller {

    private MainFrame view;
    private GameLogic game;

    public Controller() {
        view = new MainFrame(this);
        game = new GameLogic(view);

    }

    public void buttonPressed(ButtonType buttonType, int row, int col) {
        if (buttonType == ButtonType.BoardButton) {
            handleBoardButtonClick(row, col);
        }
    }

    private void handleBoardButtonClick(int row, int col) {
        if (game.isCellOccupied(row, col)) {
            JOptionPane.showMessageDialog(null,"Cellen är redan upptagen, testa en annan!");
            return;
        }

        boolean placed = game.placeDisc(row, col, game.getCurrentPlayer());
        if (placed) {
            view.updateButtonState(row, col, game.getCurrentPlayer());
            game.switchPlayer(game.getCurrentPlayer());
        }

        if (game.isEndOfGame()) {
            String winner = game.countResult();
            game.endGame(winner);

           return;
        }

        if (game.getCurrentPlayer().equals("Computer"))
        {
            game.computerMove();

            if (game.isEndOfGame())
            {
                String winner = game.countResult();
                game.endGame(winner);
            }
        }

    }
}
