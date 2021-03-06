/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.students.components;

import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.main.TabManager;
import ca.robokids.robooffice.desktop.tabs.students.StudentTab;
import ca.robokids.robooffice.entity.student.Student;

/**
 *
 * @author Coldsoft
 */
public class ActiveProgressesPanel extends javax.swing.JPanel {

   /**
    * Creates new form ActiveProgressesPanel
    */
   StudentInfoPanel studentInfo;
   Student student;
   StudentTab parent;
   public ActiveProgressesPanel(StudentTab parent) {
      initComponents();
      this.parent = parent;
      studentInfo = new StudentInfoPanel(this);
      pnlStudent.add(studentInfo);     
      
      
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
      studentInfo.setStudent(student);
      loadProgressTabs();
   }

   
   public void studenInfoUpdated()
   {
      String oldname = student.toString();
      
      this.student = studentInfo.getStudent();
      //if student is deleted.
      if (student == null){
         TabManager.closeTab(parent.getName());
         return;
      }
      
      if (!oldname.equals(student.toString()))
         parent.changeTabName(student.toString());
   }
   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlStudent = new javax.swing.JPanel();
        studentRecordPanel = new javax.swing.JPanel();
        progressTabbedPane = new javax.swing.JTabbedPane();
        btnMakePayment = new javax.swing.JButton();
        btnAbsent = new javax.swing.JButton();

        pnlStudent.setLayout(new javax.swing.BoxLayout(pnlStudent, javax.swing.BoxLayout.LINE_AXIS));

        btnMakePayment.setFont(FontsLoader.getButtonFont());
        btnMakePayment.setText("Make Payment");

        btnAbsent.setFont(FontsLoader.getButtonFont());
        btnAbsent.setText("Call-in Absent");

        javax.swing.GroupLayout studentRecordPanelLayout = new javax.swing.GroupLayout(studentRecordPanel);
        studentRecordPanel.setLayout(studentRecordPanelLayout);
        studentRecordPanelLayout.setHorizontalGroup(
            studentRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentRecordPanelLayout.createSequentialGroup()
                .addComponent(btnMakePayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addComponent(btnAbsent))
            .addComponent(progressTabbedPane)
        );
        studentRecordPanelLayout.setVerticalGroup(
            studentRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentRecordPanelLayout.createSequentialGroup()
                .addGroup(studentRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMakePayment)
                    .addComponent(btnAbsent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(studentRecordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(studentRecordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbsent;
    private javax.swing.JButton btnMakePayment;
    private javax.swing.JPanel pnlStudent;
    private javax.swing.JTabbedPane progressTabbedPane;
    private javax.swing.JPanel studentRecordPanel;
    // End of variables declaration//GEN-END:variables

   private void loadProgressTabs() {
         
   }
}
