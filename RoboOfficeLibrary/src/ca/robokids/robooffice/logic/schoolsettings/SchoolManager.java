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
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.logic.system.SystemLog;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */


public class SchoolManager {

   private static List<ProgressReportType> reportTypes;
   private static List<Course> courses;
   private static List<Membership> memberships;
      
   public static List<Classroom> loadAllClassroom() throws DatabaseException {
      return SchoolDBM.getAllClassroom();
   }

   public static void createClassroom(Classroom cr) throws BadFieldException, DatabaseException {
      CheckFields.checkClassroom(cr);
      SchoolDBM.createClassroom(cr);

      //Event Logging
      String details = " created a classroom: " + cr.getName() + " capacity:" + cr.getCapacity();
      SystemLog.createEventLog(Operation.CLASSROOM_SETTING, details);

   }

   public static void modifyClassroom(Classroom cr) throws BadFieldException, DatabaseException {
      CheckFields.checkClassroom(cr);
      //!!!!!!!!!!!!!!!!!CHECK FOR CAPACITY < STUDENTS OCCUPY!!!!!!!!!
      SchoolDBM.modifyClassroom(cr);

      //Event Logging
      String details = " modified the classroom: " + cr.getName() + " capacity:" + cr.getCapacity();
      SystemLog.createEventLog(Operation.CLASSROOM_SETTING, details);

   }

   public static List<Activity> deleteClassroom(Classroom cr) throws DatabaseException {
      List<Activity> activity = getAllActivityByClassroom(cr);
      if (activity.size() > 0) {
         return activity;
      }

      SchoolDBM.deleteClassroom(cr.getClassroom_id());

      //Event Logging
      String details = " deleted the classroom: " + cr.getName() + " capacity:" + cr.getCapacity();
      SystemLog.createEventLog(Operation.CLASSROOM_SETTING, details);
      return null;
   }

   public static void forceDeleteClassroom(Classroom cr) throws DatabaseException {
      SchoolDBM.deleteClassroom(cr.getClassroom_id());

      //Event Logging
      String details = " force deleted the classroom: " + cr.getName() + " capacity:" + cr.getCapacity();
      SystemLog.createEventLog(Operation.CLASSROOM_SETTING, details);

   }

   public static List<Activity> getAllActivityByClassroom(Classroom cr) throws DatabaseException {
      List<Activity> list = new ArrayList();

      List<Course> courses = SchoolDBM.getAllCoursesByClassroom(cr);
      List<Membership> memberships = SchoolDBM.getAllMembershipsByClassroom(cr);
      list.addAll(courses);
      list.addAll(memberships);

      return list;
   }

   public static Timeslot addTimeslot(DayOfWeek day, Time start) throws DatabaseException {
      return SchoolDBM.createTimeslot(day, start);

   }

   public static List<ProgressReportType> loadAllProgressReportType() throws DatabaseException {

      if (reportTypes == null) {
         reloadAllProgressReportType();
      }
      return reportTypes;

   }

   public static void reloadAllProgressReportType() throws DatabaseException
   {
      System.out.println("reload All ProgressReportType");
      reportTypes = SchoolDBM.getAllProgressReportType();
   }
   
   public static void deleteProgressReportType(ProgressReportType t) throws DatabaseException {

      SchoolDBM.deleteProgressReportType(t.getReport_type_id());

      for (ProgressReportType old : reportTypes)
      {
         if (old.getReport_type_id()==t.getReport_type_id())
         {
            reportTypes.remove(old);
         }
      }
      //Event Logging
      String details = " deleted progress report type:  " + t.getName();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);

   }

   public static void modifyProgressReportType(ProgressReportType t) throws DatabaseException, BadFieldException {
      CheckFields.checkProgressReportType(t);
      SchoolDBM.modifyProgressReportType(t);

      for (ProgressReportType old : reportTypes)
      {
         if (old.getReport_type_id()==t.getReport_type_id())
         {
            reportTypes.set(reportTypes.indexOf(old),t);
         }
      }
      
      //Event Logging
      String details = " modified progress report type:  " + t.getName();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);
   }

   public static void createProgressReportType(ProgressReportType t) throws BadFieldException, DatabaseException {
      CheckFields.checkProgressReportType(t);
      SchoolDBM.createProgressReportType(t);

      //Event Logging
      String details = " created progress report type:  " + t.getName();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);
   }

   public static ProgressReportType resolveProgressReportType(int report_type_id) throws DatabaseException {
      return SchoolDBM.getProgressReportTypeById(report_type_id);
   }

   public static String getProgressReportTypeName(int report_type_id) throws DatabaseException {
      return SchoolDBM.getProgressReportNameById(report_type_id);
   }

   public static void addMembershipSection(Membership membership, DayOfWeek day, Time start) throws DuplicateNameException, DatabaseException {
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

      //remove local copy timeslot
      for (Membership c : memberships)
      {
         if (c.getId() == membership.getId()) {
            c.getTimeslots().add(newTime);
         }
      }
      
      //Event Logging
      String details = "added " + newTime.toString() + " to " + membership.toString();
      SystemLog.createEventLog(Operation.MEMBERSHIP_SETTING, details);

   }

   
   public static void addCourseSection(Course course, DayOfWeek day, Time start) throws DuplicateNameException, DatabaseException {
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

      //add timeslot to current local copy of courses
      for (Course c : courses)
      {
         if (course.getId() == c.getId()) {
            c.getTimeslots().add(newTime);
         }
      }
      //Event Logging
      String details = "added " + newTime.toString() + " to " + course.toString();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);

   }

   public static void deleteMembershipSection(Membership membership, Timeslot timeslot) throws DatabaseException {
      SchoolDBM.deleteMembershipSection(membership.getId(), timeslot.getTimeslot_id());

      //remove local copy timeslot
      for (Membership c : memberships)
      {
         if (c.getId() == membership.getId()) {
            c.getTimeslots().remove(timeslot);
         }
      }
      //Event Logging
      String details = "deleted" + timeslot.toString() + " from " + membership.toString();
      SystemLog.createEventLog(Operation.MEMBERSHIP_SETTING, details);

   }

   public static void deleteCourseSection(Course course, Timeslot timeslot) throws DatabaseException {
      SchoolDBM.deleteCourseSection(course.getId(), timeslot.getTimeslot_id());

      //remove local copy timeslot
      for (Course c : courses)
      {
         if (c.getId() == course.getId())
            c.getTimeslots().remove(timeslot);
      }
      //Event Logging
      String details = "deleted " + timeslot.toString() + " from " + course.toString();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);
   }

   public static List<Course> loadAllCourses() throws DatabaseException {
      if (courses == null)
         reloadAllCourses();
      return courses;
   }
   
   public static void reloadAllCourses() throws DatabaseException
   {
      System.out.println("reload All Courses");
      courses = SchoolDBM.getAllCoursesByClassroom(null);
   }

   public static boolean deleteCourse(Course course) throws DatabaseException {
      if (SchoolDBM.deleteCourse(course.getId())) {
         
         for (Course old : courses)
      {
         if (old.getId()==course.getId())
         {
            courses.remove(old);
         }
      }
         //Event Logging
         String details = "deleted " + course.toString();
         SystemLog.createEventLog(Operation.COURSE_SETTING, details);
         return true;
      }
      return false;
   }

   public static void modifyCourse(Course course) throws DatabaseException, BadFieldException {
      CheckFields.checkCourse(course);
      SchoolDBM.modifyCourse(course);
      
      for (Course old : courses)
      {
         if (old.getId()==course.getId())
         {
            courses.set(courses.indexOf(old), course);
         }
      }
      //Event Logging
      String details = "modified " + course.toString();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);
   }

   public static Course addCourse(Course newCourse) throws DatabaseException, BadFieldException {
      if (newCourse.getTimeslots().isEmpty()) {
         throw new BadFieldException("Course needs to have at least 1 timeslot assigned.");
      }
      CheckFields.checkCourse(newCourse);
      int newID = SchoolDBM.createCourse(newCourse);
      newCourse.setId(newID);
      
      courses.add(newCourse);
      //Event Logging
      String details = "added " + newCourse.toString();
      SystemLog.createEventLog(Operation.COURSE_SETTING, details);
      return newCourse;
   }

   public static Membership createMembership(Membership newMembership) throws DatabaseException, BadFieldException {
      if (newMembership.getTimeslots().isEmpty()) {
         throw new BadFieldException("Membership needs to have at least 1 timeslot assigned.");
      }
      CheckFields.checkMembership(newMembership);
      int newID = SchoolDBM.createMembership(newMembership);
      newMembership.setId(newID);
      
      memberships.add(newMembership);
      //Event Logging
      String details = "added " + newMembership.toString();
      SystemLog.createEventLog(Operation.MEMBERSHIP_SETTING, details);
      newMembership.setId(newID);

      return newMembership;
   }

   public static void modifyMembership(Membership newMembership) throws DatabaseException, BadFieldException {
      CheckFields.checkMembership(newMembership);
      SchoolDBM.modifyMembership(newMembership);

      for (Membership old : memberships)
      {
         if (old.getId()==newMembership.getId())
         {
            memberships.set(memberships.indexOf(old), newMembership);
         }
      }
      
      String details = "modified " + newMembership.toString();
      SystemLog.createEventLog(Operation.MEMBERSHIP_SETTING, details);


   }

   public static boolean deleteMembership(Membership membership) throws DatabaseException {


      if (SchoolDBM.deleteMembership(membership.getId())) {
         
         for (Membership old : memberships)
      {
         if (old.getId()==membership.getId())
         {
            memberships.remove(old);
         }
      }
         //Event Logging
         String details = "deleted " + membership.toString();
         SystemLog.createEventLog(Operation.MEMBERSHIP_SETTING, details);
         return true;
      }
      return false;
   }

   public static List<Membership> loadAllMemberships() throws DatabaseException {
      if (memberships == null)
         reloadAllMemberships();
      return memberships;
   }
   public static void reloadAllMemberships() throws DatabaseException
   {
      System.out.println("reload All Memberships");
      memberships = SchoolDBM.getAllMembershipsByClassroom(null);
   }

   public static List<Project> loadAllMembershipProject(Membership membership) throws DatabaseException {
      return SchoolDBM.getAllMembershipProjects(membership.getId());
   }

   public static void deleteMembershipProject(Project p) throws DatabaseException {
      SchoolDBM.deleteMembershipProject(p.getProject_id());
      //Event Logging
      String details = "deleted membership project " + p.getName();
      SystemLog.createEventLog(Operation.PROJECT_SETTING, details);

   }

   public static void addMembershipProject(Membership m, Project p) throws DatabaseException, BadFieldException {
      CheckFields.checkProjects(p);
      SchoolDBM.createMembershipProject(m.getId(), p.getName());
      //Event Logging
      String details = "added project " + p.getName() + " to membership " + m.toString();
      SystemLog.createEventLog(Operation.PROJECT_SETTING, details);
   }

   public static List<Project> loadAllCourseProject(Course course) throws DatabaseException {
      return SchoolDBM.getAllProjects(course.getId());
   }

   public static void deleteCourseProject(Project p) throws DatabaseException {
      SchoolDBM.deleteCourseProject(p.getProject_id());
      //Event Logging
      String details = "deleted course project " + p.getName();
      SystemLog.createEventLog(Operation.PROJECT_SETTING, details);
   }

   public static void addCourseProject(Course c, Project p) throws DatabaseException, BadFieldException {
      CheckFields.checkProjects(p);
      SchoolDBM.createCourseProject(c.getId(), p.getName());
      //Event Logging
      String details = "added project " + p.getName() + " to course " + c.toString();
      SystemLog.createEventLog(Operation.PROJECT_SETTING, details);
      
   }

   public static void main(String args[]) throws DatabaseException {
      System.out.println(loadAllMemberships().size());

   }

   public static Membership getMembershipByID(int membershipID) throws DatabaseException {
      return SchoolDBM.getMembershipByID(membershipID);
   }

   public static Course getCourseByID(int id) throws DatabaseException {
      return SchoolDBM.getCourseByID(id);
   }
}
