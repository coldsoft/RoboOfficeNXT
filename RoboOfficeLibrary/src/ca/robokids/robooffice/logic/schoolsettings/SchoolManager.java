/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.schoolsettings;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.schoolmetadata.Activity;
import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.schoolmetadata.Course;
import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class SchoolManager {
   
   public static List<Classroom> loadAllClassroom() throws DatabaseException
   {
      return SchoolDBM.getAllClassroom();
   }
   
   public static void createClassroom(Classroom cr) throws BadFieldException, DatabaseException
   {
      CheckFields.checkClassroom(cr);
      SchoolDBM.createClassroom(cr);
      
   }
   public static void modifyClassroom(Classroom cr) throws BadFieldException, DatabaseException
   {
      CheckFields.checkClassroom(cr);
      //!!!!!!!!!!!!!!!!!CHECK FOR CAPACITY < STUDENTS OCCUPY!!!!!!!!!
      SchoolDBM.modifyClassroom(cr);
   }
   public static List<Activity> deleteClassroom(Classroom cr) throws DatabaseException
   {
      List<Activity> activity = getAllActivityByClassroom(cr);
      if (activity.size() > 0)
         return activity;
      
      SchoolDBM.deleteClassroom(cr.getClassroom_id());
      return null;
   }
   public static void forceDeleteClassroom(Classroom cr) throws DatabaseException
   {
      SchoolDBM.deleteClassroom(cr.getClassroom_id());
   }
   
   public static List<Activity> getAllActivityByClassroom(Classroom cr) throws DatabaseException
   {
      List<Activity> list = new ArrayList();
     
      List<Course> courses = SchoolDBM.getAllCourses(cr);
      //List<Membershp> memberships = SchoolDBM.getAllMembership(cr);
      list.addAll(courses);
      //list.addAll(memberships);
      
      return list;
   }
   
   public static List<ProgressReportType> getAllProgressReportType() throws DatabaseException
   {

         List<ProgressReportType> reportTypes = SchoolDBM.getAllProgressReportType();
         return reportTypes;

   }
   
   public static void deleteProgressReportType(ProgressReportType t) throws DatabaseException
   {

         SchoolDBM.deleteProgressReportType(t.getReport_type_id());

      
   }
   
   public static void modifyProgressReportType(ProgressReportType t) throws DatabaseException, BadFieldException
   {
      CheckFields.checkProgressReportType(t);
      SchoolDBM.modifyProgressReportType(t);
   }

   public static void createProgressReportType(ProgressReportType t) throws BadFieldException, DatabaseException {
      CheckFields.checkProgressReportType(t);
      SchoolDBM.createProgressReportType(t);
   }
   
}
