/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.components;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Coldsoft
 */
public class CurrencyJTextField extends JTextField {

   final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/><, ";
   private int limit = 8;

   /**
    *
    * @param limit
    */
   public void setLimit(int limit) {
      this.limit = limit;
   }

   @Override
   public void processKeyEvent(KeyEvent ev) {

      char c = ev.getKeyChar();
      if ((Character.isLetter(c) && !ev.isAltDown())
         || badchars.indexOf(c) > -1) {
         ev.consume();
      } else {


         super.processKeyEvent(ev);



         String text = this.getText();
         if (!text.matches("^[0-9]*[\\.]?[0-9]?[0-9]?$")) {
            this.setText(text.substring(0, text.length() - 1));
         }
      }
   }
}
