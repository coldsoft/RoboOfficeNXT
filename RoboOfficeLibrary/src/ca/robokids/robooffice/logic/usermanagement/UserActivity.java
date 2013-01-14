/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.usermanagement;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.exception.userexception.BadPasswordException;
import ca.robokids.exception.userexception.BadUsernameException;
import ca.robokids.robooffice.db.system.SystemDBM;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.entity.system.Log;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import ca.robokids.robooffice.logic.system.SystemLog;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class UserActivity {

   public static User loginUser = null;

   public static User login(String username, String password) throws DatabaseException, BadUsernameException, BadPasswordException {
      User user = UserDBM.getUserByUserName(username);
      if (user == null) {
         throw new BadUsernameException("User name not found.");
      }

      if (!password.equals(user.getPassword())) {
         throw new BadPasswordException("Password incorrect, please try again.");
      }
       //Event Logging
      String details = "log off.";
      SystemLog.createEventLog(Operation.LOGIN, details);

      loginUser = user;
      return user;

   }

   public static boolean hasPrivilege(String action) throws DatabaseException {
      if (loginUser.getUserName().equals("root")) {
         return true;
      }
      List<Action> actions = UserDBM.getAllActions(-1);
      if (!contains(actions, action)) {
         return true;
      }

      if (loginUser == null) {
         return true;
      }


      List<UserGroup> groups = loginUser.getUserGroups();
      for (UserGroup g : groups) {
         actions = g.getActions();
         if (contains(actions, action)) {
            return true;
         }
      }
      return false;
   }

   private static boolean contains(List<Action> actions, String action) {
      for (Action a : actions) {
         if (a.getActionName().equals(action)) {
            return true;
         }
      }
      return false;
   }

   public static void logout() throws DatabaseException {
      if (loginUser != null) {
         //Event Logging
      String details = "log off.";
      SystemLog.createEventLog(Operation.LOGOUT, details);
      }
      loginUser = null;
   }

   public static boolean isLogin() {
      return (loginUser == null) ? false : true;
   }

   public static User getLoginUser() {
      return loginUser;
   }

   public static void setLoginUser(User loginUser) {
      UserActivity.loginUser = loginUser;
   }

   public static void main(String args[]) {
      try {
         User user = login("htian", "htian");
         System.out.println("Login successful " + user.getFirstName());
      } catch (DatabaseException ex) {
         Logger.getLogger(UserActivity.class.getName()).log(Level.SEVERE, null, ex);
      } catch (BadUsernameException ex) {
         Logger.getLogger(UserActivity.class.getName()).log(Level.SEVERE, null, ex);
      } catch (BadPasswordException ex) {
         Logger.getLogger(UserActivity.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public static User forgotPassword(String username) throws DatabaseException, BadUsernameException {
      User user = UserDBM.getUserByUserName(username);
      if (user == null) {
         throw new BadUsernameException("User name not found.");
      }

       //Event Logging
      String details = "clicked forgot password.";
      SystemLog.createEventLog(Operation.FORGOTPASSWORD, details);

      loginUser = user;
      return user;
   }
}
