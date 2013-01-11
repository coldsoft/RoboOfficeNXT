/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.student;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.schoolmetadata.DayOfWeek;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class StudentActivity {
   
   
   public static List<Timeslot> getTimeslotByDate(Date date) throws DatabaseException 
   {
        Calendar c =  Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        day = (day + 5)%7;        
        DayOfWeek [] days = DayOfWeek.values();
        
        //CHECK FOR HOLIDAY~
        
        return SchoolDBM.getUniqueActiveCourseTimeslot(days[day]);
        
   }
   
   public static void main(String [] arg) throws DatabaseException
   {
      List<Timeslot> t = getTimeslotByDate(new Date(113,0,6));
      for(Timeslot time : t)
      {
         System.out.println(time);
      }
            
         
   }
   
}
