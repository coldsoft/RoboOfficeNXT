/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.main;

import ca.robokids.robooffice.desktop.loaders.ActionMappingLoader;
import ca.robokids.robooffice.desktop.loaders.FramePropertyLoader;
import ca.robokids.robooffice.desktop.tabs.Tab;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import de.javasoft.swing.JYTabbedPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Coldsoft
 */
public class TabManager implements ActionListener {

   private static JYTabbedPane container = null;
   private final static String tabLocation = "ca.robokids.robooffice.desktop.tabs.";

   public static void setContainer(JYTabbedPane container) {
      TabManager.container = container;

   }

   public static void resizeTab(int parentWidth) {
      if (container != null) {
         int count = container.getContentPanel().getComponentCount();
         if (count != 0) {
            int size = (parentWidth - 10) / count;
            if (size > container.getMinimumTabSize()) {
               container.setMaximumTabSize(size);
               container.repaint();
               container.revalidate();
               //lblMsg.setText("current size" + String.valueOf(size));
            }
         }


      }
   }

   public static void createTab(final String action) {
      Thread t = new Thread() {

         public void run() {
            MainRoboOfficeJFrame.setBusy(true);
            try {
               TabManager.openTabThreaded(action);
            } catch (Exception e) {
               e.printStackTrace();
            }
            MainRoboOfficeJFrame.setBusy(false);
         }
      };

      t.start();

   }

   public static void openTabThreaded(String action) {
      if (noTabs(action)) {
         return;
      }
      String tabName = ActionMappingLoader.getTabName(action);
      if (exists(tabName)) {
         switchTo(tabName);
         return;
      }

      String tabClass = tabLocation + ActionMappingLoader.getTabClass(action);
      try {

         JPanel panel = (JPanel) Class.forName(tabClass).newInstance();
         addTab(tabName, panel);


      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
         Logger.getLogger(TabManager.class.getName()).log(Level.SEVERE, null, ex);
      }


   }

   private static boolean exists(String tabName) {
      if (container.indexOfTab(tabName) >= 0) {
         return true;
      }
      return false;
   }

   private static void switchTo(String tabName) {
      try {
         container.setSelectedIndex(container.indexOfTab(tabName));
         Tab tab = (Tab) container.getSelectedComponent();
         tab.refresh();
      } catch (IndexOutOfBoundsException e) {
         System.err.println("Tab selection index out of bound.");
         e.printStackTrace();

      }
   }

   public static void closeTab(String tabName) {
      int index = container.indexOfTab(tabName);
      if (index > -1) {
         container.removeTabAt(index);
      }
   }
   public static void changeTabName(String tabName, String newName)
   {
      int index = container.indexOfTab(tabName);
      if (index > -1) {
         container.setTitleAt(index, newName);
      }
   }

   private static void addTab(String tabName, JPanel panel) {
      container.addTab(tabName, panel);
      panel.setName(tabName);
      int index = container.indexOfTab(tabName);
      container.setToolTipTextAt(index, tabName);
      container.setSelectedIndex(index, true);
      container.setIconAt(index, new ImageIcon(FramePropertyLoader.getImageIcon("tabIcon").getImage()));
      resizeTab(MainRoboOfficeJFrame.getInstance().getWidth());
      //tabTable.put(tabName,index);
      Tab currentTab = (Tab) panel;
      currentTab.setFocus();
   }

   private static boolean noTabs(String action) {
      if (action.equals("logOut")) {
         MainRoboOfficeJFrame.getInstance().logout();
         return true;
      } else if (action.equals("exit")) {
         MainRoboOfficeJFrame.getInstance().exit();
         return true;
      }
      return false;

   }

   @Override
   public void actionPerformed(ActionEvent e) {

      final JComponent source = (JComponent) e.getSource();

      TabManager.createTab(source.getName());



   }
}
