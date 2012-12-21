/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.main;

import ca.robokids.exception.DatabaseException;
import ca.robokids.exception.RoboOfficeException;
import ca.robokids.exception.userexception.BadUsernameException;
import ca.robokids.robooffice.desktop.loaders.FontsLoader;
import ca.robokids.robooffice.desktop.util.PopupMessage;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class LoginTab extends javax.swing.JPanel {

   /**
    * Creates new form LoginTab
    */
   public LoginTab() {
      initComponents();
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

        centre = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnForgotPassword = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        pnlLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel1.setFont(FontsLoader.getStaticLabelFont());
        jLabel1.setText("Username:");

        txtUsername.setFont(FontsLoader.getTextFieldFont());
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel2.setFont(FontsLoader.getStaticLabelFont());
        jLabel2.setText("Password:");

        txtPassword.setFont(FontsLoader.getTextFieldFont());
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        btnForgotPassword.setFont(FontsLoader.getButtonFont());
        btnForgotPassword.setText("Forgot Password?");
        btnForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPasswordActionPerformed(evt);
            }
        });

        btnLogin.setFont(FontsLoader.getButtonFont());
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblError.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword)))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(btnForgotPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnLogin)))
                .addContainerGap())
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnForgotPassword)
                    .addComponent(btnLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ca/robokids/robooffice/desktop/images/logo_Robokids.png"))); // NOI18N

        javax.swing.GroupLayout centreLayout = new javax.swing.GroupLayout(centre);
        centre.setLayout(centreLayout);
        centreLayout.setHorizontalGroup(
            centreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 355, Short.MAX_VALUE)
            .addGroup(centreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(centreLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(centreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(centreLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        centreLayout.setVerticalGroup(
            centreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
            .addGroup(centreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(centreLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addGap(6, 6, 6)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 100, 0);
        add(centre, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

   private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
      login();
   }//GEN-LAST:event_btnLoginActionPerformed

   private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
      login();
   }//GEN-LAST:event_txtUsernameActionPerformed

   private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
      login();
   }//GEN-LAST:event_txtPasswordActionPerformed

   private void btnForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPasswordActionPerformed
      User user = null;
      try {
         user = UserActivity.forgotPassword(txtUsername.getText().trim());
      } catch (DatabaseException ex) {
         PopupMessage.createErrorPopUp(ex.getMessage(), null);
         return;
      } catch (BadUsernameException ex) {
         errorMsg(ex.getMessage());
         return;
      }
      
      String question = user.getPasswordQuestion().getQuestion();
      String answer = user.getPasswordAnswer();
      
      String userInput = PopupMessage.createInput(question, "Enter your answer");
      if (userInput == null)
         return;
      if (answer.equals(userInput))
      {
         PopupMessage.createInfo("Your password: " + user.getPassword(), "Please remember");
         return;
      }else{
         PopupMessage.createErrorPopUp("Sorry, Wrong Answer.\nTry again or contact Store Manager/IT department.", answer);
      }
      
      
      
   }//GEN-LAST:event_btnForgotPasswordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel centre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

   public void focusTxtUser() {
      this.txtUsername.requestFocus();
   }

   public void reset()
    {
        txtUsername.setText("");
        txtPassword.setText("");
        lblError.setText("");
        enableLoginPanel(true);
    }
   
   private void enableLoginPanel(boolean b)
    {
        btnLogin.setEnabled(b);
        btnForgotPassword.setEnabled(b);
        txtPassword.setEnabled(b);
        txtUsername.setEnabled(b);
        pnlLogin.repaint();
        pnlLogin.revalidate();
    }
   
   private void login()
    {
        
        if ("".equals(txtUsername.getText()))
        {
            return;
        }
        
        errorMsg("Logging in, please Wait...."); 
        enableLoginPanel(false);
        String password = String.valueOf(txtPassword.getPassword());
        Thread t = new Thread(new LoginThread(this,txtUsername.getText(),password));
        t.start();

    }
   public void errorMsg(String msg)
    {
        enableLoginPanel(true);
        lblError.setText(msg);
    }
   
private class LoginThread implements Runnable
{
    LoginTab l;
    String username;
    String password;
    public LoginThread(LoginTab l,String username, String password)
    {
        this.l = l;
        this.username = username;
        this.password = password;
    }

          public void run()
    {
        User user = new User();
        try
        {
            user = UserActivity.login(username, password);
        } catch (RoboOfficeException e)
        {
            l.errorMsg(e.getMessage());
            return;
        }
        MainRoboOfficeJFrame.getInstance().login(user);
    }
       
    }

}