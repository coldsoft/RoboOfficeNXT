/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.usermanagement;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.sql.*;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class UserDBM {

   public static boolean createUser(User user, List<UserGroup> groups) throws DatabaseException {
      //create a new user row in table USER
      PreparedStatement stmt = insertUser(user);
      int key = DatabaseManager.executeGetPK(stmt);
      //add mapping between usergroup and newly added user

      for (UserGroup g : groups) {
         try {
            stmt = insertUserGroupMapping(key);
            stmt.setInt(2, g.getGroup_id());
            DatabaseManager.executeUpdate(stmt);

         } catch (SQLException ex) {

            throw new DatabaseException("SQL Error." + ex.getMessage());
         }
      }

      return true;
   }
   public static boolean createUserGroup(UserGroup group, List<Action> actions) throws DatabaseException {
      //create a new group row in table user_group
      PreparedStatement stmt = insertUserGroup(group);
      int key = DatabaseManager.executeGetPK(stmt);
      //add mapping between actions and newly added groups

      for (Action a : actions) {
         try {
            stmt = insertGroupActionMapping(key);
            stmt.setInt(2, a.getAction_id());
            DatabaseManager.executeUpdate(stmt);

         } catch (SQLException ex) {

            throw new DatabaseException("SQL Error." + ex.getMessage());
         }
      }

      return true;
   }

   public static User getUserByID(int userID) throws DatabaseException {
      try {
         User temp = new User();
         String getUserByID = "SELECT * FROM user_view WHERE user_id = ?";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getUserByID);
         stmt.setInt(1, userID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         //confirm that user id is found
         crs.first();
         int resultCount = crs.getInt("COUNT(*)");
         if (resultCount > 0) {
            crs.next();
            temp.setUser_id(crs.getInt("user_id"));
            temp.setUserName((crs.getString("user_name")));
            temp.setPassword((crs.getString("password")));

            temp.setPasswordQuestion(null);
            temp.setPasswordAnswer(crs.getString("password_answer"));
            temp.setFirstName(crs.getString("first_name"));
            temp.setLastName(crs.getString("last_name"));
            temp.setEmail(crs.getString("email"));
            temp.setAddress(crs.getString("address"));
            temp.setSIN(crs.getString("SIN"));
            temp.setPhone(crs.getString("phone"));
            temp.setDeleted(crs.getBoolean("deleted"));
            return temp;
         }
         else
         {
            return null;
         }
         
        
         
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

   private static PreparedStatement insertUserGroupMapping(int key) throws DatabaseException {
      String createMapping = "INSERT INTO user_group_mapping(user_id,group_id )"
         + "VALUES(?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createMapping);
         stmt.setInt(1, key);
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement insertUserGroup(UserGroup group) throws DatabaseException {
      String createUser = "INSERT INTO user_group (group_name) VALUES(?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createUser);
         stmt.setString(1, group.getGroupName());
         
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement insertGroupActionMapping(int key) throws DatabaseException {
      String createMapping = "INSERT INTO group_action_mapping(group_id,action_id )"
         + "VALUES(?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(createMapping);
         stmt.setInt(1, key);
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   
}
