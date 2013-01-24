/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.entity.student.Progress;
import ca.robokids.util.Attendance;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class AttendanceExcelDriver {
   
   public static void main(String args[]) throws IOException
   {
      Date date = new Date(System.currentTimeMillis());
      SimpleDateFormat sdf= new SimpleDateFormat("MMM-dd-yyyy");
      Timeslot timeslot= SampleEntities.getRandomTimeslot();
      List<Classroom> classrooms = SampleEntities.getClassrooms();
      Attendance attendance = new Attendance(sdf.format(date),timeslot.toString());
      for (Classroom cr : classrooms)
      {
         List<Progress> p = SampleEntities.getProgresses();
         attendance.addClassroom(cr, p);
      }
      
      attendance.openExcel();
      
   }
}
