/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.schoolmetadata;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.schoolmetadata.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 * This is the database communication class for School metadata part of the database.<p>
 * Tables manipulating includes <i>courses, classrooms, projects, progress report templates,</i>etc.<p>
 * 
 * Each table in database has five functions:<p>
 * Modify<p> Create.<p>Delete<p>Get by ID<p>Get All.<p>
 * 
 * This class uses the <tt>DatabaseManager</tt> class for execution of Prepared statements.
 * 
 * @author Coldsoft
 */
public class SchoolDBM {

   private static Location location = null;
   
   
   public static List<Membership> getAllMembershipsByClassroom(Classroom cr) throws DatabaseException
   {
      try {
         List<Membership> memberships = new ArrayList();
         String query;

         if (cr == null) {
            query = "SELECT DISTINCT memberships_id FROM membership_view ORDER BY code";
         } else {
            query = "SELECT DISTINCT memberships_id FROM membership_view WHERE classroom_id = " + String.valueOf(cr.getClassroom_id())
               + " ORDER BY code";
         }


         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Membership membership = getMembershipByID(crs.getInt("memberships_id"));
            memberships.add(membership);
         }

         return memberships;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   
   /**
    * Get all courses.If TimeslotID >= 1, all courses of that particular slot will be returned.<p>
    * If TimeslotID is less than 1, all course from the database will be returned 
    * @param timeslotID
    * @return ArrayList of courses
    * @throws DatabaseException
    */
   public static List<Membership> getAllMembershipByTimeslot(Timeslot timeslotID) throws DatabaseException {
      try {
         List<Membership> memberships = new ArrayList();
         String query;

         if (timeslotID.getTimeslot_id() < 1) {
            query = "SELECT DISTINCT memberships_id FROM membership_view ORDER BY code ASC";
         } else {
            query = "SELECT DISTINCT memberships_id FROM membership_view WHERE slot_id = " + String.valueOf(timeslotID.getTimeslot_id())
               + " ORDER BY code ASC";
         }


         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Membership membership = getMembershipByID(crs.getInt("memberships_id"));
            memberships.add(membership);
         }

         return memberships;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   public static Membership getMembershipByID(int membershipID) throws DatabaseException {
      Membership membership = getActiveMembershipByID(membershipID);
      if (membership == null) {
         membership = getDeletedMembershipByID(membershipID);
      }
      return membership;
   }
   public static Membership getDeletedMembershipByID(int membershipID) throws DatabaseException
   {
      try {
         Membership temp = null;
         String getMembershipByID = "SELECT * FROM deleted_membership_view WHERE memberships_id = ? GROUP BY memberships_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getMembershipByID);
         stmt.setInt(1, membershipID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            temp = new Membership();

            Classroom r = new Classroom();
            r.setName(crs.getString("classroom_name"));
            r.setLocation(getLocation());
            r.setCapacity(crs.getInt("capacity"));
            r.setClassroom_id(crs.getInt("classroom_id"));
            r.setDeleted(false);

            temp.setClassroom(r);
            temp.setCode(crs.getString("code"));
            temp.setId(membershipID);
            temp.setDeleted(true);
            temp.setDescription(crs.getString("description"));
            temp.setDuration(crs.getInt("duration"));
            temp.setRate(crs.getFloat("rate"));
            temp.setName(crs.getString("name"));
            temp.setStartDate(crs.getDate("start_date"));
            temp.setEndDate(crs.getDate("endDate"));
            temp.setTimeslots(getAllMembershipTimeslots(membershipID));
         }
         return temp;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   public static Membership getActiveMembershipByID(int membershipID) throws DatabaseException {
      try {
         Membership temp = null;
         String getMembershipByID = "SELECT * FROM membership_view WHERE memberships_id = ? GROUP BY memberships_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getMembershipByID);
         stmt.setInt(1, membershipID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            temp = new Membership();

            Classroom r = new Classroom();
            r.setName(crs.getString("classroom_name"));
            r.setLocation(getLocation());
            r.setCapacity(crs.getInt("capacity"));
            r.setClassroom_id(crs.getInt("classroom_id"));
            r.setDeleted(false);

            temp.setClassroom(r);
            temp.setCode(crs.getString("code"));
            temp.setId(membershipID);
            temp.setDeleted(false);
            temp.setDescription(crs.getString("description"));
            temp.setDuration(crs.getInt("duration"));
            temp.setRate(crs.getFloat("rate"));
            temp.setName(crs.getString("name"));
            temp.setStartDate(crs.getDate("startDate"));
            temp.setEndDate(crs.getDate("endDate"));
            temp.setHasTax(crs.getBoolean("hasTax"));
            temp.setTimeslots(getAllMembershipTimeslots(membershipID));
         }
         return temp;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   

   public static int createMembership(Membership membership) throws DatabaseException {
      PreparedStatement stmt = insertMembership(membership);
      int new_id = DatabaseManager.executeGetPK(stmt);

      for (Timeslot t : membership.getTimeslots()) {
         
         t = createTimeslot(t.getDayOfWeek(),t.getStart());
         createMembershipSection(new_id, t.getTimeslot_id());
      }
      return new_id;
   }
   
   public static void modifyMembership(Membership membership) throws DatabaseException {

      PreparedStatement stmt = updateMembership(membership);
      DatabaseManager.executeUpdate(stmt);

   }
   public static boolean deleteMembership(int membershipID) throws DatabaseException {
      try {
         Membership m = getMembershipByID(membershipID);
         for (Timeslot t : m.getTimeslots())
         {
            deleteMembershipSection(m.getId(),t.getTimeslot_id());
         }
         String query = "UPDATE membership SET deleted = 1 WHERE memberships_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, membershipID);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   public static void createMembershipSection(int membership_id, int timeslot_id) throws DatabaseException {
      try {
         String query;
         if (hasMembershipSection(membership_id, timeslot_id) >= 0)
            query = "UPDATE membership_section SET deleted = 0 WHERE slot_id = ? AND membership_id = ?";
         else
            query = "INSERT INTO membership_section (slot_id, membership_id) VALUES (?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         stmt.setInt(1, timeslot_id);
         stmt.setInt(2, membership_id);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   public static void deleteMembershipSection(int membership_id, int timeslot_id) throws DatabaseException {
      try {
         String delete = "UPDATE membership_section SET deleted = 1 WHERE membership_id = " + String.valueOf(membership_id)
            + " AND slot_id = " + String.valueOf(timeslot_id);
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   public static MembershipSection getMembershipSectionByID(int id) throws DatabaseException
   {
      try {
         MembershipSection ms = null;
         String select = "SELECT * FROM membership_section WHERE section_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setInt(1,id);
         
         ResultSet rs = DatabaseManager.executeQuery(stmt);
         while(rs.next())
         {
            ms = new MembershipSection();
            ms.setDeleted(rs.getBoolean("deleted"));
            ms.setSection_id(id);
            ms.setMembership(getMembershipByID(rs.getInt("membership_id")));
            ms.setTimeslot(getTimeslotByID(rs.getInt("slot_id")));
            
         }
         return ms;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static int hasMembershipSection(int membership_id, int timeslot_id) throws DatabaseException {
       try {
         String query = "SELECT * FROM membership_section WHERE membership_id = ? AND slot_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, membership_id);
         stmt.setInt(2, timeslot_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()){
            return crs.getInt("section_id");
         }
         return -1;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static List<Project> getAllMembershipProjects(int membership_id) throws DatabaseException
   {
      try {
         List<Project> projects = new ArrayList();
         String query;
         if (membership_id > 0)
            query = "SELECT * FROM club_project WHERE membership_id = ? AND deleted = 0 ORDER BY name ASC";
         else
            query = "SELECT * FROM club_project WHERE deleted = 0 ORDER BY name ASC";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         
         stmt.setInt(1, membership_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Project p = new Project();
            p.setDeleted(false);
            p.setActivityID(membership_id);
            p.setName(crs.getString("name"));
            p.setProject_id(crs.getInt("project_id"));
            
            projects.add(p);
         }
         return projects;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static Project getMembershipProjectByID(int projectID) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM club_project WHERE project_id = ? ";
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1,projectID);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Project p = new Project();
            p.setDeleted(crs.getBoolean("deleted"));
            p.setActivityID(crs.getInt("membership_id"));
            p.setName(crs.getString("name"));
            p.setProject_id(crs.getInt("project_id"));
            
            return p;
         }
         return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static void createMembershipProject(int membership_id, String name) throws DatabaseException
   {
      String update;
      if (hasMembershipProject(name,membership_id))
      {
         update = "UPDATE club_project SET deleted = 0 WHERE  name = ? AND membership_id = ?";
      }else
      {
         update = "INSERT INTO club_project (name, membership_id) VALUES (?,?)";
      }
      
      try {
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setString(1,name);
         stmt.setInt(2,membership_id);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static void deleteMembershipProject(int project_id) throws DatabaseException
   {
      try {
         String delete = "UPDATE club_project SET deleted = 1 WHERE project_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         stmt.setInt(1,project_id);
         
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static boolean hasMembershipProject(String name, int membership_id) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM project WHERE name = ? AND membership_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, name);
         stmt.setInt(2, membership_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()){
            return true;
         }
         return false;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   /**
    * Get all courses within a classroom
    * @param cr
    * @return ArrayList of Courses
    * @throws DatabaseException
    */
   public static List<Course> getAllCoursesByClassroom(Classroom cr) throws DatabaseException {
      try {
         List<Course> courses = new ArrayList();
         String query;

         if (cr == null) {
            query = "SELECT DISTINCT course_id FROM course_view ORDER BY code";
         } else {
            query = "SELECT DISTINCT course_id FROM course_view WHERE classroom_id = " + String.valueOf(cr.getClassroom_id())
               + " ORDER BY code";
         }


         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Course course = getCourseByID(crs.getInt("course_id"));
            courses.add(course);
         }

         return courses;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   
   /**
    * Get all courses.If TimeslotID >= 1, all courses of that particular slot will be returned.<p>
    * If TimeslotID is less than 1, all course from the database will be returned 
    * @param timeslotID
    * @return ArrayList of courses
    * @throws DatabaseException
    */
   public static List<Course> getAllCoursesByTimeslot(Timeslot timeslotID) throws DatabaseException {
      try {
         List<Course> courses = new ArrayList();
         String query;

         if (timeslotID.getTimeslot_id() < 1) {
            query = "SELECT DISTINCT course_id FROM course_view ORDER BY code ASC";
         } else {
            query = "SELECT DISTINCT course_id FROM course_view WHERE slot_id = " + String.valueOf(timeslotID.getTimeslot_id())
               + " ORDER BY code ASC";
         }


         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Course course = getCourseByID(crs.getInt("course_id"));
            courses.add(course);
         }

         return courses;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   /**
    * get Course by courseID.Search includes deleted courses as well.
    * @param courseID
    * @return Course object if found. <tt>null</tt> otherwise
    * @throws DatabaseException
    */
   public static Course getCourseByID(int courseID) throws DatabaseException {
      Course course = getActiveCourseByID(courseID);
      if (course == null) {
         course = getDeletedCourseByID(courseID);
      }
      return course;
   }

   
   /**
    * get un-deleted courses by courseID.
    * @param courseID
    * @return Course object if found. <tt>null</tt> otherwise
    * @throws DatabaseException
    */
   public static Course getActiveCourseByID(int courseID) throws DatabaseException {
      try {
         Course temp = null;
         String getCourseByID = "SELECT * FROM course_view WHERE course_id = ? GROUP BY course_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getCourseByID);
         stmt.setInt(1, courseID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            temp = new Course();

            Classroom r = new Classroom();
            r.setName(crs.getString("classroom_name"));
            r.setLocation(getLocation());
            r.setCapacity(crs.getInt("capacity"));
            r.setClassroom_id(crs.getInt("classroom_id"));
            r.setDeleted(false);

            temp.setClassroom(r);
            temp.setCode(crs.getString("code"));
            temp.setId(courseID);
            temp.setDeleted(false);
            temp.setDescription(crs.getString("description"));
            temp.setDuration(crs.getInt("duration"));
            temp.setRate(crs.getFloat("rate"));
            temp.setName(crs.getString("name"));
            temp.setReport_type_id(crs.getInt("report_type_id"));
            temp.setHasTax(crs.getBoolean("hasTax"));
            temp.setTimeslots(getAllCourseTimeslots(courseID));
         }
         return temp;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   
   /**
    * create a new course. Course object must be correctly initialized.
    * @param course
    * @return newly generated courseID from database.
    * @throws DatabaseException
    */
   public static int createCourse(Course course) throws DatabaseException {
      PreparedStatement stmt = insertCourse(course);
      int new_id = DatabaseManager.executeGetPK(stmt);

      for (Timeslot t : course.getTimeslots()) {
         
         t = createTimeslot(t.getDayOfWeek(),t.getStart());
         createCourseSection(new_id, t.getTimeslot_id());
      }
      return new_id;
   }

   /**
    * Modify an existing course.<p>
    * This Method ONLY modifies existing course information. course timeslots is unchanged.
    * @param course
    * @throws DatabaseException
    */
   public static void modifyCourse(Course course) throws DatabaseException {

      //update course fields
      PreparedStatement stmt = updateCourse(course);
      DatabaseManager.executeUpdate(stmt);

   }

   /**
    * Delete a course. Mark it's deleted flag as true
    * @param courseID
    * @return return <tt>true</tt> of deletion was successful. <tt>false</tt> if courseID not found, OR is already deleted.
    * @throws DatabaseException
    */
   public static boolean deleteCourse(int courseID) throws DatabaseException {
      try {
         Course c = getCourseByID(courseID);
         for (Timeslot t : c.getTimeslots())
         {
            deleteCourseSection(c.getId(),t.getTimeslot_id());
         }
         String query = "UPDATE course SET deleted = 1 WHERE course_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, courseID);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   public static int  hasCourseSection(int course_id, int timeslot_id) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM course_section WHERE course_id = ? AND slot_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, course_id);
         stmt.setInt(2, timeslot_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()){
            return crs.getInt("section_id");
         }
         return -1;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static void createCourseSection(int course_id, int timeslot_id) throws DatabaseException {
      try {
         String query;
         if (hasCourseSection(course_id, timeslot_id) >= 0)
            query = "UPDATE course_section SET deleted = 0 WHERE slot_id = ? AND course_id = ?";
         else
            query = "INSERT INTO course_section (slot_id, course_id) VALUES (?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         stmt.setInt(1, timeslot_id);
         stmt.setInt(2, course_id);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static void deleteCourseSection(int course_id, int timeslot_id) throws DatabaseException {
      try {
         String delete = "UPDATE course_section SET deleted = 1 WHERE course_id = " + String.valueOf(course_id)
            + " AND slot_id = " + String.valueOf(timeslot_id);
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   public static CourseSection getCourseSectionByID(int id) throws DatabaseException
   {
      try {
         CourseSection cs = null;
         String select = "SELECT * FROM course_section WHERE section_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setInt(1,id);
         
         ResultSet rs = DatabaseManager.executeQuery(stmt);
         while(rs.next())
         {
            cs = new CourseSection();
            cs.setDelete(rs.getBoolean("deleted"));
            cs.setSection_id(id);
            cs.setCourse(getCourseByID(rs.getInt("course_id")));
            cs.setTimeslot(getTimeslotByID(rs.getInt("slot_id")));
            
         }
         return cs;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   /**
    * get all classrooms in database
    * @return ArrayList of Classrooms
    * @throws DatabaseException
    */
   public static List<Classroom> getAllClassroom() throws DatabaseException {
      List<Classroom> rooms = new ArrayList();
      String query = "SELECT * FROM classroom WHERE deleted = 0 ORDER BY classroom_name ASC";
      PreparedStatement stmt;
      try {
         stmt = DatabaseManager.getPreparedStatement(query);
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

      CachedRowSet crs = DatabaseManager.executeQuery(stmt);
      try {
         while (crs.next()) {

            Classroom r = new Classroom();
            r.setCapacity(crs.getInt("capacity"));
            r.setClassroom_id(crs.getInt("classroom_id"));
            r.setDeleted(false);
            r.setLocation(getLocation());
            r.setName(crs.getString("classroom_name"));;

            rooms.add(r);

         }
         return rooms;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }



   }

   /**
    * get classroom by classroom ID.
    * @param classroomID
    * @return Classroom object if found. <tt>null</tt> if not found.
    * @throws DatabaseException
    */
   public static Classroom getClassroomByID(int classroomID) throws DatabaseException {
      try {
         Classroom r = null;
         String getClassroom = "SELECT * FROM classroom WHERE classroom_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(getClassroom);
         stmt.setInt(1, classroomID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            r = new Classroom();
            r.setClassroom_id(classroomID);
            r.setName(crs.getString("classroom_name"));
            r.setLocation(getLocation());
            r.setCapacity(crs.getInt("capacity"));
            r.setDeleted(false);
         }
         return r;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   /**
    * Create new classroom in the database.Classroom object must be correctly initialized.
    * @param classroom
    * @return return database generated classroomID
    * @throws DatabaseException
    */
   public static int createClassroom(Classroom classroom) throws DatabaseException {
      PreparedStatement stmt = insertClassroom(classroom);
      return DatabaseManager.executeGetPK(stmt);
   }

   /**
    * 
    * @param classroom
    * @throws DatabaseException
    */
   public static void modifyClassroom(Classroom classroom) throws DatabaseException {
      PreparedStatement stmt = updateClassroom(classroom);
      DatabaseManager.executeUpdate(stmt);
   }

   /**
    * 
    * @param classroomID
    * @return
    * @throws DatabaseException
    */
   public static boolean deleteClassroom(int classroomID) throws DatabaseException {
      try {
         String query = "UPDATE classroom SET deleted = 1 WHERE classroom_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, classroomID);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   public static Timeslot getTimeslotByID(int id) throws DatabaseException
   {
      try {
         Timeslot t = null;
         String select = "SELECT * FROM timeslot WHERE slot_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setInt(1, id);
         
         ResultSet rs = DatabaseManager.executeQuery(stmt);
         while(rs.next())
         {
            t = new Timeslot();
            t.setDayOfWeek(DayOfWeek.valueOf(rs.getString("day_of_week")));
            t.setStart(rs.getTime("start"));
         }
         return t;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   /**
    * create a new timeslot in database. If there already exists one, return that timeslot.
    * @param timeslot
    * @return
    * @throws DatabaseException
    */
   public static Timeslot createTimeslot(DayOfWeek day, Time start) throws DatabaseException {
      Timeslot t = hasTimeslot(day,start);
      if (t == null)
      {
         t = new Timeslot();
         PreparedStatement stmt = insertTimeslot(day, start);
         t.setTimeslot_id(DatabaseManager.executeGetPK(stmt));
         t.setDayOfWeek(day);
         t.setStart(start);
      }
      return t;     
   }
   
   public static Timeslot hasTimeslot(DayOfWeek day, Time start) throws DatabaseException
   {
      Timeslot t = null;
      try {
         String query = "SELECT * FROM timeslot WHERE day_of_week = ? AND start = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1,day.toString());
         stmt.setTime(2,start);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()){
            t = new Timeslot();
            t.setTimeslot_id(crs.getInt("slot_id"));
            t.setStart(crs.getTime("start"));
            t.setDayOfWeek(DayOfWeek.valueOf(crs.getString("day_of_week")));
         }
         return t;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static List<Project> getAllProjects(int course_id) throws DatabaseException
   {
      try {
         List<Project> projects = new ArrayList();
         String query;
         if (course_id > 0)
            query = "SELECT * FROM project WHERE course_id = ? AND deleted = 0 ORDER BY name ASC";
         else
            query = "SELECT * FROM project WHERE deleted = 0 ORDER BY name ASC";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         
         stmt.setInt(1, course_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Project p = new Project();
            p.setDeleted(false);
            p.setActivityID(course_id);
            p.setName(crs.getString("name"));
            p.setProject_id(crs.getInt("project_id"));
            
            projects.add(p);
         }
         return projects;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static Project getCourseProjectByID(int projectID) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM project WHERE project_id = ? ";
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1,projectID);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Project p = new Project();
            p.setDeleted(crs.getBoolean("deleted"));
            p.setActivityID(crs.getInt("course_id"));
            p.setName(crs.getString("name"));
            p.setProject_id(crs.getInt("project_id"));
            
            return p;
         }
         return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static void createCourseProject(int course_id, String name) throws DatabaseException
   {
      String update;
      if (hasCourseProject(name,course_id))
      {
         update = "UPDATE project SET deleted = 0 WHERE  name = ? AND course_id = ?";
      }else
      {
         update = "INSERT INTO project (name, course_id) VALUES (?,?)";
      }
      
      try {
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setString(1,name);
         stmt.setInt(2,course_id);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static void deleteCourseProject(int project_id) throws DatabaseException
   {
      try {
         String delete = "UPDATE project SET deleted = 1 WHERE project_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         stmt.setInt(1,project_id);
         
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static boolean hasCourseProject(String name, int course_id) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM project WHERE name = ? AND course_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, name);
         stmt.setInt(2, course_id);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()){
            return true;
         }
         return false;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static String getProgressReportNameById(int reportTypeID) throws DatabaseException
   {
      try {
         String query = "SELECT name FROM progress_report_type WHERE report_type_id = ?";
         PreparedStatement stmt;
       
            stmt = DatabaseManager.getPreparedStatement(query);
         
            stmt.setInt(1,reportTypeID);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
            while(crs.next())
            {
               return crs.getString("name");
            }
            return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   /**
    * get Progress report Type by report_type_id
    * @param reportTypeID
    * @return <tt>null</tt> if not found.
    * @throws DatabaseException
    */
   public static ProgressReportType getProgressReportTypeById(int reportTypeID) throws DatabaseException
   {
      try {
         String query = "SELECT * FROM progress_report_type WHERE report_type_id = ?";
         PreparedStatement stmt;
       
            stmt = DatabaseManager.getPreparedStatement(query);
         
            stmt.setInt(1,reportTypeID);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
            while(crs.next())
            {
               ProgressReportType t = new ProgressReportType();
               t.setReport_type_id(reportTypeID);
               t.setName(crs.getString("name"));
               t.setMaxScore(crs.getInt("max_score"));
               t.setSection1(crs.getString("section1"));
               t.setSection2(crs.getString("section2"));
               t.setSection3(crs.getString("section3"));
               t.setSection4(crs.getString("section4"));
               t.setCriteria1(crs.getString("criteria1"));
               t.setCriteria2(crs.getString("criteria2"));
               t.setCriteria3(crs.getString("criteria3"));
               t.setCriteria4(crs.getString("criteria4"));
               t.setCriteria5(crs.getString("criteria5"));
               t.setCriteria6(crs.getString("criteria6"));
               t.setCriteria7(crs.getString("criteria7"));
               t.setCriteria8(crs.getString("criteria8"));
               t.setCriteria9(crs.getString("criteria9"));
               t.setCriteria10(crs.getString("criteria10"));
               t.setCriteria11(crs.getString("criteria11"));
               t.setCriteria12(crs.getString("criteria12"));               
               t.setReportCardCommentTemplate(getReportCardCommentTemplateByID(reportTypeID));

               return t;
            }
            return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   /**
    * get all progress report Type in system.
    * @return
    * @throws DatabaseException
    */
   public static List<ProgressReportType> getAllProgressReportTypeLight() throws DatabaseException
   {
      try {
         List<ProgressReportType> reportTypes = new ArrayList();
         String query = "SELECT * FROM progress_report_type WHERE deleted = 0 ORDER BY name ASC";
         PreparedStatement stmt;
         try {
            stmt = DatabaseManager.getPreparedStatement(query);
         } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
         }
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            ProgressReportType t = new ProgressReportType();
            t.setReport_type_id(crs.getInt("report_type_id"));
            t.setName(crs.getString("name"));
            reportTypes.add(t);
         }
         return reportTypes;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   /**
    * get all progress report Type in system.
    * @return
    * @throws DatabaseException
    */
   public static List<ProgressReportType> getAllProgressReportType() throws DatabaseException
   {
      try {
         List<ProgressReportType> reportTypes = new ArrayList();
         String query = "SELECT report_type_id FROM progress_report_type WHERE deleted = 0";
         PreparedStatement stmt;
         try {
            stmt = DatabaseManager.getPreparedStatement(query);
         } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
         }
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            ProgressReportType t = getProgressReportTypeById(crs.getInt("report_type_id"));           
            reportTypes.add(t);
         }
         return reportTypes;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   
   /**
    * Create a progress report Type.<p>
    * This also creates the report card templates sentences associated with it.
    * @param reportType
    * @return generated database id
    * @throws DatabaseException
    */
   public static int createProgressReportType(ProgressReportType reportType) throws DatabaseException {
      //Insert report type
      PreparedStatement stmt = insertProgressReportType(reportType);
      int new_id = DatabaseManager.executeGetPK(stmt);

      //Insert list of report card comment template for this report type
      createReportCardCommentTemplate(new_id, reportType.getReportCardCommentTemplate());


      return new_id;

   }

   /**
    * 
    * @param reportType
    * @throws DatabaseException
    */
   public static void modifyProgressReportType(ProgressReportType reportType) throws DatabaseException {
      
      PreparedStatement stmt = updateProgressReportType(reportType);
      DatabaseManager.executeUpdate(stmt);
      
      //Delete old reportCardComment
      deleteReportCardCommentTemplate(reportType.getReport_type_id());
      
      //insert new reportCardComment
      createReportCardCommentTemplate(reportType.getReport_type_id(),reportType.getReportCardCommentTemplate());
      

   }

   /**
    * Mark the deleted flag of Progress report Type to true.<p>
    * Note:This methods will delete all the Report card comments sentences, since it's no longer needed.
    * @param reportTypeId
    * @return
    * @throws DatabaseException
    */
   public static boolean deleteProgressReportType(int reportTypeId) throws DatabaseException {
      try {
         String delete = "UPDATE progress_report_type SET deleted = 1 WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         stmt.setInt(1, reportTypeId);
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         if (rowChanged < 1) {
            return false;
         }
         deleteReportCardCommentTemplate(reportTypeId);

         return true;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   public static Location getLocation() throws DatabaseException
   {
      if (location == null)
         location = getLocationInformation();
      return location;
   }
   /**
    * Get the current system's location information.
    * Location information is set in the database as a singleton table, with only one row.
    * @return
    * @throws DatabaseException
    */
   private static Location getLocationInformation() throws DatabaseException {
      
      Location location = null;
      PreparedStatement stmt = null;
      try {

         String query = "SELECT * FROM location";
         stmt = DatabaseManager.getPreparedStatement(query);

         CachedRowSet rs = DatabaseManager.executeQuery(stmt);
         while (rs.next()) {
            location = new Location();
            location.setLocation_id(rs.getInt("location_id"));
            location.setName(rs.getString("location_name"));
            location.setAddress(rs.getString("location_address"));
         }
         return location;
      } catch (SQLException ex) {
         ex.printStackTrace();
         throw new DatabaseException(ex.getMessage());
      }

   }
   /**
    * 
    * @param reportTypeId
    * @param r
    * @throws DatabaseException
    */
   private static void createReportCardCommentTemplate(int reportTypeId, ReportCardCommentTemplate r) throws DatabaseException {
      for (String s : r.getSection1Observation()) {
         PreparedStatement stmt = insertSection1Observation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

      for (String s : r.getSection2Observation()) {
         PreparedStatement stmt = insertSection2Observation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

      for (String s : r.getSection3Observation()) {
         PreparedStatement stmt = insertSection3Observation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

      for (String s : r.getSection1Recommendation()) {
         PreparedStatement stmt = insertSection1Recommendation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

      for (String s : r.getSection2Recommendation()) {
         PreparedStatement stmt = insertSection2Recommendation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

      for (String s : r.getSection3Recommendation()) {
         PreparedStatement stmt = insertSection3Recommendation(reportTypeId, s);
         DatabaseManager.executeGetPK(stmt);
      }

   }

   /**
    * 
    * @param reportTypeId
    * @throws DatabaseException
    */
   private static void deleteReportCardCommentTemplate(int reportTypeId) throws DatabaseException {
      try {
         //section1 observation
         String delete = "DELETE FROM comment_section1_observation WHERE report_type_id = " + reportTypeId;
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);

         //section2 observation
         delete = "DELETE FROM comment_section2_observation WHERE report_type_id = " + reportTypeId;
         stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);

         //section3 observation
         delete = "DELETE FROM comment_section3_observation WHERE report_type_id = " + reportTypeId;
         stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);

         //section1 recommendation
         delete = "DELETE FROM comment_section1_recommendation WHERE report_type_id = " + reportTypeId;
         stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);

         //section2 recommendation
         delete = "DELETE FROM comment_section2_recommendation WHERE report_type_id = " + reportTypeId;
         stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);

         //section3 recommendation
         delete = "DELETE FROM comment_section3_recommendation WHERE report_type_id = " + reportTypeId;
         stmt = DatabaseManager.getPreparedStatement(delete);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   private static PreparedStatement insertClassroom(Classroom r) throws DatabaseException {
      try {
         String query = "INSERT INTO classroom (classroom_name, capacity, location_id) VALUES (?,?,?)";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, r.getName());
         stmt.setInt(2, r.getCapacity());
         Location location = getLocation();
         stmt.setInt(3, location.getLocation_id());
         return stmt;
      } catch (SQLException ex) {
         ex.printStackTrace();
         throw new DatabaseException("SQL Error.in insertClassroom" + ex.getMessage());
      }
   }

   private static PreparedStatement insertTimeslot(DayOfWeek day, Time start) throws DatabaseException {
      try {
         String query = "INSERT INTO timeslot (day_of_week, start) VALUES (?,?)";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, day.toString());
         stmt.setTime(2, start);
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement updateProgressReportType(ProgressReportType reportType) throws DatabaseException {
      try {
         String update = "UPDATE progress_report_type SET max_score = ?, section1 = ?, criteria1 = ?, criteria2 = ?, "
            + "criteria3 = ?, section2 = ?, criteria4 = ?, criteria5 = ?, criteria6 = ?, section3 = ?, criteria7 = ?, "
            + "criteria8 = ?, criteria9 = ?, section4 = ?, criteria10 = ?, criteria11 = ?, criteria12 = ?, name = ? "
            + "WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);

         stmt.setInt(1, reportType.getMaxScore());
         stmt.setString(2, reportType.getSection1());
         stmt.setString(3, reportType.getCriteria1());
         stmt.setString(4, reportType.getCriteria2());
         stmt.setString(5, reportType.getCriteria3());
         stmt.setString(6, reportType.getSection2());
         stmt.setString(7, reportType.getCriteria4());
         stmt.setString(8, reportType.getCriteria5());
         stmt.setString(9, reportType.getCriteria6());
         stmt.setString(10, reportType.getSection3());
         stmt.setString(11, reportType.getCriteria7());
         stmt.setString(12, reportType.getCriteria8());
         stmt.setString(13, reportType.getCriteria9());
         stmt.setString(14, reportType.getSection4());
         stmt.setString(15, reportType.getCriteria10());
         stmt.setString(16, reportType.getCriteria11());
         stmt.setString(17, reportType.getCriteria12());
         stmt.setString(18, reportType.getName());
         stmt.setInt(19, reportType.getReport_type_id());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   private static PreparedStatement insertProgressReportType(ProgressReportType reportType) throws DatabaseException {
      try {
         String query = "INSERT INTO progress_report_type (max_score,section1,criteria1,criteria2,criteria3,"
            + "section2,criteria4,criteria5,criteria6,section3,criteria7,criteria8,criteria9,section4,criteria10,criteria11,criteria12,name) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportType.getMaxScore());
         stmt.setString(2, reportType.getSection1());
         stmt.setString(3, reportType.getCriteria1());
         stmt.setString(4, reportType.getCriteria2());
         stmt.setString(5, reportType.getCriteria3());
         stmt.setString(6, reportType.getSection2());
         stmt.setString(7, reportType.getCriteria4());
         stmt.setString(8, reportType.getCriteria5());
         stmt.setString(9, reportType.getCriteria6());
         stmt.setString(10, reportType.getSection3());
         stmt.setString(11, reportType.getCriteria7());
         stmt.setString(12, reportType.getCriteria8());
         stmt.setString(13, reportType.getCriteria9());
         stmt.setString(14, reportType.getSection4());
         stmt.setString(15, reportType.getCriteria10());
         stmt.setString(16, reportType.getCriteria11());
         stmt.setString(17, reportType.getCriteria12());
         stmt.setString(18, reportType.getName());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement insertCourse(Course course) throws DatabaseException {
      try {
         String query = "INSERT INTO course (code,name,description,duration,classroom_id,rate,report_type_id, hasTax)"
            + "VALUES(?,?,?,?,?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, course.getCode());
         stmt.setString(2, course.getName());
         stmt.setString(3, course.getDescription());
         stmt.setInt(4, course.getDuration());
         stmt.setInt(5, course.getClassroom().getClassroom_id());
         stmt.setFloat(6, course.getRate());
         stmt.setInt(7, course.getReport_type_id());
         stmt.setBoolean(8,course.hasTax());
         return stmt;

      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }
   private static List<Timeslot> getAllMembershipTimeslots(int membership_id) throws DatabaseException {
      try {
         List<Timeslot> timeslots = new ArrayList();

         String query;
         if (membership_id < 1) {
            query = "SELECT slot_id,day_of_week,start FROM membership_view GROUP BY slot_id ORDER BY day_of_week ASC,start ASC";
         } else {
            query = "SELECT slot_id, day_of_week, start FROM membership_view WHERE memberships_id = " + String.valueOf(membership_id) + " ORDER BY day_of_week ASC, start ASC";
         }
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Timeslot t = new Timeslot();
            t.setTimeslot_id(crs.getInt("slot_id"));
            t.setDayOfWeek(DayOfWeek.valueOf(crs.getString("day_of_week")));
            t.setStart(crs.getTime("start"));
            timeslots.add(t);
         }
         return timeslots;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static List<Timeslot> getAllCourseTimeslots(int course_id) throws DatabaseException {
      try {
         List<Timeslot> timeslots = new ArrayList();

         String query;
         if (course_id < 1) {
            query = "SELECT slot_id,day_of_week,start FROM course_view GROUP BY slot_id ORDER BY day_of_week ASC,start ASC";
         } else {
            query = "SELECT slot_id, day_of_week, start FROM course_view WHERE course_id = " + String.valueOf(course_id) + " ORDER BY day_of_week ASC, start ASC";
         }
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            Timeslot t = new Timeslot();
            t.setTimeslot_id(crs.getInt("slot_id"));
            t.setDayOfWeek(DayOfWeek.valueOf(crs.getString("day_of_week")));
            t.setStart(crs.getTime("start"));
            timeslots.add(t);
         }
         return timeslots;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   

   private static PreparedStatement updateCourse(Course course) throws DatabaseException {
      String updateCourse = "UPDATE course SET code = ?, name = ?, description = ?, duration = ?,"
         + "classroom_id = ? ,rate = ? ,report_type_id = ? , hasTax = ? WHERE course_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(updateCourse);
         stmt.setString(1, course.getCode());
         stmt.setString(2, course.getName());
         stmt.setString(3, course.getDescription());
         stmt.setInt(4, course.getDuration());
         stmt.setInt(5, course.getClassroom().getClassroom_id());
         stmt.setFloat(6, course.getRate());
         stmt.setInt(7, course.getReport_type_id());
         stmt.setBoolean(8,course.hasTax());
         stmt.setInt(9, course.getId());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement updateClassroom(Classroom classroom) throws DatabaseException {
      try {
         String update = "UPDATE classroom SET classroom_name = ?, capacity = ?, location_id = ? WHERE classroom_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setString(1, classroom.getName());
         stmt.setInt(2, classroom.getCapacity());
         stmt.setInt(3, classroom.getLocation().getLocation_id());
         stmt.setInt(4, classroom.getClassroom_id());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }

   }

   private static Course getDeletedCourseByID(int courseID) throws DatabaseException {
      try {
         Course temp = null;
         String getCourseByID = "SELECT * FROM deleted_course_view WHERE course_id = ? GROUP BY course_id";
         PreparedStatement stmt;

         stmt = DatabaseManager.getPreparedStatement(getCourseByID);
         stmt.setInt(1, courseID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         if (crs.next()) {
            temp = new Course();

            Classroom r = new Classroom();
            r.setName(crs.getString("classroom_name"));
            r.setLocation(getLocation());
            r.setCapacity(crs.getInt("capacity"));
            r.setClassroom_id(crs.getInt("classroom_id"));
            r.setDeleted(false);

            temp.setClassroom(r);
            temp.setCode(crs.getString("code"));
            temp.setId(courseID);
            temp.setDeleted(true);
            temp.setDescription(crs.getString("description"));
            temp.setDuration(crs.getInt("duration"));
            temp.setRate(crs.getFloat("rate"));
            temp.setName(crs.getString("name"));
            temp.setReport_type_id(crs.getInt("report_type_id"));
            temp.setTimeslots(getAllCourseTimeslots(courseID));
         }
         return temp;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static PreparedStatement insertSection1Observation(int reportTypeId, String section_observation) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section1_observation (report_type_id, section1_observation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, section_observation);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertSection2Observation(int reportTypeId, String s) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section2_observation (report_type_id, section2_observation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, s);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertSection3Observation(int reportTypeId, String s) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section3_observation (report_type_id, section3_observation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, s);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertSection1Recommendation(int reportTypeId, String s) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section1_recommendation (report_type_id, section1_recommendation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, s);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertSection2Recommendation(int reportTypeId, String s) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section2_recommendation (report_type_id, section2_recommendation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, s);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertSection3Recommendation(int reportTypeId, String s) throws DatabaseException {
      try {
         String insert = "INSERT INTO comment_section3_recommendation (report_type_id, section3_recommendation) VALUES(?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, reportTypeId);
         stmt.setString(2, s);

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static ReportCardCommentTemplate getReportCardCommentTemplateByID(int reportTypeId) throws DatabaseException
   {
      ReportCardCommentTemplate t = new ReportCardCommentTemplate();
      t.setReport_type_id(reportTypeId);
      t.setSection1Observation(getSection1ObservationStrings(reportTypeId));
      t.setSection2Observation(getSection2ObservationStrings(reportTypeId));
      t.setSection3Observation(getSection3ObservationStrings(reportTypeId));
      t.setSection1Recommendation(getSection1RecommendationStrings(reportTypeId));
      t.setSection2Recommendation(getSection2RecommendationStrings(reportTypeId));
      t.setSection3Recommendation(getSection3RecommendationStrings(reportTypeId));
      
      return t;
   }
   private static List<String> getSection1ObservationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section1_observation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section1_observation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   private static List<String> getSection2ObservationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section2_observation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section2_observation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static List<String> getSection3ObservationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section3_observation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section3_observation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static List<String> getSection1RecommendationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section1_recommendation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section1_recommendation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static List<String> getSection2RecommendationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section2_recommendation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section2_recommendation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static List<String> getSection3RecommendationStrings(int reportTypeId) throws DatabaseException {
      try {
         List<String> sentences = new ArrayList();
         String query = "SELECT * FROM comment_section3_recommendation WHERE report_type_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, reportTypeId);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            String s = crs.getString("section3_recommendation");
            sentences.add(s);
         }
         return sentences;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static void main(String args[]) throws DatabaseException
   {
      System.out.println(hasTimeslot(DayOfWeek.Wed,new Time(12,0,0)));
      createTimeslot(DayOfWeek.Wed,new Time(12,0,0));
   }

   public static List<Timeslot> getUniqueActivitiesTimeslot(DayOfWeek day) throws DatabaseException {
      try {
         List<Timeslot> timeslots = new ArrayList();
         String query = new String();
         if (day == null)
         {
            query = "SELECT slot_id, day_of_week, start FROM course_view GROUP BY slot_id ORDER BY day_of_week, start";
         }else
         {
            query = "SELECT slot_id, day_of_week, start FROM course_view WHERE day_of_week = ? GROUP BY slot_id ORDER BY start";     
         }
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         
         if (day != null)
            stmt.setString(1,day.toString());
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Timeslot t = new Timeslot();
            t.setTimeslot_id(crs.getInt("slot_id"));
            t.setStart(crs.getTime("start"));
            t.setDayOfWeek(DayOfWeek.valueOf(crs.getString("day_of_week")));
            
            timeslots.add(t);
         }
         
         //Memberships
         List<Timeslot> membershipTime = new ArrayList();
         if (day == null)
         {
            query = "SELECT slot_id, day_of_week, start FROM membership_view GROUP BY slot_id ORDER BY day_of_week, start";
         }else
         {
            query = "SELECT slot_id, day_of_week, start FROM membership_view WHERE day_of_week = ? GROUP BY slot_id ORDER BY start";     
         }
         
         stmt = DatabaseManager.getPreparedStatement(query);
         
         if (day != null)
            stmt.setString(1,day.toString());
         crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Timeslot t = new Timeslot();
            boolean dup = false;
            t.setTimeslot_id(crs.getInt("slot_id"));
            t.setStart(crs.getTime("start"));
            t.setDayOfWeek(DayOfWeek.valueOf(crs.getString("day_of_week")));
            
            for (Timeslot exist : timeslots)
            {
               if (exist.getTimeslot_id() == t.getTimeslot_id())
                  dup = true;
            }
            if (!dup)
               membershipTime.add(t);
         }
         timeslots.addAll(membershipTime);        
         return timeslots;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }

   private static PreparedStatement insertMembership(Membership membership) throws DatabaseException {
      try {
         String insert = "INSERT INTO membership ( classroom_id, code, name, description, duration, startDate, endDate, rate, deleted, hasTax) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         
         stmt.setInt(1,membership.getClassroom().getClassroom_id());
         stmt.setString(2,membership.getCode());
         stmt.setString(3,membership.getName());
         stmt.setString(4,membership.getDescription());
         stmt.setInt(5,membership.getDuration());
         stmt.setDate(6,membership.getStartDate());
         stmt.setDate(7,membership.getEndDate());
         stmt.setFloat(8,membership.getRate());
         stmt.setBoolean(9,false);
         stmt.setBoolean(10, membership.hasTax());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }

   

   private static PreparedStatement updateMembership(Membership membership) throws DatabaseException {
      String updateCourse = "UPDATE membership SET code = ?, name = ?, description = ?, duration = ?,"
         + "classroom_id = ? ,rate = ? ,hasTax = ?, startDate = ?, endDate = ? WHERE memberships_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(updateCourse);
         stmt.setString(1, membership.getCode());
         stmt.setString(2, membership.getName());
         stmt.setString(3, membership.getDescription());
         stmt.setInt(4, membership.getDuration());
         stmt.setInt(5, membership.getClassroom().getClassroom_id());
         stmt.setFloat(6, membership.getRate());     
         stmt.setBoolean(7,membership.hasTax());
         stmt.setDate(8,membership.getStartDate());
         stmt.setDate(9,membership.getEndDate());
         stmt.setInt(10, membership.getId());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   
}
