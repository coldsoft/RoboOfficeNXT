/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.main;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.desktop.loaders.ActionMappingLoader;
import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.loaders.FramePropertyLoader;
import ca.robokids.robooffice.desktop.tabs.system.SystemSettingsTab;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.logic.system.SystemManager;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import de.javasoft.swing.JYTabbedPane;
import java.awt.*;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JToolBar.Separator;

/**
 *
 * @author Coldsoft
 */
public class MainRoboOfficeJFrame extends javax.swing.JFrame {

   private static MainRoboOfficeJFrame self;
   private Separator buttonSpacing;
   private ActionListener actionListener = new TabManager();
   private boolean active;
   private LoginTab loginPanel = new LoginTab();
   private final static MouseAdapter mouseAdapter =
      new MouseAdapter() {
      };
   private ProgressGlassPane glassPane;

   /**
    * Creates new form MainRoboOfficeJFrame
    */
   public MainRoboOfficeJFrame() {
      initComponents();
      setGlassPane(glassPane = new ProgressGlassPane());
      setMenuItemsActionID();
      setTabbedPaneProperty();
      initFrameProperty();
      addCloseConfirmation();
      addButtons();
      setFrameInactive();


      loginPanel.focusTxtUser();

      TabManager.setContainer(tabbedPane);

      self = this;
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem6 = new javax.swing.JMenuItem();
        toolBar = new javax.swing.JToolBar();
        tabbedPane = new de.javasoft.swing.JYTabbedPane();
        mainMenuBar = new javax.swing.JMenuBar();
        accountMenu = new javax.swing.JMenu();
        inboxMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        accountMenuItem = new javax.swing.JMenuItem();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        logOutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        studentsMenu = new javax.swing.JMenu();
        viewAllStudentMenuItem = new javax.swing.JMenuItem();
        searchStudentMenuItem = new javax.swing.JMenuItem();
        reportAbsentMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        weeklyScheduleMenuItem = new javax.swing.JMenuItem();
        attendanceMenu = new javax.swing.JMenu();
        quickPrintMenuItem = new javax.swing.JMenuItem();
        printMenuItem = new javax.swing.JMenuItem();
        recordMenuItem = new javax.swing.JMenuItem();
        enterProgressMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        newStudentMenuItem = new javax.swing.JMenuItem();
        printAssessmentMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        studentStatMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        financeMenu = new javax.swing.JMenu();
        newPaymentMenuItem = new javax.swing.JMenuItem();
        overdueStudentMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        ARMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        hrMenu = new javax.swing.JMenu();
        payrollMenuItem = new javax.swing.JMenuItem();
        addUserMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        schoolMenu = new javax.swing.JMenu();
        courseSettingMenuItem = new javax.swing.JMenuItem();
        membershipSettingMenuItem = new javax.swing.JMenuItem();
        projectSettingMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        holidayMenuItem = new javax.swing.JMenuItem();
        classroomMenuItem = new javax.swing.JMenuItem();
        financeSettingMenuItem = new javax.swing.JMenuItem();
        userAccountMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        systemMenu = new javax.swing.JMenu();
        memoMenuItem = new javax.swing.JMenuItem();
        databaseBackupMenuItem = new javax.swing.JMenuItem();
        systemLogMenuItem = new javax.swing.JMenuItem();
        systemSettingMenuItem = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        reportIssueMenuItem = new javax.swing.JMenuItem();

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        tabbedPane.setFont(FontsLoader.getTabFont());
        tabbedPane.setTabHistoryEnabled(true);
        tabbedPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                tabbedPaneComponentResized(evt);
            }
        });
        tabbedPane.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabbedPaneComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tabbedPaneComponentRemoved(evt);
            }
        });

        accountMenu.setText("My Account");
        accountMenu.setFont(FontsLoader.getMenuFont());

        inboxMenuItem.setFont(FontsLoader.getMenuItemFont());
        inboxMenuItem.setText("My Inbox");
        accountMenu.add(inboxMenuItem);
        accountMenu.add(jSeparator2);

        accountMenuItem.setFont(FontsLoader.getMenuItemFont());
        accountMenuItem.setText("My Account");
        accountMenu.add(accountMenuItem);

        changePasswordMenuItem.setFont(FontsLoader.getMenuItemFont());
        changePasswordMenuItem.setText("Change Password");
        accountMenu.add(changePasswordMenuItem);
        accountMenu.add(jSeparator1);

        logOutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        logOutMenuItem.setFont(FontsLoader.getMenuItemFont());
        logOutMenuItem.setText("Log Out");
        accountMenu.add(logOutMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setFont(FontsLoader.getMenuItemFont());
        exitMenuItem.setText("Exit");
        accountMenu.add(exitMenuItem);

        mainMenuBar.add(accountMenu);

        jMenu1.setText(" ");
        jMenu1.setEnabled(false);
        mainMenuBar.add(jMenu1);

        studentsMenu.setText("Students");
        studentsMenu.setFont(FontsLoader.getMenuFont());

        viewAllStudentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        viewAllStudentMenuItem.setFont(FontsLoader.getMenuItemFont());
        viewAllStudentMenuItem.setText("View All Students");
        studentsMenu.add(viewAllStudentMenuItem);

        searchStudentMenuItem.setFont(FontsLoader.getMenuItemFont());
        searchStudentMenuItem.setText("Search Student");
        studentsMenu.add(searchStudentMenuItem);

        reportAbsentMenuItem.setFont(FontsLoader.getMenuItemFont());
        reportAbsentMenuItem.setText("Report Call-in Absent");
        studentsMenu.add(reportAbsentMenuItem);
        studentsMenu.add(jSeparator5);

        weeklyScheduleMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        weeklyScheduleMenuItem.setFont(FontsLoader.getMenuItemFont());
        weeklyScheduleMenuItem.setText("Weekly Schedule");
        studentsMenu.add(weeklyScheduleMenuItem);

        attendanceMenu.setText("Attendance");
        attendanceMenu.setFont(FontsLoader.getMenuItemFont());

        quickPrintMenuItem.setFont(FontsLoader.getMenuItemFont());
        quickPrintMenuItem.setText("Quick Print Today");
        attendanceMenu.add(quickPrintMenuItem);

        printMenuItem.setFont(FontsLoader.getMenuItemFont());
        printMenuItem.setText("Print...");
        attendanceMenu.add(printMenuItem);

        recordMenuItem.setFont(FontsLoader.getMenuItemFont());
        recordMenuItem.setText("Record...");
        attendanceMenu.add(recordMenuItem);

        studentsMenu.add(attendanceMenu);

        enterProgressMenuItem.setFont(FontsLoader.getMenuItemFont());
        enterProgressMenuItem.setText("Enter Progress Report");
        studentsMenu.add(enterProgressMenuItem);
        studentsMenu.add(jSeparator3);

        newStudentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newStudentMenuItem.setFont(FontsLoader.getMenuItemFont());
        newStudentMenuItem.setText("New Student Registration");
        studentsMenu.add(newStudentMenuItem);

        printAssessmentMenuItem.setFont(FontsLoader.getMenuItemFont());
        printAssessmentMenuItem.setText("Print Assessment Sheet");
        studentsMenu.add(printAssessmentMenuItem);
        studentsMenu.add(jSeparator4);

        studentStatMenuItem.setFont(FontsLoader.getMenuItemFont());
        studentStatMenuItem.setText("Students Statistic");
        studentsMenu.add(studentStatMenuItem);

        mainMenuBar.add(studentsMenu);

        jMenu2.setText(" ");
        jMenu2.setEnabled(false);
        mainMenuBar.add(jMenu2);

        financeMenu.setText("Finance");
        financeMenu.setFont(FontsLoader.getMenuFont());

        newPaymentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        newPaymentMenuItem.setFont(FontsLoader.getMenuItemFont());
        newPaymentMenuItem.setText("New Payment");
        financeMenu.add(newPaymentMenuItem);

        overdueStudentMenuItem.setFont(FontsLoader.getMenuItemFont());
        overdueStudentMenuItem.setText("Fee Overdue Students");
        financeMenu.add(overdueStudentMenuItem);
        financeMenu.add(jSeparator6);

        ARMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        ARMenuItem.setFont(FontsLoader.getMenuItemFont());
        ARMenuItem.setText("Account Receivable");
        financeMenu.add(ARMenuItem);

        mainMenuBar.add(financeMenu);

        jMenu3.setText(" ");
        jMenu3.setEnabled(false);
        mainMenuBar.add(jMenu3);

        hrMenu.setText("Human resources");
        hrMenu.setFont(FontsLoader.getMenuFont());

        payrollMenuItem.setFont(FontsLoader.getMenuItemFont());
        payrollMenuItem.setText("Payroll");
        hrMenu.add(payrollMenuItem);

        addUserMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        addUserMenuItem.setFont(FontsLoader.getMenuItemFont());
        addUserMenuItem.setText("Add Staff...");
        hrMenu.add(addUserMenuItem);

        mainMenuBar.add(hrMenu);

        jMenu4.setText(" ");
        jMenu4.setEnabled(false);
        mainMenuBar.add(jMenu4);

        schoolMenu.setText("School administration");
        schoolMenu.setFont(FontsLoader.getMenuFont());

        courseSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        courseSettingMenuItem.setFont(FontsLoader.getMenuItemFont());
        courseSettingMenuItem.setText("Courses...");
        schoolMenu.add(courseSettingMenuItem);

        membershipSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        membershipSettingMenuItem.setFont(FontsLoader.getMenuItemFont());
        membershipSettingMenuItem.setText("Memberships...");
        schoolMenu.add(membershipSettingMenuItem);

        projectSettingMenuItem.setFont(FontsLoader.getMenuItemFont());
        projectSettingMenuItem.setText("Projects...");
        schoolMenu.add(projectSettingMenuItem);
        schoolMenu.add(jSeparator7);

        holidayMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        holidayMenuItem.setFont(FontsLoader.getMenuItemFont());
        holidayMenuItem.setText("Holiday Setting");
        schoolMenu.add(holidayMenuItem);

        classroomMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        classroomMenuItem.setFont(FontsLoader.getMenuItemFont());
        classroomMenuItem.setText("Classroom Setting");
        schoolMenu.add(classroomMenuItem);

        financeSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        financeSettingMenuItem.setFont(FontsLoader.getMenuItemFont());
        financeSettingMenuItem.setText("Finance Settings");
        schoolMenu.add(financeSettingMenuItem);

        userAccountMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        userAccountMenuItem.setFont(FontsLoader.getMenuItemFont());
        userAccountMenuItem.setText("User Account Settings");
        schoolMenu.add(userAccountMenuItem);

        mainMenuBar.add(schoolMenu);

        jMenu5.setText(" ");
        jMenu5.setEnabled(false);
        mainMenuBar.add(jMenu5);

        systemMenu.setText("System");
        systemMenu.setFont(FontsLoader.getMenuFont());

        memoMenuItem.setFont(FontsLoader.getMenuItemFont());
        memoMenuItem.setText("Memos/Tasks Management");
        systemMenu.add(memoMenuItem);

        databaseBackupMenuItem.setFont(FontsLoader.getMenuItemFont());
        databaseBackupMenuItem.setText("Database Backup...");
        systemMenu.add(databaseBackupMenuItem);

        systemLogMenuItem.setFont(FontsLoader.getMenuItemFont());
        systemLogMenuItem.setText("System Logs");
        systemMenu.add(systemLogMenuItem);

        systemSettingMenuItem.setFont(FontsLoader.getMenuItemFont());
        systemSettingMenuItem.setText("System Settings");
        systemMenu.add(systemSettingMenuItem);

        mainMenuBar.add(systemMenu);

        jMenu6.setText(" ");
        jMenu6.setEnabled(false);
        mainMenuBar.add(jMenu6);

        helpMenu.setText("Help");
        helpMenu.setFont(FontsLoader.getMenuFont());

        reportIssueMenuItem.setFont(FontsLoader.getMenuItemFont());
        reportIssueMenuItem.setText("Report Issue...");
        helpMenu.add(reportIssueMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      this.setLocationRelativeTo(null);
   }//GEN-LAST:event_formWindowOpened

   private void tabbedPaneComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabbedPaneComponentAdded
      resize();
   }//GEN-LAST:event_tabbedPaneComponentAdded

   private void tabbedPaneComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabbedPaneComponentRemoved
      resize();
   }//GEN-LAST:event_tabbedPaneComponentRemoved

   private void tabbedPaneComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tabbedPaneComponentResized
      resize();
   }//GEN-LAST:event_tabbedPaneComponentResized

   private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
      resize();
   }//GEN-LAST:event_formComponentResized

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      
      try {
         UIManager.put("Synthetica.rootPane.titlePane.title.center", Boolean.TRUE);
         UIManager.put("Synthetica.tabbedPane.tab.selected.bold", Boolean.TRUE);
         UIManager.put("Synthetica.rootPane.titlePane.dropShadow", Boolean.TRUE);
         UIManager.put("Synthetica.rootPane.titlePane.gap", 5);
         

         javax.swing.UIManager.setLookAndFeel(FramePropertyLoader.getLookAndFeel());
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(MainRoboOfficeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(MainRoboOfficeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(MainRoboOfficeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(MainRoboOfficeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }

      try {
         SystemManager.startup();
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }
      /*
       * Create and display the form
       */
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            new MainRoboOfficeJFrame().setVisible(true);
         }
      });
   }

   public static MainRoboOfficeJFrame getInstance() {
      return self;
   }

   private void addCloseConfirmation() {
      this.addWindowListener(new WindowAdapter() {

         @Override
         public void windowClosing(WindowEvent e) {
            if (UserActivity.isLogin() && PopupMessage.createConfirmPopUp("Do you want to logoff and exit?", "User Confirmation")) {
               MainRoboOfficeJFrame.getInstance().exit();
            }
            if (!UserActivity.isLogin() && PopupMessage.createConfirmPopUp("Do you want to exit?", "User Confirmation")) {
               System.exit(0);
            }
         }
      });
   }

   //Set Main Frame Title, size and LookAndFeel
   private void initFrameProperty() {


      setTitle(FramePropertyLoader.getFrameTitle());

      //Set minimum width and height
      Dimension size = new Dimension(FramePropertyLoader.getFrameWidth(), FramePropertyLoader.getFrameHeight());
      this.setMinimumSize(size);
      this.setPreferredSize(size);

      // Get the current screen size
      // Set frame in the middle of the screen
      //Toolkit toolkit = Toolkit.getDefaultToolkit();
      //Dimension scrnsize = toolkit.getScreenSize();
      //this.setLocation((scrnsize.width - size.width) / 2, (scrnsize.height - size.height) / 2);

      //set Icon Image
      try {
         //URL url = this.getClass().getResource("/Interface/desktop/resources/images/CletterChocoIcon.png");
         //setIconImage(new ImageIcon(url).getImage());
         setIconImage(FramePropertyLoader.getImageIcon("frameIcon").getImage());
         //image = ImageIO.read(this.getClass().getResourceAsStream("/resource/RoboLogo.jpg"));


      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private void setMenuItemsActionID() {
      //For every menu in menu bar
      for (int i = 0; i < mainMenuBar.getMenuCount(); i++) {
         JMenu menu = (JMenu) mainMenuBar.getMenu(i);

         //for every menu item or submenu within each menu
         for (int j = 0; j < menu.getMenuComponentCount(); j++) {
            JComponent component = menu.getItem(j);
            //add menu item action id
            if (component instanceof JMenuItem) {

               JMenuItem item = (JMenuItem) component;
               item.setName(ActionMappingLoader.getActionIDByMenuItemText(item.getText()));
               item.addActionListener(actionListener);
            }
            //or search sub menu for more menu items
            if (component instanceof JMenu) {
               JMenu submenu = (JMenu) component;
               submenu.setName(ActionMappingLoader.getActionIDByMenuItemText(submenu.getText()));
               for (int k = 0; k < submenu.getMenuComponentCount(); k++) {
                  JComponent submenuitem = submenu.getItem(k);
                  if (component instanceof JMenuItem) {
                     JMenuItem item = (JMenuItem) submenuitem;
                     System.out.println(item.getText());
                     item.setName(ActionMappingLoader.getActionIDByMenuItemText(item.getText()));
                     item.addActionListener(actionListener);
                  }

               }
            }
         }
      }
   }

   private void setTabbedPaneProperty() {
      tabbedPane.setTabHistoryEnabled(true);
      tabbedPane.setCloseButtonStrategy(JYTabbedPane.CloseButtonStrategy.SELECTED_TAB);
      tabbedPane.setTabReorderByDraggingEnabled(true);
      tabbedPane.setTabsStretchedEnabled(false);
      tabbedPane.setMinimumTabSize(FramePropertyLoader.getMinimumTabSize());
      tabbedPane.setPaintSelectedTabBold(true);
      tabbedPane.setFont(FontsLoader.getTabFont());
      tabbedPane.getContentPanel().addComponentListener(new java.awt.event.ComponentAdapter() {

         public void componentResized(java.awt.event.ComponentEvent evt) {
            tabbedPaneComponentResized(evt);
         }
      });
      tabbedPane.getContentPanel().addContainerListener(new java.awt.event.ContainerAdapter() {

         public void componentAdded(java.awt.event.ContainerEvent evt) {
            tabbedPaneComponentAdded(evt);
         }

         public void componentRemoved(java.awt.event.ContainerEvent evt) {
            tabbedPaneComponentRemoved(evt);
         }
      });

   }

   private void resize() {
      TabManager.resizeTab(this.getWidth());
      Dimension dim = new Dimension(this.getSize().width - getButtonSpacing(), 0);
      buttonSpacing.setSeparatorSize(dim);
      this.revalidate();

   }

   public void exit() {
      logout();
      dispose();
      System.exit(0);
   }

   public boolean getActive() {
      return active;
   }

   /**
    * Log out current user, if applicable.System interface inactive Login page
    * is displayed.Send a logout message to controller layer.
    */
   public void logout() {
      try {
         UserActivity.logout();
      } catch (DatabaseException ex) {
         System.out.println("Log out failed due to database error in main frame, logout(). " + ex.getMessage());
      }
      this.setFrameInactive();
   }

   private void setFrameInactive() {
      this.active = false;
      setMenuInactive();
      tabbedPane.removeAll();
      loginPanel.reset();
      tabbedPane.setCloseButtonStrategy(JYTabbedPane.CloseButtonStrategy.NONE);
      tabbedPane.add("Login", loginPanel);
      EnableButtons(false);
   }

   public void login(User user) {
      if (user == null) {
         return;
      }
      this.active = true;
      setMenuActive(user);

      try {
         setPrivilege();
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }
      //EnableButtons(true);
      tabbedPane.removeAll();
      this.mainMenuBar.repaint();
      this.toolBar.repaint();


   }

   private void setMenuActive(User user) {
      accountMenu.setText(user.getFirstName() + " " + user.getLastName());
      tabbedPane.setCloseButtonStrategy(JYTabbedPane.CloseButtonStrategy.ALL_TABS);
   }

   private void setMenuInactive() {
      enableMenu(false);
      accountMenu.setText("User");
   }

   private void enableMenu(boolean enable) {
      accountMenu.setEnabled(enable);
      studentsMenu.setEnabled(enable);
      financeMenu.setEnabled(enable);
      hrMenu.setEnabled(enable);
      schoolMenu.setEnabled(enable);
      systemMenu.setEnabled(enable);
      mainMenuBar.repaint();
   }

   public static void setBusy(boolean busy) {
      if (busy) {
         self.glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
         self.glassPane.addMouseListener(mouseAdapter);
         
         self.glassPane.setVisible(true);

      } else {
         self.glassPane.setCursor(Cursor.getDefaultCursor());
         self.glassPane.removeMouseListener(mouseAdapter);
         self.glassPane.setVisible(false);

      }
   }
      public void restart() {
      try {
         final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
         final File currentJar = new File(MainRoboOfficeJFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI());

         /*
          * is it a jar file?
          */
         if (!currentJar.getName().endsWith(".jar")) {
            return;
         }

         /*
          * Build command: java -jar application.jar
          */
         final ArrayList<String> command = new ArrayList<String>();
         command.add(javaBin);
         command.add("-jar");
         command.add(currentJar.getPath());

         final ProcessBuilder builder = new ProcessBuilder(command);
         builder.start();
         exit();
      } catch (URISyntaxException | IOException ex) {
         Logger.getLogger(SystemSettingsTab.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ARMenuItem;
    private javax.swing.JMenu accountMenu;
    private javax.swing.JMenuItem accountMenuItem;
    private javax.swing.JMenuItem addUserMenuItem;
    private javax.swing.JMenu attendanceMenu;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private javax.swing.JMenuItem classroomMenuItem;
    private javax.swing.JMenuItem courseSettingMenuItem;
    private javax.swing.JMenuItem databaseBackupMenuItem;
    private javax.swing.JMenuItem enterProgressMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu financeMenu;
    private javax.swing.JMenuItem financeSettingMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem holidayMenuItem;
    private javax.swing.JMenu hrMenu;
    private javax.swing.JMenuItem inboxMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JMenuItem logOutMenuItem;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem membershipSettingMenuItem;
    private javax.swing.JMenuItem memoMenuItem;
    private javax.swing.JMenuItem newPaymentMenuItem;
    private javax.swing.JMenuItem newStudentMenuItem;
    private javax.swing.JMenuItem overdueStudentMenuItem;
    private javax.swing.JMenuItem payrollMenuItem;
    private javax.swing.JMenuItem printAssessmentMenuItem;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JMenuItem projectSettingMenuItem;
    private javax.swing.JMenuItem quickPrintMenuItem;
    private javax.swing.JMenuItem recordMenuItem;
    private javax.swing.JMenuItem reportAbsentMenuItem;
    private javax.swing.JMenuItem reportIssueMenuItem;
    private javax.swing.JMenu schoolMenu;
    private javax.swing.JMenuItem searchStudentMenuItem;
    private javax.swing.JMenuItem studentStatMenuItem;
    private javax.swing.JMenu studentsMenu;
    private javax.swing.JMenuItem systemLogMenuItem;
    private javax.swing.JMenu systemMenu;
    private javax.swing.JMenuItem systemSettingMenuItem;
    private de.javasoft.swing.JYTabbedPane tabbedPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JMenuItem userAccountMenuItem;
    private javax.swing.JMenuItem viewAllStudentMenuItem;
    private javax.swing.JMenuItem weeklyScheduleMenuItem;
    // End of variables declaration//GEN-END:variables

   public void setUserMenu(User temp) {
      setMenuActive(temp);
   }

   private void setPrivilege() throws DatabaseException {
      //For every menu in menu bar
      for (int i = 0; i < mainMenuBar.getMenuCount(); i++) {
         JMenu menu = (JMenu) mainMenuBar.getMenu(i);
         boolean disabled = false;
         //for every menu item or submenu within each menu
         for (int j = 0; j < menu.getMenuComponentCount(); j++) {
            JComponent component = menu.getItem(j);
            if (component instanceof JMenuItem) {
               JMenuItem item = (JMenuItem) component;
               item.setEnabled(UserActivity.loginUserHasPrivilege(item.getName()));
               disabled |= item.isEnabled();
            }
            //or search sub menu for more menu items
            if (component instanceof JMenu) {
               JMenu submenu = (JMenu) component;

               for (int k = 0; k < submenu.getMenuComponentCount(); k++) {
                  JComponent submenuitem = submenu.getItem(k);
                  if (component instanceof JMenuItem) {
                     JMenuItem item = (JMenuItem) submenuitem;
                     item.setEnabled(UserActivity.loginUserHasPrivilege(item.getName()));
                  }

               }
            }
         }
         //If every menus Items in a menu is disabled, then disable the menu
         menu.setEnabled(disabled);
      }
      //setButton privilege
      Component[] buttons = toolBar.getComponents();
      for (int i = 0; i < buttons.length; i++) {
         if (!(buttons[i] instanceof JButton)) {
            continue;
         }
         String action = buttons[i].getName();
         System.out.println(action);
         if (action.equals("quickPrintAttendance") || action.equals("printAttendance") || action.equals("recordAttendance")) {
            action = "attendance";
         }
         buttons[i].setEnabled(UserActivity.loginUserHasPrivilege(action));
      }
   }

   private void addButtons() {
      toolBar.add(getButton("viewAllStudents", this.actionListener));
      toolBar.add(getButton("weeklySchedule", this.actionListener));
      toolBar.add(new Separator());
      toolBar.add(getButton("printAttendance", this.actionListener));
      toolBar.add(getButton("recordAttendance", this.actionListener));
      toolBar.add(getButton("newStudent", this.actionListener));
      toolBar.add(new Separator());
      toolBar.add(getButton("newPayment", this.actionListener));
      toolBar.add(getButton("accountReceivable", this.actionListener));
      toolBar.add(new Separator());
      toolBar.add(getButton("enterProgressReport", this.actionListener));


      Dimension dim = new Dimension(this.getMinimumSize().width - getButtonSpacing(), 0);
      buttonSpacing = new Separator(dim);
      toolBar.add(buttonSpacing);
      toolBar.add(getButton("logOut", this.actionListener));


   }

   private int getButtonSpacing() {
      int BUTTON_SIZE = 72;
      int count = 9;
      return count * BUTTON_SIZE;
   }

   private JButton getButton(String actionID, ActionListener listener) {
      JButton btn = new JButton();
      btn.setFont(FontsLoader.getShortCutButtonFont());
      btn.setName(actionID);
      btn.addActionListener(listener);
      btn.setIcon(ActionMappingLoader.getImageFileName(actionID));
      btn.setToolTipText(ActionMappingLoader.getTabName(actionID));
      return btn;
   }

   private void EnableButtons(boolean enable) {
      Component[] buttons = toolBar.getComponents();
      for (int i = 0; i < buttons.length; i++) {
         buttons[i].setEnabled(enable);
      }
      // btnLogoff.setEnabled(enable);
   }
}

/**
 *
 * @author Romain Guy
 */
class ProgressGlassPane extends JComponent {
    private static int BAR_WIDTH = 250;
    private static final int BAR_HEIGHT = 10;
    
    private static final Color TEXT_COLOR = new Color(250,102,0);
    private static final Color BORDER_COLOR = new Color(0x333333);
    
    private static final float[] GRADIENT_FRACTIONS = new float[] {
        0.0f, 0.499f, 0.5f, 1.0f
    };
    private static final Color[] GRADIENT_COLORS = new Color[] {
        Color.GRAY, Color.DARK_GRAY, Color.BLACK, Color.GRAY
    };
    private static final Color GRADIENT_COLOR2 = Color.WHITE;
    private static final Color GRADIENT_COLOR1 = Color.GRAY;

    private String message = FramePropertyLoader.getLoadingMessage();
    private int progress = 0;
    
    public ProgressGlassPane() {
        setBackground(Color.WHITE);
        setFont(new Font("宋体", Font.BOLD, 14)); 
        BAR_WIDTH = message.length() * 10;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        int oldProgress = this.progress;
        this.progress = progress;
        
        // computes the damaged area
        FontMetrics metrics = getGraphics().getFontMetrics(getFont()); 
        int w = (int) (BAR_WIDTH * ((float) oldProgress / 100.0f));
        int x = w + (getWidth() - BAR_WIDTH) / 2;
        int y = (getHeight() - BAR_HEIGHT) / 2;
        y += metrics.getDescent() / 2;
        
        w = (int) (BAR_WIDTH * ((float) progress / 100.0f)) - w;
        int h = BAR_HEIGHT;
        
        repaint(x, y, w, h);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        // enables anti-aliasing
        Graphics2D g2 = (Graphics2D) g; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        // gets the current clipping area
        Rectangle clip = g.getClipBounds();
        
        // sets a 65% translucent composite
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.87f);
        Composite composite = g2.getComposite();
        g2.setComposite(alpha);
        
        // fills the background
        g2.setColor(getBackground());
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
        FontMetrics metrics = g.getFontMetrics();   
        int x = (getWidth() - BAR_WIDTH) / 2;
        int y = (getHeight() - BAR_HEIGHT - metrics.getDescent()) / 2;
//        // centers the progress bar on screen
//             
//        
//        // set background for progress bar and text
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, BAR_WIDTH+100, BAR_HEIGHT+100);
        // draws the text
        g2.setColor(TEXT_COLOR);       
        drawString(g2,message, x, y);
        
//        // goes to the position of the progress bar
//        y += metrics.getDescent();
//        
//        // computes the size of the progress indicator
//        int w = (int) (BAR_WIDTH * ((float) progress / 100.0f));
//        int h = BAR_HEIGHT;
//        
//        // draws the content of the progress bar
//        Paint paint = g2.getPaint();
//        
//        // bar's background
//        Paint gradient = new GradientPaint(x, y, GRADIENT_COLOR1,
//                x, y + h, GRADIENT_COLOR2);
//        g2.setPaint(gradient);
//        g2.fillRect(x, y, BAR_WIDTH, BAR_HEIGHT);
//        
//        // actual progress
//        gradient = new LinearGradientPaint(x, y, x, y + h,
//                GRADIENT_FRACTIONS, GRADIENT_COLORS);
//        g2.setPaint(gradient);
//        g2.fillRect(x, y, w, h);
//        
//        g2.setPaint(paint);
//        
//        // draws the progress bar border
//        g2.drawRect(x, y, BAR_WIDTH, BAR_HEIGHT);
        
        g2.setComposite(composite);
    }
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}