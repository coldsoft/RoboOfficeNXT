/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.student;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.schoolmetadata.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class AttendanceManager {
   
   
   public static List<Timeslot> getTimeslotByDate(Date date) throws DatabaseException 
   {
       
        //CHECK FOR HOLIDAY~
        
        return SchoolDBM.getUniqueActivitiesTimeslot(dateToDayOfWeek(date));
        
   }
   
   public static List<Classroom> getClassroomByTimeslot(Timeslot t) throws DatabaseException
   {
      List<Course> courses = SchoolDBM.getAllCoursesByTimeslot(t);
      List<Membership> memberships = SchoolDBM.getAllMembershipByTimeslot(t);
      
      List<Classroom> classrooms = new ArrayList(); 
      for (Course course : courses)
      {
         addUniqueClassroom(course.getClassroom(),classrooms);
      }
      for (Membership membership : memberships)
      {
         addUniqueClassroom(membership.getClassroom(),classrooms);
      }
      return classrooms;
   }
   
   private static void addUniqueClassroom(Classroom cr, List<Classroom> classrooms)
   {
      for (Classroom c : classrooms)
      {
         if (c.getClassroom_id() == cr.getClassroom_id())
            return;
         
      }
      classrooms.add(cr);
   }
   public static DayOfWeek dateToDayOfWeek(Date date )
   {
      Calendar c =  Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        day = (day + 5)%7;        
        DayOfWeek [] days = DayOfWeek.values();
        return days[day];
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
