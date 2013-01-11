/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.schoolsettings;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.exception.DuplicateNameException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.schoolmetadata.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
     
      List<Course> courses = SchoolDBM.getAllCoursesByClassroom(cr);
      //List<Membershp> memberships = SchoolDBM.getAllMembership(cr);
      list.addAll(courses);
      //list.addAll(memberships);
      
      return list;
   }
   public static Timeslot addTimeslot(DayOfWeek day, Time start) throws DatabaseException
   {
      return SchoolDBM.createTimeslot(day, start);
   }
   public static List<ProgressReportType> loadAllProgressReportType() throws DatabaseException
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

   public static ProgressReportType resolveProgressReportType(int report_type_id) throws DatabaseException {
      return SchoolDBM.getProgressReportTypeById(report_type_id);
   }
   
   public static String getProgressReportTypeName(int report_type_id) throws DatabaseException
   {
      return SchoolDBM.getProgressReportNameById(report_type_id);
   }
   
   public static void addMembershipSection(Membership membership, DayOfWeek day, Time start) throws DuplicateNameException, DatabaseException
   {
      Timeslot newTime = new Timeslot();
      newTime.setDayOfWeek(day);
      newTime.setStart(start);
//      //check if the time is already a course section
//      for (Timeslot t : course.getTimeslots())
//      {
//         if (t.equals(newTime)) {
//            throw new DuplicateNameException();
//         }
//      }
      //get Timeslot / create a new timeslot
      newTime = SchoolDBM.createTimeslot(day, start);
      //create a new mapping using the timeslot
      SchoolDBM.createMembershipSection(membership.getId(), newTime.getTimeslot_id());
      
   };
   public static void addCourseSection(Course course, DayOfWeek day, Time start) throws DuplicateNameException, DatabaseException
   {
      Timeslot newTime = new Timeslot();
      newTime.setDayOfWeek(day);
      newTime.setStart(start);
//      //check if the time is already a course section
//      for (Timeslot t : course.getTimeslots())
//      {
//         if (t.equals(newTime)) {
//            throw new DuplicateNameException();
//         }
//      }
      //get Timeslot / create a new timeslot
      newTime = SchoolDBM.createTimeslot(day, start);
      //create a new mapping using the timeslot
      SchoolDBM.createCourseSection(course.getId(), newTime.getTimeslot_id());
      
   }
   
   public static void deleteMembershipSection(Membership membership, Timeslot timeslot) throws DatabaseException
   {
      SchoolDBM.deleteMembershipSection(membership.getId(), timeslot.getTimeslot_id());
      
   }
   public static void deleteCourseSection(Course course, Timeslot timeslot) throws DatabaseException
   {
      SchoolDBM.deleteCourseSection(course.getId(), timeslot.getTimeslot_id());
      
   }

   public static List<Course> loadAllCourses() throws DatabaseException
   {
      return SchoolDBM.getAllCoursesByClassroom(null);
   }

   public static boolean deleteCourse(Course course) throws DatabaseException {
      return SchoolDBM.deleteCourse(course.getId());
   }

   public static void modifyCourse(Course newCourse) throws DatabaseException {
      SchoolDBM.modifyCourse(newCourse);
   }

   public static Course addCourse(Course newCourse) throws DatabaseException, BadFieldException {
      if (newCourse.getTimeslots().isEmpty())
         throw new BadFieldException("Course needs to have at least 1 timeslot assigned.");
      CheckFields.checkCourse(newCourse);
      int newID = SchoolDBM.createCourse(newCourse);
      newCourse.setId(newID);
      return newCourse;
   }

   public static Membership createMembership(Membership newMembership) throws DatabaseException, BadFieldException {
      if (newMembership.getTimeslots().isEmpty())
         throw new BadFieldException("Membership needs to have at least 1 timeslot assigned.");
      CheckFields.checkMembership(newMembership);
      int newID = SchoolDBM.createMembership(newMembership);
      newMembership.setId(newID);
      return newMembership;
   }

   public static void modifyMembership(Membership newMembership) throws DatabaseException{
      SchoolDBM.modifyMembership(newMembership);
   }

   public static boolean deleteMembership(Membership membership) throws DatabaseException{
      return SchoolDBM.deleteMembership(membership.getId());
   }

   public static List<Membership> loadAllMemberships() throws DatabaseException{
      return SchoolDBM.getAllMembershipsByClassroom(null);
   }
   public static List<Project> loadAllMembershipProject(Membership membership) throws DatabaseException
   {
      return SchoolDBM.getAllMembershipProjects(membership.getId());
   }
   public static void deleteMembershipProject(Project p) throws DatabaseException
   {
      SchoolDBM.deleteMembershipProject(p.getProject_id());
   }
   public static void addMembershipProject(Membership m,Project p) throws DatabaseException, BadFieldException
   {
      CheckFields.checkProjects(p);
      SchoolDBM.createMembershipProject(m.getId(),p.getName());
   }
   public static List<Project> loadAllCourseProject(Course course) throws DatabaseException
   {
      return SchoolDBM.getAllProjects(course.getId());
   }
   public static void deleteCourseProject(Project p) throws DatabaseException
   {
      SchoolDBM.deleteCourseProject(p.getProject_id());
   }
   public static void addCourseProject(Course c,Project p) throws DatabaseException, BadFieldException
   {
      CheckFields.checkProjects(p);
      SchoolDBM.createCourseProject(c.getId(),p.getName());
   }
   
   public static void main(String args[]) throws DatabaseException
   {
      System.out.println(loadAllMemberships().size());
      
   }
}
