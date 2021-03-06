/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.guitest;

import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.util.ButtonColumn;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Coldsoft
 */
public class JTableTester extends javax.swing.JFrame {

   DefaultTableModel model;

   /**
    * Creates new form JTableTester
    */
   public JTableTester() {
      initComponents();

      tblSummary.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

         public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
               valueChange();
            }
         }
      });


      DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
      rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
      tblSummary.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

      tblSummary.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Payment Entry");
      tblSummary.getColumnModel().getColumn(0).setMinWidth(200);
      
      tblSummary.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Amount");
      tblSummary.getColumnModel().getColumn(1).setMinWidth(30);
      
      tblSummary.getColumnModel().getColumn(2).setMinWidth(30);
      Action delete = new AbstractAction() {

         
         @Override
         public void actionPerformed(ActionEvent e) {
            tblSummary = (JTable) e.getSource();
            int modelRow = Integer.valueOf(e.getActionCommand());
            ((DefaultTableModel) tblSummary.getModel()).removeRow(modelRow);
         }
      };
      ButtonColumn bc = new ButtonColumn(tblSummary, delete, 2,true);
      DefaultTableModel dm = (DefaultTableModel) tblSummary.getModel();
      resetTableContent();
      dm.addRow(new Object[]{"Column 1", "Column 2", "Remove"});
   }

   private void resetTableContent() {
      DefaultTableModel dm = (DefaultTableModel) tblSummary.getModel();

      for (int i = dm.getRowCount() - 1; i >= 0; i--) {
         dm.removeRow(i);
      }
   }

   private void valueChange() {
      System.out.println("clicked " + tblSummary.getSelectedRow());
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
        tblSummary = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSummary.setFont(FontsLoader.getDynamicLabelFont());
        tblSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Title 2", "   "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSummary.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tblSummary.setEditingColumn(2);
        tblSummary.setRowHeight(30);
        tblSummary.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSummary.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblSummaryPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tblSummary);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void tblSummaryPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblSummaryPropertyChange
      // TODO add your handling code here:
   }//GEN-LAST:event_tblSummaryPropertyChange

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      /*
       * Set the Nimbus look and feel
       */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
       * If Nimbus (introduced in Java SE 6) is not available, stay with the
       * default look and feel. For details see
       * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
       */
      try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Windows".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(JTableTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(JTableTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(JTableTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(JTableTester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /*
       * Create and display the form
       */
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            new JTableTester().setVisible(true);
         }
      });
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSummary;
    // End of variables declaration//GEN-END:variables
}
