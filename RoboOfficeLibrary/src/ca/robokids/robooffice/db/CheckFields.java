/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db;

import ca.robokids.exception.BadFieldException;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class CheckFields {
   
   public static void checkUser(User user) throws BadFieldException
   {
      checkLength(1,45,user.getUserName(),
                 "Username is empty.","Username must be less than 45 characters.");
      checkLength(1,45,user.getFirstName(),
                 "First name is empty.","First name must be less than 45 characters.");
      checkLength(1,45,user.getLastName(),
                 "Last name is empty.","Last name must be less than 45 characters.");
      checkLength(1,80,user.getAddress(),
                 "Address is empty.","Address must be less than 80 characters.");
      checkLength(1,80,user.getEmail(),
                 "Email is empty.","Email must be less than 45 characters.");
      checkLength(4,80,user.getPassword(),
                 "Password must be more that 4 characters.","Password must be less than 80 characters.");
      checkLength(1,45,user.getPasswordAnswer(),
                 "Password answer is empty","Password must be less than 45 characters.");
      checkLength(9,9,user.getSIN(),
                 "SIN number incorrect.","SIN number incorrect.");
      checkLength(12,12,user.getPhone(),
                 "Phone number format incorrect.","Phone number format incorrect.");
      
      if (user.getEmail().indexOf("@") < 0)
         throw new BadFieldException("Email format incorrect.");
      List<UserGroup> groups = user.getUserGroups();
      if (groups == null || groups.size()<1)
         throw new BadFieldException("User must belong to a user group.");
   }
   
   public static void checkGroup(UserGroup group) throws BadFieldException 
   {
      checkLength(1,30,group.getGroupName(),
                 "Usergroup name is empty","Usergroup name must be less than 30 characters");
      List<Action> actions = group.getActions();
      if (actions == null || actions.size() < 1)
         throw new BadFieldException("User group must have a privilege.");
   }
   
   
   
   private static void checkLength(int min, int max, String field, String minMsg, String maxMsg) throws BadFieldException
   {
      if (field == null || field.length() < min ) {
         throw new BadFieldException(minMsg);
      }
      if (field.length() > max) {
         throw new BadFieldException(maxMsg);
      }
   }
   
}
