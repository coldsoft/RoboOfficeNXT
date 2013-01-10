/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.customSwing;

import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.student.Progress;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class AttendancePanel extends javax.swing.JPanel {

   /**
    * Creates new form AttendancePanel
    */
   
   List<ClassroomPanel> classroomPanels = new ArrayList();

   public AttendancePanel() {
      initComponents();
     
   }

   public void addClassroom(Classroom classroom, List<Progress> progresses,boolean selected)
   {
      
      ClassroomPanel cp = new ClassroomPanel(classroom, progresses, selected);
      cp.setAlignmentX(Component.LEFT_ALIGNMENT);
      cp.setAlignmentY(Component.TOP_ALIGNMENT);
      classroomPanels.add(cp);
      this.classroomContainer.add(cp);
   }
   public List<Progress> getAllSelectedProgress()
   {
      List<Progress> all = new ArrayList();
      for (ClassroomPanel cp : classroomPanels)
      {
         List<Progress> progress = cp.getProgress();
         all.addAll(progress);
      }
      return all;
   }
   public List<Progress> getSelectedProgress(Classroom c)
   {
      for (ClassroomPanel cp : classroomPanels)
      {
         Classroom classroom = cp.getClassroom();
         if (c.getClassroom_id() == classroom.getClassroom_id()) {
            return cp.getProgress();
         }
      }
      return new ArrayList();
   }
   public void reset()
   {
      classroomPanels.clear();
      this.classroomContainer.removeAll();
      this.classroomContainer.revalidate();
   }
   
   
   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        classroomContainer = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(10000, 10000));
        setMinimumSize(new java.awt.Dimension(900, 500));

        jScrollPane1.setBorder(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(900, 450));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(900, 450));

        classroomContainer.setLayout(new javax.swing.BoxLayout(classroomContainer, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(classroomContainer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel classroomContainer;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
