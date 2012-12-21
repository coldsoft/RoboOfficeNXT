/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.system;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.system.Log;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
   
   private static PreparedStatement insertLog(Log log) throws DatabaseException
   {
      try {
         String query = "INSERT INTO system_log (operation_performed, date_created, affecting_person, performing_user) VALUES (?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1,log.getOperation());
         stmt.setTimestamp(2, log.getTimeCreated());
         stmt.setInt(3,log.getAffectingPerson());
         stmt.setInt(4,log.getPerformingUser());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
         
      
   }
   
}
