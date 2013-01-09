/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.customSwing;

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
      try {
         char c = ev.getKeyChar();
         if ((Character.isLetter(c) && !ev.isAltDown())
            || badchars.indexOf(c) > -1) {
            ev.consume();
         } else {
            int length = this.getText().length();
            if (c == '.') {
               if (this.getText().contains(".")) {

                  return;
               }

            }


            super.processKeyEvent(ev);



            if (length + 1 > limit) {
               String text = this.getText();
               this.setText(text.substring(0, length));
            }
            if (this.getText().contains(".") && (this.getText().indexOf(".") + 3 == length)) {
               String text = this.getText();
               this.setText(text.substring(0, length));
            }
         }

      } catch (Exception ex) {
      }
   }
}
