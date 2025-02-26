import controller.Controller;

import javax.swing.*;

/**
 * KRAV
 *  - Bräda 4x4
 *   - Alla drag tillåtna utom på upptagna platser + platser utanför bräda
 *  - Init tillstånd för bräder är tom
 *  - Spelet är slut när brädan är fylld.
 * */


public class Main {
     public static void main(String[] args) {
          try {
              UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
          } catch (Exception e) {
               e.printStackTrace();
          }
          new Controller();
     }

}
