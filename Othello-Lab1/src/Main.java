
import javax.swing.*;

import java.awt.*;

/**
 * KRAV
 * - Bräda storlek 4x4
 * - Inital state är tom
 * - Alla drag tillåtna, utom en tagen bricka!
 * - Spelet är slut när alla positioner är täckta.
 * */


public class Main {

     public static void main(String[] args){

         JFrame frame = new JFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setResizable(false);

         frame.setMinimumSize(new Dimension(400,400));

         Board board = new Board();
         frame.add(board);

         frame.pack();
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);

     }
}
