/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.system;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.system.Log;
import ca.robokids.robooffice.entity.system.Operation;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class SystemDBM {
   
   
   public static void createLog(Log log) throws DatabaseException
   {
      
      PreparedStatement stmt = insertLog(log);
      DatabaseManager.executeGetPK(stmt);

      
   }
   
   public static List<Log> getAllLogs( Operation operation,
                                             String affecting_firstName,
                                             String affecting_lastName,
                                             String performing_firstName,
                                             String performing_lastName,
                                             Timestamp rangeStart, 
                                             Timestamp rangeEnd) throws DatabaseException
   {      
      List<Log> userLog = SystemDBM.getAllLogs("user_system_log_view",operation,affecting_firstName,affecting_lastName,performing_firstName,performing_lastName,rangeStart,rangeEnd);
      List<Log> studentLog = SystemDBM.getAllLogs("student_system_log_view",operation,affecting_firstName,affecting_lastName,performing_firstName,performing_lastName,rangeStart,rangeEnd);
   
      if (userLog.size() > studentLog.size()){
         userLog.addAll(studentLog);
         return userLog;
      }
      
      studentLog.addAll(userLog);
      return studentLog;
   }
   
   private static List<Log> getAllLogs(String tablename, Operation operation,
                                             String affecting_firstName,
                                             String affecting_lastName,
                                             String performing_firstName,
                                             String performing_lastName,
                                             Timestamp rangeStart, 
                                             Timestamp rangeEnd) throws DatabaseException
   {
      List<Log> logs = new ArrayList();
      
      try {
         String query = "SELECT * FROM " + tablename + " WHERE ";
         if (operation != null)
         {
            query += " operation_performed = \'" + operation.toString() + "\' AND";
         }
         
         if (affecting_firstName != null)
         { 
            query += " affecting_fname = '" + affecting_firstName + "' AND";
         }
         if (affecting_lastName != null)
         {
            query += " affecting_lname = '" + affecting_lastName + "' AND";        
         }
         if (performing_firstName != null)
         {
            query += " performing_fname = '" + performing_firstName + "' AND";
         }
         if (performing_lastName != null)
         {
            query += " performing_lname = '" + performing_lastName + "' AND ";
         }
         
         query += " date_created >= ? AND date_created <= ? ORDER BY date_created DESC";
         System.out.println(query);
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setTimestamp(1, rangeStart);
         stmt.setTimestamp(2, rangeEnd);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            Log log = new Log();
            log.setLog_id(crs.getInt("system_log_id"));
            log.setOperation(Operation.getValue(crs.getString("operation_performed")));
            log.setDetail(crs.getString("detail"));
            log.setTimeCreated(crs.getTimestamp("date_created"));
            log.setPerformingUser(crs.getInt("performing_user"));
            log.setPerforming_firstName(crs.getString("performing_fname"));
            log.setPerforming_lastName(crs.getString("performing_lname"));
            log.setAffectingPerson(crs.getInt("affecting_person"));
            log.setAffecting_firstName(crs.getString("affecting_fname"));
            log.setAffecting_lastName(crs.getString("affecting_lname"));
            
            logs.add(log);
         }
         return logs;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   
   private static PreparedStatement insertLog(Log log) throws DatabaseException
   {
      try {
         String query = "INSERT INTO system_log (operation_performed, date_created, affecting_person, performing_user,detail,affecting_student) VALUES (?,?,?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1,log.getOperation().toString());
         stmt.setTimestamp(2, log.getTimeCreated());
         stmt.setInt(3,log.getAffectingPerson());
         stmt.setInt(4,log.getPerformingUser());
         stmt.setString(5,log.getDetail());
         stmt.setBoolean(6,log.isAffectingStudent());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
         
      
   }
   
   
   public static void main(String arg[]) throws DatabaseException
   {
      Date start = new Date(100,1,1);
      Date end = new Date(200,1,1);

      System.out.println(SystemDBM.getAllLogs(Operation.LOGIN, "James", null, null, null, new Timestamp(start.getTime()), new Timestamp(end.getTime())).size());
   }
}
