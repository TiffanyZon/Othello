import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    int cols = 4;
    int rows = 4;
    int cellSize = 100;
    Graphics g;

    int [][] boardInitialState = new int[rows][cols];



    public Board() {
        this.setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
    }

    protected void paintComponent(){
        // super.paintComponent(g);

     //   g.setColor(Color.getHSBColor(0.33f,0.9f,0.45f));
     //   g.fillRect(0,0,getWidth(),getHeight());

     //   g.setColor(Color.BLACK);

        for (int i = 1; i < cols; i++){
            int x = i * cellSize;
  //          g.drawLine(x,0,x,getHeight());
        }

        for(int i = 1; i < rows; i++){
            int y = i * cellSize;
    //        g.drawLine(0,y, getWidth(),y);
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                boardInitialState[i][j] = 0;
            }
        }

    }

    public void paintCell(String player){

        if (player == "Computer"){
            g.setColor(Color.BLACK);
        } else if (player == "Human") {
            g.setColor(Color.WHITE);
        } else {
            System.out.println("Invalid player....?");
        }

    }

    public static void main(String[] args){

        Board board = new Board();

        board.paintComponent();
    }
}
