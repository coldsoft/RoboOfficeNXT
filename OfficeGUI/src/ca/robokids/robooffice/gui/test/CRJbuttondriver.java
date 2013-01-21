/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.robokids.robooffice.gui.test;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.studentmanagement.StudentDBM;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.desktop.tabs.students.components.ClassRecordJButton;
import ca.robokids.robooffice.desktop.tabs.students.components.StudentInfoPanel;
import ca.robokids.robooffice.entity.schoolmetadata.CourseSection;
import ca.robokids.robooffice.entity.schoolmetadata.DayOfWeek;
import ca.robokids.robooffice.entity.schoolmetadata.Project;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.entity.student.ClassRecord;
import ca.robokids.robooffice.entity.student.Sex;
import ca.robokids.robooffice.entity.student.Student;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import java.sql.Date;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class CRJbuttondriver extends javax.swing.JFrame {

    /** Creates new form CRJbuttondriver */
    public CRJbuttondriver() {

        initComponents();
        StudentInfoPanel studentinfo = new StudentInfoPanel();
        student.add(studentinfo);
      try {
         studentinfo.setStudent(StudentDBM.getStudentByID(2));
      } catch (DatabaseException ex) {
         Logger.getLogger(CRJbuttondriver.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        student = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        student.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        student.setLayout(new javax.swing.BoxLayout(student, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(student, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CRJbuttondriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CRJbuttondriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CRJbuttondriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CRJbuttondriver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CRJbuttondriver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel student;
    // End of variables declaration//GEN-END:variables

   private ClassRecord getSampleClassRecord() {
      ClassRecord cr = new ClassRecord();
      cr.setAttendDate(new Date(System.currentTimeMillis()));
      Timeslot time = new Timeslot();
      time.setDayOfWeek(DayOfWeek.Wed);
      time.setStart((new Time(System.currentTimeMillis())));
      CourseSection section = new CourseSection();
      section.setTimeslot(time);
      cr.setSection(section);
      Project p = new Project();
      p.setName("CSI");
      cr.setProject(p);
      cr.setComplete(true);
      cr.setTest(false);
      cr.setMakeupClass(true);
      cr.setOverdue(false);
      return cr;
   }

}
