/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.util;

import ca.robokids.robooffice.desktop.loaders.FramePropertyLoader;
import ca.robokids.robooffice.desktop.main.MainRoboOfficeJFrame;
import java.awt.Dialog.ModalityType;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Create various kind of pop-up messages box, using JOptionPane
 * @author Coldsoft
 */

public class PopupMessage {
   static JDialog modalDialog;
   /**
    * Create a deletion confirm message box to the user
    * 
    * @param deleting list of string items separated with \n. Only need \n in between strings 
    * 
    * @return true if user click yes; false if user click no.
    */
   public static boolean createDelete(String deleting)
   {
      return (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(MainRoboOfficeJFrame.getInstance(),
                    "Do you want to delete the following..\n\n" + deleting + "\n\nNote: Deletion is permanent!", "Permanent Delete",JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,FramePropertyLoader.getImageIcon("systemIcon")));
   }
   
   
   public static String createInput(String message,String title)
   {
      return (String)JOptionPane.showInputDialog(MainRoboOfficeJFrame.getInstance(), message, title, 
         JOptionPane.QUESTION_MESSAGE, FramePropertyLoader.getImageIcon("systemIcon"), null, null);
   }
   
   
   public static boolean createConfirmPopUp(String msg, String title)
    {
        return (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(MainRoboOfficeJFrame.getInstance(),
                    msg, title,JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                    FramePropertyLoader.getImageIcon("systemIcon")));
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
        JOptionPane.showMessageDialog(MainRoboOfficeJFrame.getInstance(),
                    msg, title,
                    JOptionPane.INFORMATION_MESSAGE,FramePropertyLoader.getImageIcon("systemIcon"));
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
        JOptionPane.showMessageDialog(MainRoboOfficeJFrame.getInstance(),
                    msg, title,
                     JOptionPane.ERROR_MESSAGE,FramePropertyLoader.getImageIcon("systemIcon"));
    }
   
   public static void startLoading()
   {
      if (modalDialog == null)
      { 
         modalDialog =  new JDialog(MainRoboOfficeJFrame.getInstance(), "Busy", ModalityType.DOCUMENT_MODAL);
         modalDialog.setSize(200, 150);
         modalDialog.setLocationRelativeTo(MainRoboOfficeJFrame.getInstance());
      }
      modalDialog.setVisible(true);
      
   }
   public static void endLoading()
   {
      modalDialog.setVisible(false);
   }
}
