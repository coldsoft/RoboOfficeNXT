/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.robooffice.db.DatabaseManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Coldsoft
 */
public class testProperties {
   
   public static Properties pro = new Properties();
   
   public static void main(String arg[]) throws IOException, SQLException
   {
      Connection conn = DatabaseManager.getConnection();
      System.out.println(conn.getCatalog());
      
   }
   
}
