/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.student;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.studentmanagement.ProgressDBM;
import ca.robokids.robooffice.db.studentmanagement.StudentDBM;
import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.entity.student.ClassRecord;
import ca.robokids.robooffice.entity.student.CourseProgress;
import ca.robokids.robooffice.entity.student.Progress;
import ca.robokids.robooffice.entity.student.Student;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class StudentProgressManager {

   private static final Color NORMAL_COLOR = Color.WHITE;
   private static final Color OVERDUE = Color.WHITE;;
   private static final Color MAKEUP_COLOR = Color.GREEN;

   
   
   
   
   
   public static Color getClassRecordColor(ClassRecord cr) {
      if (cr.isOverdue())
         return OVERDUE;
      if (cr.isMakeupClass())
         return MAKEUP_COLOR;
      return NORMAL_COLOR;
   }

   public static List<Progress> getProgressByClassroomAndTimeslot(Classroom cr, Timeslot timeslot) {
      return null;
   }
   
   public static int addNewCourseProgress(CourseProgress cp) throws DatabaseException
   {
      int newID = ProgressDBM.createCourseProgress(cp);
      cp.setProgressID(newID);
      Student s= cp.getStudent();
      if (s.isProspective() || !s.isActive() )
      {
         //Change student prospective to false, active to true
         StudentDBM.modifyStudentStatus(s.getStudent_id(), true, false);
      }
      return newID;
   }
   
 

   public static List<CourseProgress> getActiveCourseProgresses(Student s) throws DatabaseException {
      return ProgressDBM.getAllCourseProgressByStudentID(s.getStudent_id(), true);
   }
  
}
