/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;
import ca.robokids.utility.gui.*;
/**
 *
 * @author Coldsoft
 */
public class testDialog {
   
   public static void main(String [] args)
   {
      inputDialog input = new inputDialog(null, "me");
      input.pack();
      input.setVisible(true);
   }
}
