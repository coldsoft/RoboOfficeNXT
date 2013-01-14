/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.usermanagement;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.exception.DoesNotExistException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import ca.robokids.robooffice.logic.system.SystemLog;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class UserManager {
   
   public static void createUser(User user) throws BadFieldException, DatabaseException
   {
      CheckFields.checkUser(user);      
      user.setUser_id(UserDBM.createUser(user));
      
      //Event Logging
      String details = "New user  " + user.toString() + " created.";
      SystemLog.createUserLog(Operation.USER_SETTING, details, user.getUser_id());
   }
   
   public static void modifyUser(User user) throws BadFieldException, DatabaseException
   {
      CheckFields.checkUser(user);
      UserDBM.modifyUser(user);
      //Event Logging
      String details = "User " + user.toString() + " was modified";
      SystemLog.createUserLog(Operation.USER_SETTING, details, user.getUser_id());
   }
   public static void deleteUser(User user) throws DatabaseException, DoesNotExistException 
   {
      User temp = UserDBM.getUserByID(user.getUser_id());
      if (temp == null)
         throw new DoesNotExistException("Error: User not found in database; user ID " + user.getUser_id()+ " no match.");
      
      UserDBM.deleteUser(user.getUser_id());
      //Event Logging
      String details = "Deleted user " + user.toString();
      SystemLog.createUserLog(Operation.USER_SETTING, details, user.getUser_id());
   }
   
   public static void createUserGroup(UserGroup group) throws BadFieldException, DatabaseException
   {
      CheckFields.checkGroup(group);
      
      UserDBM.createUserGroup(group);
      
      //Event Logging
      String details = "New Usergroup " + group.getGroupName() + "was created.";
      SystemLog.createUserLog(Operation.USER_GROUP_SETTING, details, group.getGroup_id());
   }
   
   public static void deleteUserGroup(UserGroup group) throws DatabaseException, DoesNotExistException
   {
      UserGroup temp = UserDBM.getUserGroupByID(group.getGroup_id());
      if (temp == null)
         throw new DoesNotExistException("Error: Usergroup not found in database; group ID " + group.getGroup_id()+ " no match.");
      
      UserDBM.deleteGroup(group.getGroup_id());
      
      //Event Logging
      String details = "Deleted usergroup " + group.getGroupName() + "";
      SystemLog.createUserLog(Operation.USER_GROUP_SETTING, details, group.getGroup_id());
   }
   public static void modifyUserGroup(UserGroup group) throws DatabaseException, BadFieldException
   {
      CheckFields.checkGroup(group);
      UserDBM.modifyGroup(group);
      //Event Logging
      String details = "Modified Usergroup " + group.getGroupName();
      SystemLog.createUserLog(Operation.USER_GROUP_SETTING, details, group.getGroup_id());
   }
      
   
   
   public static List<User> loadAllUsers() throws DatabaseException
   {
      return UserDBM.getAllUsers(-1);
   }
   public static List<User> loadAllUsersOfGroup(int groupID) throws DatabaseException
   {
      return UserDBM.getAllUsers(groupID);
   }
   
   public static List<UserGroup> loadAllUserGroup() throws DatabaseException
   {
      return UserDBM.getAllUserGroups(-1);
   }
   
   
   public static List<UserGroup> loadUsergroupsOfUser(int userID) throws DatabaseException
   {
      return UserDBM.getAllUserGroups(userID);
   }
   
   public static List<Action> loadAllActions() throws DatabaseException
   {
      return UserDBM.getAllActions(-1);
   }
   
   public static List<Action> loadAllActionsOfGroup(int groupID) throws DatabaseException 
   {
      return UserDBM.getAllActions(groupID);
   }
   
   public static List<PasswordQuestion> loadAllQuestion() throws DatabaseException
   {
      return UserDBM.getAllPasswordQuestion();
   }
   
   
   
}
