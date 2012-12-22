/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.school;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.desktop.customSwing.ClassroomInfoPanel;
import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.tabs.Tab;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.logic.schoolsettings.SchoolManager;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Coldsoft
 */
public class ClassroomTab extends javax.swing.JPanel implements Tab {

   /**
    * Creates new form ClassroomTab
    */
   DefaultListModel<Classroom> classroomModel = new DefaultListModel();
   ClassroomInfoPanel panelClassroom;
   Classroom classroom;
   int selection = 0;

   public ClassroomTab() {

      initComponents();
      switchCard("existing");
      panelClassroom = new ClassroomInfoPanel(this);
      panelClassroom.setClassroom(null);
      pnlclassroom.add(panelClassroom);
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
        pnlAdd = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblMsg = new javax.swing.JLabel();
        spinCapacity = new com.toedter.components.JSpinField();
        pnlExisting = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstClassroom = new javax.swing.JList();
        pnlclassroom = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        center.setLayout(new java.awt.CardLayout());

        pnlAdd.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add new Classroom", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, FontsLoader.getTitleBorderFont()));

        btnSave.setFont(FontsLoader.getButtonFont());
        btnSave.setText("Add");
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

        jLabel2.setFont(FontsLoader.getStaticLabelFont());
        jLabel2.setText("Classroom name:");

        jLabel3.setFont(FontsLoader.getStaticLabelFont());
        jLabel3.setText("Capacity:");

        txtName.setFont(FontsLoader.getTextFieldFont());

        lblMsg.setFont(FontsLoader.getErrorLabelFont());

        spinCapacity.setFont(FontsLoader.getComboBoxFont());
        spinCapacity.setMaximum(50);
        spinCapacity.setMinimum(0);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(spinCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 127, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(spinCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 127;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(140, 122, 151, 144);
        pnlAdd.add(jPanel1, gridBagConstraints);

        center.add(pnlAdd, "add");

        jLabel1.setFont(FontsLoader.getStaticLabelFont());
        jLabel1.setText("Classrooms");

        lstClassroom.setFont(FontsLoader.getListFont());
        lstClassroom.setModel(classroomModel);
        lstClassroom.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstClassroomValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstClassroom);

        pnlclassroom.setLayout(new javax.swing.BoxLayout(pnlclassroom, javax.swing.BoxLayout.LINE_AXIS));

        btnAdd.setFont(FontsLoader.getButtonFont());
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlExistingLayout = new javax.swing.GroupLayout(pnlExisting);
        pnlExisting.setLayout(pnlExistingLayout);
        pnlExistingLayout.setHorizontalGroup(
            pnlExistingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExistingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExistingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlExistingLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlclassroom, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
        );
        pnlExistingLayout.setVerticalGroup(
            pnlExistingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExistingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExistingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlclassroom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlExistingLayout.createSequentialGroup()
                        .addGroup(pnlExistingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(btnAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)))
                .addContainerGap())
        );

        center.add(pnlExisting, "existing");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(center, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

   private void lstClassroomValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstClassroomValueChanged
      int index = lstClassroom.getSelectedIndex();
      if (index > -1) {
         selection = index;
         classroom = classroomModel.getElementAt(index);
         panelClassroom.setClassroom(classroom);
      }

   }//GEN-LAST:event_lstClassroomValueChanged

   private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

      switchCard("add");
      txtName.setText("");
      errorMsgEdit("");
   }//GEN-LAST:event_btnAddActionPerformed

   private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      this.btnSave.setEnabled(false);
      Classroom temp = getFields();
      if (temp != null) {
         try {
            SchoolManager.createClassroom(temp);

         } catch (BadFieldException ex) {
            errorMsgEdit(ex.getMessage());
            this.btnSave.setEnabled(true);
            return;
         } catch (DatabaseException ex) {
            PopupMessage.createErrorPopUp(ex.getMessage(), null);
            this.btnSave.setEnabled(true);
            return;
         }

         classroom = temp;
         switchCard("existing");

      }
      refresh();
      this.btnSave.setEnabled(true);
   }//GEN-LAST:event_btnSaveActionPerformed

   private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
      switchCard("existing");
   }//GEN-LAST:event_btnCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel center;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JList lstClassroom;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlExisting;
    private javax.swing.JPanel pnlclassroom;
    private com.toedter.components.JSpinField spinCapacity;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

   @Override
   public void initialize() {
      refresh();
   }

   @Override
   public void refresh() {
      classroomModel.clear();
      try {
         List<Classroom> classrooms = SchoolManager.loadAllClassroom();
         for (Classroom c : classrooms) {
            classroomModel.addElement(c);
         }
         
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
      }
      int size = classroomModel.getSize();
         if (size > 0 && selection < size) {
            
            lstClassroom.setSelectedIndex(selection);
         }else
         {
            if (size > 0)
            {
               lstClassroom.setSelectedIndex(0);
               
            }else
               panelClassroom.setClassroom(null);
         }
   }

   @Override
   public void setFocus() {
      this.btnAdd.requestFocus();
   }

   private void errorMsgEdit(String message) {
      lblMsg.setForeground(new Color(255, 102, 102));
      lblMsg.setText(message);
   }

   private Classroom getFields() {
      Classroom cr = new Classroom();
      if (txtName.getText().trim().equals("")) {
         errorMsgEdit("Name needs to be filled");
         return null;

      }
      if ((Integer) spinCapacity.getValue() < 3) {
         if (!PopupMessage.createConfirmPopUp("You entered a very small capacity number.\n Are you sure?", "Hmm...")) {
            return null;
         }
      }


      cr.setName(txtName.getText().trim());
      cr.setCapacity((Integer) spinCapacity.getValue());
      return cr;
   }

   private void switchCard(String card) {
      CardLayout c = (CardLayout) center.getLayout();
      c.show(center, card);
   }
}
