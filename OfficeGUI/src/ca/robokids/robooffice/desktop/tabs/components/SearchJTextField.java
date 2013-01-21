/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.components;

import javax.swing.JTextField;

/**
 *
 * @author Coldsoft
 */
public class SearchJTextField extends JTextField{
    /**
     * 
     */
    public SearchJTextField()
    {
        super();
        this.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPatronIDActionPerformed(evt);
            }
        });
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPatronIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPatronIDFocusLost(evt);
            }
        });
    }
     private void txtPatronIDActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.setName(this.getText());
    }                                           

    private void txtPatronIDFocusGained(java.awt.event.FocusEvent evt) {                                        
        this.setName(this.getText());
        this.setText("");
    }                                       

    private void txtPatronIDFocusLost(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here
        this.setText(this.getName());
    }     
    
}
