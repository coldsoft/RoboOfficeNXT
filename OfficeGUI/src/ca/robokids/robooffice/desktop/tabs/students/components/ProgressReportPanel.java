/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.students.components;

import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import ca.robokids.robooffice.entity.student.ProgressReport;

/**
 *
 * @author Coldsoft
 */
public class ProgressReportPanel extends javax.swing.JPanel {

   /**
    * Creates new form ProgressReportPanel
    */
   ProgressReportSectionPanel section1;
   ProgressReportSectionPanel section2;
   ProgressReportSectionPanel section3;
   ProgressReportSectionPanel section4;
   
   ProgressReport progressReport;
   ProgressReportType reportType;
   
   public ProgressReportPanel() {
      initComponents();
      section1 = new ProgressReportSectionPanel();
      section2 = new ProgressReportSectionPanel();
      section3 = new ProgressReportSectionPanel();
      section4 = new ProgressReportSectionPanel();
      
      sectionPanel.add(section1);
      sectionPanel.add(section2);
      sectionPanel.add(section3);
      sectionPanel.add(section4);
   }
   
   public void setProgressReport(ProgressReport progressReport, ProgressReportType reportType)
   {
      if (reportType == null)
         return;
      
      this.reportType = reportType;
      setReportType();
      
      if (progressReport == null){
         reset();
         this.progressReport = null;
      }else{
         populateFields();
      }
         
      
   }
   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sectionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblProject = new javax.swing.JComboBox();
        lblTimeslot = new javax.swing.JLabel();
        chkTest = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        lblProgressReportType = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblMsg = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblModifiedBy = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblModifiedDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCourse = new javax.swing.JComboBox();

        jLabel7.setText("jLabel7");

        sectionPanel.setMaximumSize(new java.awt.Dimension(2000, 1000));
        sectionPanel.setLayout(new javax.swing.BoxLayout(sectionPanel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(sectionPanel);

        jLabel1.setFont(FontsLoader.getStaticLabelFont());
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Project");

        lblProject.setEditable(true);
        lblProject.setFont(FontsLoader.getComboBoxFont());
        lblProject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Explorer" }));

        lblTimeslot.setFont(FontsLoader.getDynamicLabelFont());
        lblTimeslot.setText("Mon 16:00 Jan 1, 2013");

        chkTest.setFont(FontsLoader.getStaticLabelFont());
        chkTest.setText("Test Day");

        jLabel3.setFont(FontsLoader.getStaticLabelFont());
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Using Report Type:");

        lblProgressReportType.setFont(FontsLoader.getDynamicLabelFont());
        lblProgressReportType.setText("N/A");

        btnSave.setFont(FontsLoader.getButtonFont());
        btnSave.setText("Save Progress Report");

        lblMsg.setFont(FontsLoader.getErrorLabelFont());

        jLabel5.setFont(FontsLoader.getStaticLabelFont());
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Last Modified by:");

        lblModifiedBy.setFont(FontsLoader.getDynamicLabelFont());
        lblModifiedBy.setText("N/A");

        jLabel8.setFont(FontsLoader.getStaticLabelFont());
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Modified on:");

        lblModifiedDate.setFont(FontsLoader.getDynamicLabelFont());
        lblModifiedDate.setText("N/A");

        jLabel6.setFont(FontsLoader.getStaticLabelFont());
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("From Course:");

        lblCourse.setEditable(true);
        lblCourse.setFont(FontsLoader.getComboBoxFont());
        lblCourse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Explorer" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(chkTest, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProject, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimeslot, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModifiedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProgressReportType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModifiedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkTest, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTimeslot, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblModifiedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProgressReportType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                        .addComponent(lblModifiedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox lblCourse;
    private javax.swing.JLabel lblModifiedBy;
    private javax.swing.JLabel lblModifiedDate;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JLabel lblProgressReportType;
    private javax.swing.JComboBox lblProject;
    private javax.swing.JLabel lblTimeslot;
    private javax.swing.JPanel sectionPanel;
    // End of variables declaration//GEN-END:variables

   private void setReportType() {
      lblProgressReportType.setText(reportType.getName() + "(max score:" + Integer.valueOf(reportType.getMaxScore()) + ")");
      section1.setSection( reportType.getMaxScore(),
                           reportType.getSection1(),
                           reportType.getCriteria1(),
                           reportType.getCriteria2(),
                           reportType.getCriteria3());
      section2.setSection( reportType.getMaxScore(),
                           reportType.getSection2(),
                           reportType.getCriteria4(),
                           reportType.getCriteria5(),
                           reportType.getCriteria6());
      section3.setSection( reportType.getMaxScore(),
                           reportType.getSection3(),
                           reportType.getCriteria7(),
                           reportType.getCriteria8(),
                           reportType.getCriteria9());
      section4.setSection( reportType.getMaxScore(),
                           reportType.getSection4(),
                           reportType.getCriteria10(),
                           reportType.getCriteria11(),
                           reportType.getCriteria12());
      section4.setDisable(true);
      
   }

   private void reset() {
      section1.reset();
      section2.reset();
      section3.reset();
      section4.reset();
      
   }

   private void populateFields() {
      section1.setData( progressReport.getCriteria1(),
                        progressReport.getCriteria2(),
                        progressReport.getCriteria3(),
                        progressReport.getSection1_comment());
      section2.setData( progressReport.getCriteria4(),
                        progressReport.getCriteria5(),
                        progressReport.getCriteria6(),
                        progressReport.getSection2_comment());
      section3.setData( progressReport.getCriteria7(),
                        progressReport.getCriteria8(),
                        progressReport.getCriteria9(),
                        progressReport.getSection3_comment());
      section4.setData( progressReport.getSection4_comment());
   }
}