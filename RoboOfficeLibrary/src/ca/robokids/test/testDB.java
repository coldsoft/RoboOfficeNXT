/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class testDB {

   public static void main(String arg[]) {
      User temp = new User();
      temp.setUserName("bqian");
      temp.setPassword("bqian");
      PasswordQuestion question = new PasswordQuestion();
      question.setPassword_qestion_id(3);
      temp.setPasswordQuestion(question);
      temp.setPasswordAnswer("Qian");
      temp.setFirstName("Brian");
      temp.setLastName("Qian");
      temp.setEmail("N/A");
      temp.setAddress("N/A");
      temp.setSIN("N/A");
      temp.setPhone("N/A");
      
      List<Action> actions = new ArrayList();
      Action a = new Action();
      a.setAction_id(1);
      actions.add(a);
      UserGroup g = new UserGroup();
      g.setGroup_id(1);
      g.setGroupName("Training Teacher");
      

      try {
         UserDBM.createUserGroup(g, actions);
      } catch (DatabaseException ex) {
         System.out.println(ex.getMessage());
      }
      
   }
}
