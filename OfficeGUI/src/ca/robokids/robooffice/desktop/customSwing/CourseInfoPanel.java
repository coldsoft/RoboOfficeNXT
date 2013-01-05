/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.customSwing;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.exception.DuplicateNameException;
import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.main.MainRoboOfficeJFrame;
import ca.robokids.robooffice.desktop.main.TabManager;
import ca.robokids.robooffice.desktop.tabs.school.CourseTab;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.schoolmetadata.Course;
import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.logic.schoolsettings.SchoolManager;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Coldsoft
 */
public class CourseInfoPanel extends javax.swing.JPanel {

   /**
    * Creates new form CourseInfoPanel
    */
   DefaultListModel<Timeslot> slots = new DefaultListModel();
   List<Timeslot> timeslots = new ArrayList();
   DefaultComboBoxModel<Classroom> classroomModel = new DefaultComboBoxModel();
   DefaultComboBoxModel<ProgressReportType> reportTypeModel = new DefaultComboBoxModel();
   CourseTab parent;
   Course course;
   boolean editable;
   TimeslotDialog selectTime;

   public CourseInfoPanel(CourseTab parent) {
      initComponents();
      selectTime = new TimeslotDialog(MainRoboOfficeJFrame.getInstance(), true);
      loadPanel();
      this.parent = parent;
      btnEdit.setEnabled(false);
      btnDelete.setEnabled(false);
      btnAdd.setEnabled(false);
      btnDeleteTime.setEnabled(false);
      txtCode.setLimit(4);
   }

   public void setCourse(Course c) {
      course = c;
      if (c == null) {
         btnEdit.setEnabled(false);
         btnDelete.setEnabled(false);
         btnAdd.setEnabled(false);
         btnDeleteTime.setEnabled(false);
      } else {
         populateFields();
         btnEdit.setEnabled(true);
         btnDelete.setEnabled(true);
         btnAdd.setEnabled(true);
         btnDeleteTime.setEnabled(true);
      }
   }

   public Course getCourse() {
      if (!editable) {
         return course;
      } else {
         Course newCourse = getFields();
         newCourse.setId(course.getId());
         return newCourse;
      }
   }

   public void setEditable(boolean edit) {
      editable = edit;
      if (edit) {
         switchTo("edit");
      } else {
         switchTo("display");
      }
   }

   public void add() {
      course = null;
      this.reset();
      switchTo("edit");
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        postalCodeJTextField1 = new ca.robokids.robooffice.desktop.customSwing.PostalCodeJTextField();
        information = new javax.swing.JPanel();
        display = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblCode = new javax.swing.JLabel();
        lblRate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblDescription = new javax.swing.JTextArea();
        lblClassroom = new javax.swing.JLabel();
        lblProgressReportType = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        edit = new javax.swing.JPanel();
        cboReportType = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblName1 = new javax.swing.JLabel();
        lblCode1 = new javax.swing.JLabel();
        cboClassrooms = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtRate = new ca.robokids.robooffice.desktop.customSwing.CurrencyJTextField();
        txtDuration = new ca.robokids.robooffice.desktop.customSwing.IntegerNumberJTextField();
        txtName = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        txtCode = new ca.robokids.robooffice.desktop.customSwing.PostalCodeJTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstTimeslots = new javax.swing.JList();
        btnAdd = new javax.swing.JButton();
        btnDeleteTime = new javax.swing.JButton();

        postalCodeJTextField1.setText("postalCodeJTextField1");

        information.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(FontsLoader.getStaticLabelFont());
        jLabel1.setText("Course Duration(min):");

        jLabel5.setFont(FontsLoader.getStaticLabelFont());
        jLabel5.setText("Progress Report Type:");

        lblDuration.setFont(FontsLoader.getDynamicLabelFont());
        lblDuration.setText("N/A");

        jLabel3.setFont(FontsLoader.getStaticLabelFont());
        jLabel3.setText("Classroom");

        lblName.setFont(FontsLoader.getBigStaticLabelFont());
        lblName.setText("Course Name");

        lblCode.setFont(FontsLoader.getBigStaticLabelFont());
        lblCode.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCode.setText("Course Code");

        lblRate.setFont(FontsLoader.getDynamicLabelFont());
        lblRate.setText("N/A");

        jLabel2.setFont(FontsLoader.getStaticLabelFont());
        jLabel2.setText("Rate:");

        jLabel7.setFont(FontsLoader.getStaticLabelFont());
        jLabel7.setText("Description:");

        lblDescription.setColumns(20);
        lblDescription.setEditable(false);
        lblDescription.setFont(FontsLoader.getTextFieldFont());
        lblDescription.setLineWrap(true);
        lblDescription.setRows(5);
        lblDescription.setEnabled(false);
        jScrollPane2.setViewportView(lblDescription);

        lblClassroom.setFont(FontsLoader.getDynamicLabelFont());
        lblClassroom.setText("N/A");

        lblProgressReportType.setFont(FontsLoader.getDynamicLabelFont());
        lblProgressReportType.setText("N/A");

        btnEdit.setFont(FontsLoader.getButtonFont());
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(FontsLoader.getButtonFont());
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayLayout = new javax.swing.GroupLayout(display);
        display.setLayout(displayLayout);
        displayLayout.setHorizontalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayLayout.createSequentialGroup()
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(displayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProgressReportType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClassroom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(displayLayout.createSequentialGroup()
                                .addComponent(lblDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 198, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayLayout.createSequentialGroup()
                        .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        displayLayout.setVerticalGroup(
            displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(lblCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDuration))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRate)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblClassroom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProgressReportType)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap())
        );

        information.add(display, "display");

        cboReportType.setFont(FontsLoader.getComboBoxFont());
        cboReportType.setModel(this.reportTypeModel);

        jLabel8.setFont(FontsLoader.getStaticLabelFont());
        jLabel8.setText("Course Duration(min):");

        jLabel9.setFont(FontsLoader.getStaticLabelFont());
        jLabel9.setText("Progress Report Type:");

        jLabel10.setFont(FontsLoader.getStaticLabelFont());
        jLabel10.setText("Classroom");

        lblName1.setFont(FontsLoader.getStaticLabelFont());
        lblName1.setText("Course Name:");

        lblCode1.setFont(FontsLoader.getStaticLabelFont());
        lblCode1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCode1.setText("Course Code:");

        cboClassrooms.setFont(FontsLoader.getComboBoxFont());
        cboClassrooms.setModel(this.classroomModel);

        jLabel12.setFont(FontsLoader.getStaticLabelFont());
        jLabel12.setText("Rate: $");

        jLabel13.setFont(FontsLoader.getStaticLabelFont());
        jLabel13.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setFont(FontsLoader.getTextFieldFont());
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        jScrollPane3.setViewportView(txtDescription);

        txtRate.setFont(FontsLoader.getTextFieldFont());

        txtDuration.setFont(FontsLoader.getTextFieldFont());

        txtName.setFont(FontsLoader.getTextFieldFont());

        btnSave.setFont(FontsLoader.getButtonFont());
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setFont(FontsLoader.getButtonFont());
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblMsg.setFont(FontsLoader.getErrorLabelFont());
        lblMsg.setForeground(new java.awt.Color(255, 0, 0));

        txtCode.setColumns(4);
        txtCode.setFont(FontsLoader.getTextFieldFont());

        javax.swing.GroupLayout editLayout = new javax.swing.GroupLayout(edit);
        edit.setLayout(editLayout);
        editLayout.setHorizontalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCode1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                .addComponent(txtDuration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(editLayout.createSequentialGroup()
                        .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cboClassrooms, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboReportType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(editLayout.createSequentialGroup()
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addGroup(editLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)
                        .addGap(0, 342, Short.MAX_VALUE))
                    .addGroup(editLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        editLayout.setVerticalGroup(
            editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCode1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboClassrooms, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboReportType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        information.add(edit, "edit");

        jLabel14.setFont(FontsLoader.getStaticLabelFont());
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("<html>Course Timeslots<br>(Start time)");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lstTimeslots.setFont(FontsLoader.getBigListFont());
        lstTimeslots.setModel(this.slots);
        jScrollPane4.setViewportView(lstTimeslots);

        btnAdd.setFont(FontsLoader.getButtonFont());
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDeleteTime.setFont(FontsLoader.getButtonFont());
        btnDeleteTime.setForeground(new java.awt.Color(255, 0, 0));
        btnDeleteTime.setText("Delete");
        btnDeleteTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(information, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDeleteTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteTime))
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(information, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

   private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

      Course newCourse = getFields();
      //if save new Course
      if (course == null) {
         try {
            SchoolManager.createCourse(newCourse);
            parent.refresh();
         } catch (DatabaseException ex) {
            PopupMessage.createErrorPopUp(ex.getMessage(), null);
         } catch(BadFieldException ex)
         {
            lblMsg.setText(ex.getMessage());
         }
      } else {

         newCourse.setId(course.getId());
         try {
            SchoolManager.modifyCourse(newCourse);
            parent.refresh();
         } catch (DatabaseException ex) {
            PopupMessage.createErrorPopUp(ex.getMessage(), null);
         }
      }

   }//GEN-LAST:event_btnSaveActionPerformed

   private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
      setEditable(true);
   }//GEN-LAST:event_btnEditActionPerformed

   private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
      if (PopupMessage.createDelete(course.toString())) {
         try {
            if (SchoolManager.deleteCourse(course)) {
               PopupMessage.createInfo(course + " is deleted.", "Complete");
               this.reset();
               parent.refresh();
            } else {
               PopupMessage.createInfo("Cannot Delete.Course is already deleted.", null);
            }
         } catch (DatabaseException ex) {
            PopupMessage.createErrorPopUp(ex.getMessage(), "Database Error");
         }
      }

   }//GEN-LAST:event_btnDeleteActionPerformed

   private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      try {
         selectTime.setVisible(true);
         selectTime.setLocationRelativeTo(MainRoboOfficeJFrame.getInstance());

         Timeslot newTime = selectTime.getTimeslot();
         if (newTime == null)
            return;
         SchoolManager.addCourseSection(course, newTime.getDayOfWeek(), newTime.getStart());
         this.timeslots.add(newTime);
         this.slots.addElement(newTime);
      } catch (DuplicateNameException ex) {
         PopupMessage.createErrorPopUp("The new timeslot already existed.", "Sorry");
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }

   }//GEN-LAST:event_btnAddActionPerformed

   private void btnDeleteTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTimeActionPerformed
      int index = lstTimeslots.getSelectedIndex();
      if (index > -1) {
         if (slots.getSize() == 1) {
            PopupMessage.createErrorPopUp("Course must have at least 1 timeslot assigned to.\nTry adding a new timeslot, then delete this one.", "Cannot delete");
            return;
         }
         try {
            Timeslot delete = slots.elementAt(index);
            SchoolManager.deleteCourseSection(course, delete);
            course.getTimeslots().remove(delete);
            slots.remove(index);

         } catch (DatabaseException ex) {
            PopupMessage.createErrorPopUp(ex.getMessage(), null);
         }
      }
   }//GEN-LAST:event_btnDeleteTimeActionPerformed

   private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
      switchTo("display");
      parent.refresh();
   }//GEN-LAST:event_btnCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteTime;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboClassrooms;
    private javax.swing.JComboBox cboReportType;
    private javax.swing.JPanel display;
    private javax.swing.JPanel edit;
    private javax.swing.JPanel information;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblClassroom;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblCode1;
    private javax.swing.JTextArea lblDescription;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblProgressReportType;
    private javax.swing.JLabel lblRate;
    private javax.swing.JList lstTimeslots;
    private ca.robokids.robooffice.desktop.customSwing.PostalCodeJTextField postalCodeJTextField1;
    private ca.robokids.robooffice.desktop.customSwing.PostalCodeJTextField txtCode;
    private javax.swing.JTextArea txtDescription;
    private ca.robokids.robooffice.desktop.customSwing.IntegerNumberJTextField txtDuration;
    private javax.swing.JTextField txtName;
    private ca.robokids.robooffice.desktop.customSwing.CurrencyJTextField txtRate;
    // End of variables declaration//GEN-END:variables

   private void populateFields() {

      this.lblCode.setText(course.getCode());
      this.lblName.setText(course.getName());
      this.lblRate.setText(course.getRateString());
      this.lblDuration.setText(String.valueOf(course.getDuration()));
      String reportName = new String();
      try {
         reportName = SchoolManager.getProgressReportTypeName(course.getReport_type_id());
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }
      this.lblDescription.setText(course.getDescription());
      this.lblProgressReportType.setText(reportName);
      this.lblClassroom.setText(course.getClassroom().toString());

      this.txtCode.setText(course.getCode());
      this.txtName.setText(course.getName());
      this.txtRate.setText(String.valueOf(course.getRate()));
      this.txtDuration.setText(String.valueOf(course.getDuration()));
      this.txtDescription.setText(course.getDescription());
      //set Selection of classroom and progress Report Type
      for (int i = 0; i < classroomModel.getSize(); i++) {
         if (course.getClassroom().getClassroom_id() == classroomModel.getElementAt(i).getClassroom_id()) {
            cboClassrooms.setSelectedIndex(i);
            break;
         }
      }
      for (int i = 0; i < reportTypeModel.getSize(); i++) {
         if (course.getReport_type_id() == reportTypeModel.getElementAt(i).getReport_type_id()) {
            cboReportType.setSelectedIndex(i);
            break;
         }
      }

      timeslots.clear();
      slots.removeAllElements();
      for (Timeslot t : course.getTimeslots()) {
         timeslots.add(t);
         slots.addElement(t);
      }


   }

   private void loadPanel() {
      try {
         classroomModel.removeAllElements();
         reportTypeModel.removeAllElements();

         List<Classroom> classrooms = SchoolManager.loadAllClassroom();
         List<ProgressReportType> reportTypes = SchoolManager.loadAllProgressReportType();

         for (Classroom cr : classrooms) {
            classroomModel.addElement(cr);
         }
         if (classroomModel.getSize() < 1) {
            PopupMessage.createErrorPopUp("There is no classroom setting in the system.\nYou need to go and create a classroom first.", "Warning!");
            TabManager.createTab("classroomSettings");
            TabManager.closeTab(parent.getName());
         }
         cboClassrooms.setSelectedIndex(0);
         for (ProgressReportType t : reportTypes) {
            reportTypeModel.addElement(t);
         }
         if (reportTypeModel.getSize() < 1) {
            PopupMessage.createErrorPopUp("There is no Progress Report Template setting in the system.\nYou need to go and create a progress report template first.", "Warning!");
            TabManager.createTab("progresReportSettings");
            TabManager.closeTab(parent.getName());
         }
         cboReportType.setSelectedIndex(0);;

      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }


   }

   private void switchTo(String display) {
      CardLayout c = (CardLayout) information.getLayout();
      c.show(information, display);
   }

   private Course getFields() {
      Course c = new Course();
      c.setCode(txtCode.getText().trim());
      c.setName(txtName.getText().trim());
      c.setDescription(txtDescription.getText().trim());
      c.setDuration(Integer.valueOf(txtDuration.getText().trim()));
      c.setRate(Float.valueOf(txtRate.getText().trim()));
      c.setClassroom(classroomModel.getElementAt(cboClassrooms.getSelectedIndex()));
      c.setReport_type_id(reportTypeModel.getElementAt(cboReportType.getSelectedIndex()).getReport_type_id());
      c.setTimeslots(timeslots);

      return c;
   }

   private void reset() {
      lblCode.setText("N/A");
      lblName.setText("N/A");
      lblDuration.setText("N/A");
      lblRate.setText("N/A");
      lblClassroom.setText("N/A");
      this.lblProgressReportType.setText("N/A");
      timeslots.clear();
      slots.clear();

      this.txtCode.setText("");
      this.txtName.setText("");
      this.txtDuration.setText("");
      this.txtRate.setText("");
      this.txtDescription.setText("");

      lblMsg.setText("");
   }
}
