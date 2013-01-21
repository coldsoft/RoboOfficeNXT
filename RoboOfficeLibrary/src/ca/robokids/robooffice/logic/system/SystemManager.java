/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.system;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseConfig;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.logic.schoolsettings.SchoolManager;
import ca.robokids.robooffice.logic.usermanagement.UserManager;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class SystemManager {
   
   private static final String DEFAULT_BACKUP_LOCATION = "C:";
   private static final String DEFAULT_BACKUP_FILENAME = "RoboOffice_backup.sql";
   
  
   
   public static void startup()
   {
      testDatabaseConnectivity();
      
      startPeriodicalReloadingThread();
   } 
   
   private static void testDatabaseConnectivity()
   {
      
   }
   public static String backupDatabase(String backupFilePath) throws DatabaseException
   {
      if (backupFilePath == null)
      {
         backupFilePath = getDefaultBackupLocation();
      }
      
      String filename = getBackupFilename();
      
      DatabaseManager.databaseBackup("\""+ backupFilePath + "\\"+ filename + "\"");
      return backupFilePath + "\\"+ filename;
   }

   private static String getBackupFilename()
   {
      long time = System.currentTimeMillis();
      Date date = new Date(time);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd-hh-mm-ss");
      
      return  sdf.format(date) + "-"+DEFAULT_BACKUP_FILENAME;
   }
   public static String getDefaultBackupLocation()
   {
      Properties dbProperties = new Properties();
      try {
         dbProperties = DatabaseConfig.getConfig();
         return  dbProperties.getProperty("defaultBackupLocation");
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      return DEFAULT_BACKUP_LOCATION;
   }

   private static void startPeriodicalReloadingThread() {
      Thread t = new Thread () {
         @Override
         public void run()
         {
            while(true){
               try {
                  Thread.sleep(5000);
                  UserManager.reloadAllUsers();
                  UserManager.reloadAllUsergroup();
                  UserManager.reloadAllActions();
                  SchoolManager.reloadAllCourses();
                  SchoolManager.reloadAllMemberships();
                  SchoolManager.reloadAllProgressReportType();
               } catch (InterruptedException | DatabaseException ex) {
                  Logger.getLogger(SystemManager.class.getName()).log(Level.SEVERE, null, ex);
               }
            
            }
         }
      };
      
      t.start();
   }
   
}
