/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.system;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.system.SystemDBM;
import ca.robokids.robooffice.entity.system.Log;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class SystemLog {

   public static List<Log> loadAllLogs(Operation operation,
                                             String affecting_firstName,
                                             String affecting_lastName,
                                             String performing_firstName,
                                             String performing_lastName,
                                             Timestamp rangeStart, 
                                             Timestamp rangeEnd) throws DatabaseException
   {
      return SystemDBM.getAllLogs(operation, affecting_firstName, affecting_lastName, performing_firstName, performing_lastName, rangeStart, rangeEnd);
   }
   
   public static void createEventLog(Operation operation,
      String detail)
   {
      try {
         createLog(false,UserActivity.loginUser.getUser_id(), operation,detail, UserActivity.loginUser.getUser_id());
      } catch (BadFieldException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DatabaseException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
   public static void createUserLog(
      Operation operation,
      String detail,
      int affecting_user_id)  {
      try {
         createLog(false,UserActivity.loginUser.getUser_id(), operation,detail, affecting_user_id);
      } catch (BadFieldException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DatabaseException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public static void createStudentLog(
      Operation operation,
      String detail,
      int affecting_student_id) {
      try {
         createLog(true,UserActivity.loginUser.getUser_id(), operation,detail, affecting_student_id);
      } catch (BadFieldException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      } catch (DatabaseException ex) {
         Logger.getLogger(SystemLog.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   
   private static void createLog(
      boolean affecting_student,
      int performing_user_id,
      Operation operation,
      String detail,
      int affecting_person_id) throws BadFieldException, DatabaseException {
      
      //Create log 
      Log log = new Log();
      log.setAffectingStudent(affecting_student);
      log.setAffectingPerson(affecting_person_id);
      log.setDetail(detail);
      log.setOperation(operation);
      log.setPerformingUser(performing_user_id);
      //Get current system timestamp
      long time = System.currentTimeMillis();
      Timestamp timestamp = new java.sql.Timestamp(time);
      log.setTimeCreated(timestamp);
    
      CheckFields.checkLog(log);
      
      SystemDBM.createLog(log);
      
      
   }
   
   
}
