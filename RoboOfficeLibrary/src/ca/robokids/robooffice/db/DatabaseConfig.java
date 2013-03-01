
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
      if (pro.isEmpty()) {
         pro.load(new FileInputStream("database.properties"));
      }
      return pro;
   }
   
   public static void saveConfig(String comment) throws IOException
   {
      pro.store(new FileOutputStream("database.properties"),comment);
   }
   public static String getUsername() throws IOException
   {
      return getConfig().getProperty("username");     
   }
   public static void setUsername(String user,String comment) throws IOException
   {
      getConfig().setProperty("username", user);
      saveConfig(comment);
   }
   public static void setHost(String host,String comment) throws IOException
   {
      getConfig().setProperty("host", host);
      saveConfig(comment);
   }
   public static String getHost() throws IOException
   {
      return getConfig().getProperty("host");     
   }
   public static void setPort(String port,String comment) throws IOException
   {
      getConfig().setProperty("port", port);
      saveConfig(comment);
      
   }
   public static String getPort() throws IOException
   {
      return getConfig().getProperty("port");     
   }
   public static void setDatabase(String database,String comment) throws IOException
   {
      getConfig().setProperty("database", database);
      saveConfig(comment);
   }
   public static String getDatabase() throws IOException
   {
      return getConfig().getProperty("database");     
   }
   public static void setPassword(String password,String comment) throws IOException
   {
      getConfig().setProperty("password", password);
      saveConfig(comment);
   }
   public static String getPassword() throws IOException
   {
      return getConfig().getProperty("password");     
   }
   public static int getRefreshInterval() throws IOException
   {
      return Integer.valueOf(getConfig().getProperty("refreshInterval"));
   }
   public static void setRefreshInterval(int interval, String comment) throws IOException
   {
      getConfig().setProperty("refreshInterval", String.valueOf(interval));
      saveConfig(comment);
   }

   public static String getBackupLocation() throws IOException {
      return getConfig().getProperty("defaultBackupLocation"); 
   }
   public static void setBackupLocation(String location,String comment) throws IOException
   {
      getConfig().setProperty("defaultBackupLocation", String.valueOf(location));
      saveConfig(comment);
   }

   public static int getTimeout() throws IOException {
      return Integer.valueOf(getConfig().getProperty("timeout"));
   }
}
