/*
 * 
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.usermanagement;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class UserDBM {

   
   /**
    * User object needs to be completely filled, with List of UserGroup Objects
    *
    * @param user
    * @throws DatabaseException
    */
   public static void createUser(User user) throws DatabaseException {
      List<UserGroup> groups = user.getUserGroups();

      //create a new user row in table USER
      PreparedStatement stmt = insertUser(user);
      int newUserID = DatabaseManager.executeGetPK(stmt);

      //add mapping between usergroup and newly added user
      for (UserGroup g : groups) {
         createUserGroupMapping(newUserID,g.getGroup_id());
      }     
   }
   
   public static void modifyUser(User user) throws DatabaseException
   {
      //Delete old user group mapping for this user
      List<UserGroup> groups = getAllUserGroups(user.getUser_id());
      for (UserGroup g : groups) {
         try {
            deleteUserGroupMapping(user.getUser_id(),g.getGroup_id());
         } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
         }
      }  
      
      //update user fields
      PreparedStatement stmt = updateUser(user);
      DatabaseManager.executeUpdate(stmt);

      groups = user.getUserGroups();
      //add mapping between usergroup and newly added user
      for (UserGroup g : groups) {
         createUserGroupMapping(user.getUser_id(),g.getGroup_id());
      }     
   }

   /**
    * Deleted a user in the system, mark the delete flag as 1 User must exist
    * before calling this method
    *
    * @param userID 
    * @return false if the deleted flag is already 1, else true
    * @throws DatabaseException
    */
   public static boolean deleteUser(int userID) throws DatabaseException {
      try {
         String query = "UPDATE user SET deleted = 1 WHERE user_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1,userID);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

   }

   
   /**
    * search user by user ID, returns the User object
    * @param userID
    * @return User object if there exists a user. else return null
    * @throws DatabaseException
    */
   public static User getUserByID(int userID) throws DatabaseException {
      try {
         User temp = null;
         String getUserByID = "SELECT * FROM user_view WHERE user_id = ? GROUP BY user_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getUserByID);
         stmt.setInt(1, userID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            temp = new User();
            temp.setUser_id(crs.getInt("user_id"));
            temp.setUserName((crs.getString("user_name")));
            temp.setPassword((crs.getString("password")));
            //Set password question object in user
            PasswordQuestion p = new PasswordQuestion();
            p.setPassword_qestion_id(crs.getInt("question_id"));
            p.setQuestion(crs.getString("question"));
            temp.setPasswordQuestion(p);

            temp.setPasswordAnswer(crs.getString("password_answer"));
            temp.setFirstName(crs.getString("first_name"));
            temp.setLastName(crs.getString("last_name"));
            temp.setEmail(crs.getString("email"));
            temp.setAddress(crs.getString("address"));
            temp.setSIN(crs.getString("SIN"));
            temp.setPhone(crs.getString("phone"));
            temp.setDeleted(false);
            temp.setUserGroups(getAllUserGroups(temp.getUser_id()));
         }
         return temp;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

   }
   /**
    * get All undeleted users within a given usergroup, or every user in the
    * system if userGroupID < 1
    *

    *
    * @param userGroupID
    * @return
    * @throws DatabaseException
    */
   public static List<User> getAllUsers(int userGroupID) throws DatabaseException {
      try {
         List<User> users = new ArrayList();
         String query;
         if (userGroupID < 1) {
            query = "SELECT DISTINCT user_id FROM roboofficenxt.user_view";
         } else {
            query = "SELECT DISTINCT user_id FROM roboofficenxt.user_view WHERE GROUP_ID = " + String.valueOf(userGroupID)
               + " ORDER BY user_name ASC";
         }

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            User user = getUserByID(crs.getInt("user_id"));
            if (!user.getUserName().equals("root"))
               users.add(user);
         }

         return users;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   /**
    * Create a UserGroup object in the database, list of Actions needs to be
    * specified
    *
    * @param group
    * @throws DatabaseException
    */
   public static void createUserGroup(UserGroup group) throws DatabaseException {
      List<Action> actions = group.getActions();
      //create a new group row in table user_group
      PreparedStatement stmt = insertUserGroup(group);
      int newGroupID = DatabaseManager.executeGetPK(stmt);
      //add mapping between actions and newly added groups

      for (Action a : actions) {
         createGroupActionMapping(newGroupID,a.getAction_id());
      }
   }
   
   

   public static void modifyGroup(UserGroup group) throws DatabaseException
   {
      //Delete old group action mapping for this group
      List<Action> actions = getAllActions(group.getGroup_id());
      for (Action a : actions) {
         try {
            deleteGroupActionMapping(group.getGroup_id(),a.getAction_id());
         } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
         }
      }  
      
      //update group fields
      PreparedStatement stmt = updateUserGroup(group);
      int newUserID = DatabaseManager.executeUpdate(stmt);

      actions = group.getActions();
      //add new mapping between group and actions
      for (Action a : actions) {
         createGroupActionMapping(group.getGroup_id(),a.getAction_id());
      }     
   }
   public static boolean deleteGroup(int userGroupID) throws DatabaseException {
      try {
         String query = "UPDATE user_group SET deleted = 1 WHERE group_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1,userGroupID);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

   }

   

   public static UserGroup getUserGroupByID(int groupID) throws DatabaseException {
      try {
         UserGroup group = null;
         String getUserGroupByID = "SELECT * FROM group_view WHERE group_id = ? GROUP BY group_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getUserGroupByID);
         stmt.setInt(1, groupID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            group = new UserGroup();
            group.setGroup_id(crs.getInt("group_id"));
            group.setGroupName(crs.getString("group_name"));
            group.setActions(getAllActions(group.getGroup_id()));           
         }
         return group;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

   }
   /**
    * get All usergroups of a single User, or every usergroup in the system if
    * userID < 1
    *

    *
    * @param userID
    * @return
    * @throws DatabaseException
    */
   public static List<UserGroup> getAllUserGroups(int userID) throws DatabaseException {
      try {
         List<UserGroup> groups = new ArrayList();

         String query;
         if (userID < 1) {
            query = "SELECT * FROM user_group WHERE deleted = 0 ORDER BY group_name ASC";
         } else {
            query = "SELECT * FROM user_view WHERE user_id = " + String.valueOf(userID) + " ORDER BY group_name ASC";
         }
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            UserGroup g = new UserGroup();
            g.setGroup_id(crs.getInt("group_id"));
            g.setGroupName(crs.getString("group_name"));
            g.setActions(getAllActions(g.getGroup_id()));
            if (g.getGroupName().equals("superuser"))
               continue;
            groups.add(g);
         }
         return groups;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   /**
    * get All actions for a user group, or every action within the system if
    * groupID < 1
    *

    *
    * @param groupID
    * @return
    * @throws DatabaseException
    */
   public static List<Action> getAllActions(int groupID) throws DatabaseException {
      try {
         List<Action> actions = new ArrayList();
         String query;
         if (groupID < 1) {
            query = "SELECT * FROM action WHERE deleted = 0 ORDER BY action_description ASC";
         } else {
            query = "SELECT * FROM group_view WHERE group_id = " + String.valueOf(groupID) + " ORDER BY action_description ASC";
         }
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);


         while (crs.next()) {
            Action a = new Action();
            a.setAction_id(crs.getInt("action_id"));
            a.setActionName(crs.getString("action_name"));
            a.setDescription(crs.getString("action_description"));
            actions.add(a);
         }

         return actions;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());

      }
   }

   /**
    *
    * @param userName
    * @return
    * @throws DatabaseException
    */
   public static User getUserByUserName(String userName) throws DatabaseException {
      try {
         User user = null;
         String query = "SELECT DISTINCT user_id FROM user_view WHERE user_name = ?";
         PreparedStatement stmt;
         stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, userName);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()) {
            user = getUserByID(crs.getInt("user_id"));
         }
         return user;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }


   private static PreparedStatement insertUser(User user) throws DatabaseException {
      String createUser = "INSERT INTO user (user_name,password,password_question_id,password_answer,first_name,last_name,email,address,SIN,phone) "
         + " VALUES(?,?,?,?,?,?,?,?,?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createUser);
         stmt.setString(1, user.getUserName());
         stmt.setString(2, user.getPassword());
         stmt.setInt(3, user.getPasswordQuestion().getPassword_qestion_id());
         stmt.setString(4, user.getPasswordAnswer());
         stmt.setString(5, user.getFirstName());
         stmt.setString(6, user.getLastName());
         stmt.setString(7, user.getEmail());
         stmt.setString(8, user.getAddress());
         stmt.setString(9, user.getSIN());
         stmt.setString(10, user.getPhone());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   
   private static PreparedStatement updateUser(User user) throws DatabaseException {
      String updateUser = "UPDATE user SET user_name = ?, password = ?, password_question_id = ?, password_answer = ?,"
         + "first_name = ? ,last_name = ? ,email = ?,address = ?,SIN = ?,phone = ? WHERE user_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(updateUser);
         stmt.setString(1, user.getUserName());
         stmt.setString(2, user.getPassword());
         stmt.setInt(3, user.getPasswordQuestion().getPassword_qestion_id());
         stmt.setString(4, user.getPasswordAnswer());
         stmt.setString(5, user.getFirstName());
         stmt.setString(6, user.getLastName());
         stmt.setString(7, user.getEmail());
         stmt.setString(8, user.getAddress());
         stmt.setString(9, user.getSIN());
         stmt.setString(10, user.getPhone());
         stmt.setInt(11, user.getUser_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   private static void createUserGroupMapping(int userID, int groupID) throws DatabaseException
   {
      PreparedStatement stmt = insertUserGroupMapping(userID, groupID);
      DatabaseManager.executeUpdate(stmt);
   }
   private static void deleteUserGroupMapping(int userID, int groupID) throws SQLException, DatabaseException
   {
      String delete = "DELETE FROM user_group_mapping WHERE user_id = " + String.valueOf(userID) + 
                        " AND group_id = " + String.valueOf(groupID);
      PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
      DatabaseManager.executeUpdate(stmt);
   }
   private static PreparedStatement insertUserGroupMapping(int userID, int groupID) throws DatabaseException {
      String createMapping = "INSERT INTO user_group_mapping(user_id,group_id )"
         + "VALUES(?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createMapping);
         stmt.setInt(1, userID);
         stmt.setInt(2, groupID);
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement insertUserGroup(UserGroup group) throws DatabaseException {
      String createUserGroup = "INSERT INTO user_group (group_name) VALUES(?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createUserGroup);
         stmt.setString(1, group.getGroupName());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   private static PreparedStatement updateUserGroup(UserGroup group) throws DatabaseException {
      String updateUserGroup = "UPDATE user_group SET group_name = ? WHERE group_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(updateUserGroup);
         stmt.setString(1, group.getGroupName());
         stmt.setInt(2, group.getGroup_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   private static void createGroupActionMapping(int groupID, int actionID) throws DatabaseException
   {
      PreparedStatement stmt = insertGroupActionMapping(groupID, actionID);
            DatabaseManager.executeUpdate(stmt);
   }
   private static void deleteGroupActionMapping(int groupID, int actionID) throws SQLException, DatabaseException
   {
      String delete = "DELETE FROM group_action_mapping WHERE group_id = " + String.valueOf(groupID) + 
                        " AND action_id = " + String.valueOf(actionID);
      PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
      DatabaseManager.executeUpdate(stmt);
   }
   private static PreparedStatement insertGroupActionMapping(int groupID, int actionID) throws DatabaseException {
      String createMapping = "INSERT INTO group_action_mapping(group_id,action_id )"
         + "VALUES(?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createMapping);
         stmt.setInt(1, groupID);
         stmt.setInt(2, actionID);
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   public static List<PasswordQuestion> getAllPasswordQuestion() throws DatabaseException {
      try {
         List<PasswordQuestion> questions = new ArrayList();

         String query = "SELECT * FROM password_question";
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            PasswordQuestion q = new PasswordQuestion();
            q.setPassword_qestion_id(crs.getInt("question_id"));
            q.setQuestion(crs.getString("question"));
            questions.add(q);
         }
         return questions;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   

}
