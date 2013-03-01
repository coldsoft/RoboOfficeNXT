/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db;

import ca.robokids.exception.DatabaseException;
import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class DatabaseManager {

   public static Connection conn = null;

   public static Connection getConnection() {


      if (conn != null) {
         try {
            //if connection is not closed, return connection
            if (!conn.isClosed()) {
               return conn;
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }

      }

      Properties dbProperties = new Properties();
      try {
         dbProperties = DatabaseConfig.getConfig();
      } catch (IOException ex) {
         ex.printStackTrace();
      }

      String url = "jdbc:mysql://" + dbProperties.getProperty("host") + ":"
         + dbProperties.getProperty("port") + "/" + dbProperties.getProperty("database");
      String userName = dbProperties.getProperty("username");
      String password = dbProperties.getProperty("password");
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         DriverManager.setLoginTimeout(DatabaseConfig.getTimeout());
         conn = DriverManager.getConnection(url, userName, password);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return conn;
   }

   public static int executeGetPK(PreparedStatement stmt) throws DatabaseException {
      int key = -1;
      try {
         stmt.executeUpdate();
         key = getGeneratedKey(stmt);
         return key;

      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      } finally {
         if (stmt != null) {
            try {
               stmt.close();

            } catch (SQLException sqlEx) {
               throw new DatabaseException("Statement Close Error.");
            }
         }
         //closeConnection();
      }
   }

   public static int executeUpdate(PreparedStatement stmt) throws DatabaseException {

      try {
         return stmt.executeUpdate();


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      } finally {
         if (stmt != null) {
            try {
               stmt.close();
            } catch (SQLException sqlEx) {
               throw new DatabaseException("Statement Close Error.");
            }
         }
         //closeConnection();
      }


   }

   public static CachedRowSet executeQuery(PreparedStatement stmt) throws DatabaseException {

      CachedRowSet crs = null;
      ResultSet rs = null;

      try {
         rs = stmt.executeQuery();
         crs = new CachedRowSetImpl();
         crs.populate(rs);
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException sqlEx) {
               throw new DatabaseException("Resultset Close Error.");
            }
         }

         //closeConnection();
      }
      return crs;
   }

   public static PreparedStatement getPreparedStatement(String query) throws SQLException {
      return getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
   }

   public static int getGeneratedKey(PreparedStatement stmt) throws SQLException {
      ResultSet rs = stmt.getGeneratedKeys();
      rs.first();
      return rs.getInt(1);
   }

   private static void closeConnection() {
      try {
         if (conn != null) {
            if (!conn.isClosed()) {
               conn.close();
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   
   public static void databaseBackup(String path) throws DatabaseException
   {
      Properties dbProperties = new Properties();
      try {
         dbProperties = DatabaseConfig.getConfig();
      } catch (IOException ex) {
         ex.printStackTrace();
      }

      String host = dbProperties.getProperty("host");
      String port = dbProperties.getProperty("port");
      String database = dbProperties.getProperty("database");
      String userName = dbProperties.getProperty("username");
      String password = dbProperties.getProperty("password");
      String mySQLdump = dbProperties.getProperty("mysql_dump");
      if (!DatabaseManager.backupDB(mySQLdump, database, host, port, userName, password, path))
         throw new DatabaseException("Can't create backup.");
   }
   
   public static void databaseRestore(String path) throws DatabaseException
   {
      Properties dbProperties = new Properties();
      try {
         dbProperties = DatabaseConfig.getConfig();
      } catch (IOException ex) {
         ex.printStackTrace();
      }


      String database = dbProperties.getProperty("database");
      String userName = dbProperties.getProperty("username");
      String password = dbProperties.getProperty("password");
      String mySQL = dbProperties.getProperty("mysql");
      if (!DatabaseManager.restoreDB(mySQL, database, userName, password, path))
         throw new DatabaseException("Can't create backup.");
   }
   private static boolean backupDB(String mySQLdump,String dbName, String host, String port, String dbUserName, String dbPassword, String path) throws DatabaseException {
      
      String executeCmd = mySQLdump + " -P " + port + " -h " + host+ " -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
      Process runtimeProcess;
      try {
         System.out.println("Start to backup.");
         
         runtimeProcess = Runtime.getRuntime().exec(executeCmd);
         int processComplete = runtimeProcess.waitFor();
         if (processComplete == 0) {
            System.out.println("Backup created successfully in " + path);
            return true;
         } else {
            System.out.println("Could not create the backup");
         }
      } catch (IOException | InterruptedException ex) {
         ex.printStackTrace();
         throw new DatabaseException(ex.getMessage());
      }
      return false;
   }
   
   public static boolean restoreDB(String mysql, String dbName, String dbUserName, String dbPassword, String source) throws DatabaseException {

        String[] executeCmd = new String[]{mysql, " --user=" + dbUserName, " --password=" + dbPassword, dbName," -e", " source "+source};

        Process runtimeProcess;
        try {

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup restored successfully");
                return true;
            } else {
                System.out.println("Could not restore the backup");
            }
        } catch (IOException | InterruptedException ex) {
         ex.printStackTrace();
         throw new DatabaseException(ex.getMessage());
      }

        return false;
    }

   public static void main(String args[]) 
   {

      Properties dbProperties = new Properties();
      try {
         dbProperties = DatabaseConfig.getConfig();
      } catch (IOException ex) {
         ex.printStackTrace();
      }


      String userName = dbProperties.getProperty("username");
      String password = dbProperties.getProperty("password");
      String mySQL = dbProperties.getProperty("mysql");
      try {
         DatabaseManager.restoreDB(mySQL, "new_database", userName, password, "\"C:/table.sql\"");
      } catch (DatabaseException ex) {
         Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
      }
       
      
   }

   public static void testConnectivity() throws DatabaseException {
      getConnection();
      if (conn == null)
         throw new DatabaseException("Database connection failed.\n check database.properties for database settings");
   }
   
   
   
}
