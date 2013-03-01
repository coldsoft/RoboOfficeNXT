/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Coldsoft
 */
public class Utilities {
   
   private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");   
   
   public static String formatDate(Date date)
   {
      return sdf.format(date);
   }
}
