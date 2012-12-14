/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.utility.gui;

import javax.swing.JOptionPane;

/**
 * Create various kind of pop-up messages box, using JOptionPane
 * @author Coldsoft
 */
public class PopupMessage {
   
   /**
    * Create a deletion confirm message box to the user
    * 
    * @param deleting list of string items separated with \n. Only need \n in between strings 
    * 
    * @return true if user click yes; false if user click no.
    */
   public static boolean createDelete(String deleting)
   {
      return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                    "Do you want to delete the following..\n", deleting + "\n\n",JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE));
   }
   
   /**
    * Create an information message box to the user
    * @param msg information for the user
    * @param title title of the message box, default(if null) is "So.."
    */
   public static void createInfo(String msg, String title)
   {
      if (title == null) {
         title = "Message";
      }
        JOptionPane.showMessageDialog(null,
                    msg, title,
                    JOptionPane.INFORMATION_MESSAGE);
   }
   /**
    * Create an Error Message box for the user
    * @param msg the error message
    * @param title the title. Default(if null) is "Oops, sorry.."
    */
   public static void createErrorPopUp(String msg, String title)
    {
        if (title == null) {
         title = "Oops, sorry..";
      }
        JOptionPane.showMessageDialog(null,
                    msg, title,
                     JOptionPane.ERROR_MESSAGE);
    }
}
