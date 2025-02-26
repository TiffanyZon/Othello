package game;
import view.MainFrame;

import javax.swing.*;
import java.util.Arrays;

// välj utgångsläge för algoritmen
// Skapa en metod för automatiska drag.
// loopa igenom alla möjliga drag
// hitta bestmove mha minimax funktion


public class GameLogic {

    private int[][] gameState;
    private int boardsize = 4;
    private int moveCount = 0;
    private int nodeCount = 0;
    private int maxDepth = 6;
    String currentPlayer;
    String opponent;
    private MainFrame view;

    public GameLogic(MainFrame view) {
        this.gameState = new int[boardsize][boardsize];

        this.currentPlayer = "Human";
        this.opponent = "Computer";
        this.view = view;
    }

    public boolean placeDisc(int row, int col, String player) {
        if (player.equals("Human")) {
            gameState[row][col] = 1;
        } else if (player.equals("Computer")) {
            gameState[row][col] = 2;

        } else {
            return false;
        }
        checkNeighbour(row, col, player);
        moveCount++;
        return true;
    }

    public void switchPlayer(String player) {
        if (player.equals("Human")) {
            setCurrentPlayer("Computer");
        } else if (player.equals("Computer")) {
            setCurrentPlayer("Human");
        }
    }

    public boolean isCellOccupied(int row, int col) {
        return gameState[row][col] != 0;
    }

    public void checkNeighbour(int currentRow, int currentCol, String player) {
        int currentPlayer;
        int opponent;

        if (player.equals("Human")) {
            currentPlayer = 1;
        } else {
            currentPlayer = 2;
        }

        if (currentPlayer == 1) {
            opponent = 2;
        } else {
            opponent = 1;
        }

        flip(currentRow, currentCol, -1, 0, currentPlayer, opponent);
        flip(currentRow, currentCol, 1, 0, currentPlayer, opponent);
        flip(currentRow, currentCol, 0, -1, currentPlayer, opponent);
        flip(currentRow, currentCol, 0, 1, currentPlayer, opponent);
        flip(currentRow, currentCol, -1, -1, currentPlayer, opponent);
        flip(currentRow, currentCol, -1, 1, currentPlayer, opponent);
        flip(currentRow, currentCol, 1, -1, currentPlayer, opponent);
        flip(currentRow, currentCol, 1, 1, currentPlayer, opponent);
    }

    public void flip(int row, int col, int rowStep, int colStep, int currentPlayer, int opponent) {
        int newRow = row + rowStep;
        int newCol = col + colStep;

        boolean hasOpponentDisc = false;

        while (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4 && gameState[newRow][newCol] == opponent) {
            hasOpponentDisc = true;
            newRow += rowStep;
            newCol += colStep;
        }

        if (hasOpponentDisc && newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4 && gameState[newRow][newCol] == currentPlayer) {
            newRow -= rowStep;
            newCol -= colStep;

            while (newRow != row || newCol != col) {
                gameState[newRow][newCol] = currentPlayer;
                view.updateButtonState(newRow, newCol, currentPlayer == 1 ? "Human" : "Computer");

                newRow -= rowStep;
                newCol -= colStep;
            }
        }
    }

    public int evaluateBoard(int[][] board) {
        int score = 0;

        int[][] weights = {
                {100, -20, 10, 10, -20, 100},
                {-20, -50, -2, -2, -50, -20},
                {10, -2, 5, 5, -2, 10},
                {10, -2, 5, 5, -2, 10},
                {-20, -50, -2, -2, -50, -20},
                {100, -20, 10, 10, -20, 100}
        };


        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                if (board[i][j] == 2) {
                    score += weights[i][j];
                } else if (board[i][j] == 1) {
                    score += weights[i][j];
                }
            }
        }

        return score;
    }

    public void computerMove()  {
        nodeCount = 0;
        if (!startAlgorithm())
        {
            for (int i = 0; i < boardsize; i++){
                for (int j = 0; j < boardsize; j++)
                {
                    if (!isCellOccupied(i,j))
                    {
                        placeDisc(i,j,"Computer");
                        view.updateButtonState(i,j,"Computer");
                        switchPlayer("Computer");
                        return;
                    }
                }
            }
        }
        else
        {
            int [] bestMove = bestMove();

            if (bestMove != null)
            {
                System.out.println("Computer chooses: (" + bestMove[0] + ", " + bestMove[1] + ")");
                placeDisc(bestMove[0], bestMove[1], "Computer");
                view.updateButtonState(bestMove[0], bestMove[1], "Computer");
                switchPlayer("Computer");
            }
            else
            {
                bestMove();
            }
        }

        System.out.println("Antal undersökta noder: " + nodeCount);
    }

    public boolean startAlgorithm() {
        return moveCount >= 10;
    }

    public int [] bestMove(){
        int bestValue = Integer.MIN_VALUE;
        int [] bestMove = null;

        for (int [] move : getAllPossibleMoves(gameState,2)){
            int [][] newBoard = tryMove(gameState,move,2);
            int moveScore = minimax(newBoard,maxDepth,false, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (moveScore > bestValue){
                bestValue = moveScore;
                bestMove = move;
            }
        }
        return bestMove;
    }

    public int minimax(int [][] boardState, int depth, boolean maximizing, float alpha, float beta) {
        nodeCount++;
        if (isEndOfGame() || depth == 0)
        {
            return evaluateBoard(boardState);
        }

        if (maximizing)
        {
            int maxValue = Integer.MIN_VALUE;

            for (int [] move : getAllPossibleMoves(boardState,2))
            {
                int [][] newBoard = tryMove(boardState,move,2);
                int value = minimax(newBoard, depth -1, false, alpha, beta);
                maxValue = Math.max(maxValue,value);
                alpha = Math.max(alpha,value);

                if (alpha >= beta)
                {
                    System.out.println("Maximizing: Pruning branch at depth " + depth + " with alpha=" + alpha + " and beta=" + beta);
                    break;
                }
            }

            return maxValue;

        } else {
            int minValue = Integer.MAX_VALUE;

            for (int [] move : getAllPossibleMoves(boardState,1)){
                int [][] newBoard = tryMove(boardState,move,1);
                int value = minimax(newBoard,depth-1,true,alpha,beta);
                minValue = Math.min(minValue,value);
                beta = Math.min(beta,value);

                if (alpha >= beta){
                    System.out.println("Minimizing: Pruning branch at depth " + depth + " with alpha=" + alpha + " and beta=" + beta);
                    break;
                }
            }

            return minValue;
        }

    }

    private int[][] copyBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] newBoard = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }

    public int[][] tryMove(int[][] board, int[] move, int player) {
        int[][] newBoard = copyBoard(board);
        newBoard[move[0]][move[1]] = player;
        return newBoard;
    }

    public int [][] getAllPossibleMoves(int [][] board, int player){

        int [][] possibleMoves = new int[16][2];

        int moveCount = 0;

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 0 && isOkMove(board, i,j,player)){
                    possibleMoves[moveCount][0] = i;
                    possibleMoves[moveCount][1] = j;
                    moveCount++;
                }
            }
        }

        return Arrays.copyOf(possibleMoves,moveCount);
    }

    public boolean isOkMove (int [][] board, int row, int col, int player){
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        return board[row][col] == 0;
    }

    public boolean isEndOfGame() {

        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                if (gameState[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String countResult(){
        int [] result = view.getButtonColor();
        int white = result[0];
        int black = result[1];
        String winner = "";
        if (white > black){
            winner = "Human";
        } else if (white < black){
            winner = "Computer";
        }

        return winner;

    }
    public void endGame(String winner) {

        JOptionPane.showMessageDialog(null, "Vinnaren är..... " + winner);
        System.exit(0);
    }
    private void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }

}


