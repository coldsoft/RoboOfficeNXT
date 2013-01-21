/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.school;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.desktop.tabs.school.components.CourseSettingPanel;
import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.main.TabManager;
import ca.robokids.robooffice.desktop.tabs.Tab;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import ca.robokids.robooffice.entity.schoolmetadata.Course;
import ca.robokids.robooffice.entity.schoolmetadata.DayOfWeek;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.logic.schoolsettings.SchoolManager;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Coldsoft
 */
public class CourseTab extends javax.swing.JPanel implements Tab{

   /**
    * Creates new form CourseTab
    */
   CourseSettingPanel coursePane;
   Course current;
   DefaultListModel<Course> courseModel = new DefaultListModel();
   int index;  
   boolean refreshing;
   boolean addCoursePrivilege;
   
   public CourseTab() {
      initComponents();
      coursePane = new CourseSettingPanel(this);
      try {
         coursePane.checkPrivilege();
         addCoursePrivilege = UserActivity.loginUserHasPrivilege("courseSettings");
         btnAdd.setEnabled(addCoursePrivilege);
      } catch (DatabaseException ex) {
         Logger.getLogger(CourseTab.class.getName()).log(Level.SEVERE, null, ex);
      }
      pnlCourse.add(coursePane);
      initialize();

   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        center = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCourses = new javax.swing.JList();
        btnReportType = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        pnlCourse = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(FontsLoader.getStaticLabelFont());
        jLabel1.setText("Courses");

        lstCourses.setFont(FontsLoader.getListFont());
        lstCourses.setModel(courseModel);
        lstCourses.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCoursesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstCourses);

        btnReportType.setFont(FontsLoader.getButtonFont());
        btnReportType.setText("Progress Report Types");
        btnReportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportTypeActionPerformed(evt);
            }
        });

        btnAdd.setFont(FontsLoader.getButtonFont());
        btnAdd.setText("Add");
        btnAdd.setName("courseSettings");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        pnlCourse.setLayout(new javax.swing.BoxLayout(pnlCourse, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout centerLayout = new javax.swing.GroupLayout(center);
        center.setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(centerLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addComponent(jScrollPane1)
                    .addComponent(btnReportType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        centerLayout.setVerticalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportType))
            .addComponent(pnlCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        add(center, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

   private void lstCoursesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCoursesValueChanged
      if (refreshing)
         return;
      index = lstCourses.getSelectedIndex();
      if (index > -1)
      {
         current = courseModel.elementAt(index);
         coursePane.setCourse(current);
         
      }
   }//GEN-LAST:event_lstCoursesValueChanged

   private void btnReportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportTypeActionPerformed
      TabManager.createTab("progressReportSettings");
   }//GEN-LAST:event_btnReportTypeActionPerformed

   private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
     lstCourses.clearSelection();
     btnAdd.setEnabled(false);
      coursePane.add();
//      try {
//         String name = PopupMessage.createInput("Enter the course name:", "Add Course");
//         if (name == null)
//            return;
//         Course newCourse = getSampleCourse();
//         newCourse.setName(name);
//         SchoolManager.addCourse(newCourse);
//         refresh();
//         PopupMessage.createInfo("New course "+ name + " is added! \nPlease edit the course info right now.", "Congratulations!");
//         for (int i = 0 ; i < courseModel.getSize(); i++)
//         {
//            if (courseModel.get(i).getName().equals(name)){
//               lstCourses.setSelectedIndex(i);
//               break;
//            }
//            
//         }
//         coursePane.setEditable(true);
//      } catch (DatabaseException ex) {
//         PopupMessage.createErrorPopUp(ex.getMessage(),null);
//      }
   }//GEN-LAST:event_btnAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReportType;
    private javax.swing.JPanel center;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstCourses;
    private javax.swing.JPanel pnlCourse;
    // End of variables declaration//GEN-END:variables

   @Override
   public void initialize() {
      refresh();
   }

   @Override
   public void refresh() {
      refreshing = true;
      btnAdd.setEnabled(addCoursePrivilege);
      courseModel.removeAllElements();
      try {
         List<Course> courses = SchoolManager.loadAllCourses();
         for (Course c : courses)
            courseModel.addElement(c);
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }
      refreshing = false;
      if (courseModel.getSize() > 0)
      {
         if (index > -1 && index < courseModel.getSize())
            lstCourses.setSelectedIndex(index);
         else
            lstCourses.setSelectedIndex(0);
      }
      coursePane.setEditable(false);
      
   }

   @Override
   public void setFocus() {
      
      this.btnAdd.requestFocus();
      
   }

   
}
