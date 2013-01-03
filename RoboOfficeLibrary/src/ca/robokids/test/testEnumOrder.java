/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.robooffice.entity.schoolmetadata.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class testEnumOrder {
   
   public static void main(String args[])
   {
      List<DayOfWeek> days = new ArrayList();
      days.add(DayOfWeek.Tue);
      days.add(DayOfWeek.Fri);
      days.add(DayOfWeek.Mon);
      
      
      Collections.sort(days);
      System.out.println(days.get(0));
      
   }
   
}
