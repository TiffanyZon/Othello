

public class GameLogic {

    Board board = new Board();
    int[][] boardState = board.boardInitialState;
    boolean discPlacement[][];

    public void placeDisc(int row, int col, String player){

        if(boardState[row][col] == 0){
            boardState[row][col] = 1;

            if(player == "Computer")
            {
               board.paintCell(player);
            }
            else if(player == "Human")
            {
                board.paintCell(player);
            }
        }
    }

    public void squareSelected(int row, int col){
        if (isCellOccupied(row,col)){
            System.out.println("Cellen är redan upptagen.");
        }
    }

    public boolean isCellOccupied(int row, int col){


        return boardState[row][col] != 0;
    }

    public void checkNeighbour(int row , int col){

    }

    public void endOfGame(){

    }
}
