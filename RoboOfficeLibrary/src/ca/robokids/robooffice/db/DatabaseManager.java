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
}
