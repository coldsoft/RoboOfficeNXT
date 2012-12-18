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
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class DatabaseManager {
   
   public static Connection conn = null;
   
   
   public static Connection getConnection()
   {
      
      if (conn != null)
         return conn;
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
   

    public static int executeUpdate(PreparedStatement stmt) throws DatabaseException
    {
       int rowChanged = 0;
       try
       {
          rowChanged = stmt.executeUpdate();
          
       }catch (SQLException ex)
        {
            throw new DatabaseException("SQL Error." + ex.getMessage());
        } finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                    throw new DatabaseException("Statement Close Error.");
                }
            }
        }
       return rowChanged;
       
    }
    
 
    public static CachedRowSet executeQuery(PreparedStatement stmt) throws DatabaseException
    {
        
        CachedRowSet crs = null;       
        ResultSet rs = null;
        
        try
        {
           rs = stmt.executeQuery();            
                crs = new CachedRowSetImpl();
                crs.populate(rs);           
        } catch (SQLException ex)
        {
            throw new DatabaseException("SQL Error." + ex.getMessage());
        } finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                } catch (SQLException sqlEx)
                {
                    throw new DatabaseException("Statement Close Error.");
                }
            }
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException sqlEx)
                {
                    throw new DatabaseException("Resultset Close Error.");
                }
            }
        }
        return crs;
    }
}
