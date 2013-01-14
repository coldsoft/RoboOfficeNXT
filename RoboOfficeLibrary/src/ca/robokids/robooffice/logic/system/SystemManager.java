/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.system;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseConfig;
import ca.robokids.robooffice.db.DatabaseManager;

/**
 *
 * @author Coldsoft
 */
public class SystemManager {
   
   private static final String DEFAULT_BACKUP_LOCATION = "C:";
   private static final String DEFAULT_BACKUP_FILENAME = "RoboOffice_backup.sql";
   public static String backupDatabase(String backupFilePath) throws DatabaseException
   {
      if (backupFilePath == null)
      {
         backupFilePath = DEFAULT_BACKUP_LOCATION;
      }
      DatabaseManager.databaseBackup("\""+ backupFilePath + "\\"+DEFAULT_BACKUP_FILENAME + "\"");
      return backupFilePath + "\\"+ DEFAULT_BACKUP_FILENAME;
   }

   
}
