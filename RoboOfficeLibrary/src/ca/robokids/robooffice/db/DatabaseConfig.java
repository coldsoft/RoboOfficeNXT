
package ca.robokids.robooffice.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Coldsoft
 */
public class DatabaseConfig {
   
   private static Properties pro = new Properties();
   
   public static Properties getConfig() throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      return pro;
   }
   public static void setUser(String user,String comment) throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      pro.setProperty("user", user);
      pro.store(new FileOutputStream("database.properties"), comment);
   }
   public static void setHost(String host,String comment) throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      pro.setProperty("host", host);
      pro.store(new FileOutputStream("database.properties"), comment);
   }
   public static void setPort(String port,String comment) throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      pro.setProperty("host", port);
      pro.store(new FileOutputStream("database.properties"), comment);
   }
   public static void setDatabase(String database,String comment) throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      pro.setProperty("host", database);
      pro.store(new FileOutputStream("database.properties"), comment);
   }
   public static void setPassword(String password,String comment) throws IOException
   {
      pro.load(new FileInputStream("database.properties"));
      pro.setProperty("host", password);
      pro.store(new FileOutputStream("database.properties"), comment);
   }
   
}
