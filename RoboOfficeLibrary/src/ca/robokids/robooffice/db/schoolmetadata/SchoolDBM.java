/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.schoolmetadata;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.schoolmetadata.*;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class SchoolDBM {

   public static List<Course> getAllCourses(Classroom cr) throws DatabaseException {
      try {
         List<Course> courses = new ArrayList();
         String query;

         if (cr.getClassroom_id() < 1) {
            query = "SELECT DISTINCT course_id FROM course_view";
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

   public static List<Course> getAllCourses(Timeslot timeslotID) throws DatabaseException {
      try {
         List<Course> courses = new ArrayList();
         String query;

         if (timeslotID.getTimeslot_id() < 1) {
            query = "SELECT DISTINCT course_id FROM course_view";
         } else {
            query = "SELECT DISTINCT course_id FROM course_view WHERE slot_id = " + String.valueOf(timeslotID.getTimeslot_id())
               + "ORDER BY code";
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

   public static Course getCourseByID(int courseID) throws DatabaseException {
      Course course = getActiveCourseByID(courseID);
      if (course == null) {
         course = getDeletedCourseByID(courseID);
      }
      return course;
   }

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
            temp.setReport_type_id(courseID);
            temp.setTimeslots(getAllTimeslots(courseID));
         }
         return temp;


      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   public static int createCourse(Course course) throws DatabaseException {
      PreparedStatement stmt = insertCourse(course);
      int new_id = DatabaseManager.executeGetPK(stmt);

      for (Timeslot t : course.getTimeslots()) {
         createCourseSection(new_id, t.getTimeslot_id());
      }
      return new_id;
   }

   public static void modifyCourse(Course course) throws DatabaseException {
      //Delete old timeslots for this course
      List<Timeslot> timeslots = getAllTimeslots(course.getId());
      for (Timeslot t : timeslots) {
         try {
            deleteCourseSection(course.getId(), t.getTimeslot_id());
         } catch (SQLException ex) {
            throw new DatabaseException(ex.getMessage());
         }
      }

      //update course fields
      PreparedStatement stmt = updateCourse(course);
      DatabaseManager.executeUpdate(stmt);

      timeslots = course.getTimeslots();
      //add mapping between timeslots and modified course
      for (Timeslot t : timeslots) {
         createCourseSection(course.getId(), t.getTimeslot_id());
      }
   }

   public static boolean deleteCourse(int courseID) throws DatabaseException {
      try {
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

   public static List<Classroom> getAllClassroom() throws DatabaseException {
      List<Classroom> rooms = new ArrayList();
      String query = "SELECT * FROM classroom WHERE deleted = 0";
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

   public static int createClassroom(Classroom classroom) throws DatabaseException {
      PreparedStatement stmt = insertClassroom(classroom);
      return DatabaseManager.executeGetPK(stmt);
   }

   public static void modifyClassroom(Classroom classroom) throws DatabaseException {
      PreparedStatement stmt = updateClassroom(classroom);
      DatabaseManager.executeUpdate(stmt);
   }

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

   public static int createTimeSlot(Timeslot timeslot) throws DatabaseException {
      PreparedStatement stmt = insertTimeslot(timeslot);
      return DatabaseManager.executeGetPK(stmt);
   }

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
               t.setReportCardCommentTemplate(getReportCardCommentTemplate(reportTypeID));

               return t;
            }
            return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static List<ProgressReportType> getAllProgressReportType() throws DatabaseException
   {
      try {
         List<ProgressReportType> reportTypes = new ArrayList();
         String query = "SELECT * FROM progress_report_type WHERE deleted = 0";
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
   private static ReportCardCommentTemplate getReportCardCommentTemplate(int reportTypeId) throws DatabaseException
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
   public static int createProgressReportType(ProgressReportType reportType) throws DatabaseException {
      //Insert report type
      PreparedStatement stmt = insertProgressReportType(reportType);
      int new_id = DatabaseManager.executeGetPK(stmt);

      //Insert list of report card comment template for this report type
      createReportCardCommentTemplate(new_id, reportType.getReportCardCommentTemplate());


      return new_id;

   }

   public static void modifyProgressReportType(ProgressReportType reportType) throws DatabaseException {
      
      PreparedStatement stmt = updateProgressReportType(reportType);
      DatabaseManager.executeUpdate(stmt);
      
      //Delete old reportCardComment
      deleteReportCardCommentTemplate(reportType.getReport_type_id());
      
      //insert new reportCardComment
      createReportCardCommentTemplate(reportType.getReport_type_id(),reportType.getReportCardCommentTemplate());
      

   }

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

   public static void createReportCardCommentTemplate(int reportTypeId, ReportCardCommentTemplate r) throws DatabaseException {
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

   public static void deleteReportCardCommentTemplate(int reportTypeId) throws DatabaseException {
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

   public static Location getLocation() throws DatabaseException {
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

   private static PreparedStatement insertTimeslot(Timeslot timeslot) throws DatabaseException {
      try {
         String query = "INSERT INTO timeslot (day_of_week, start) VALUES (?,?)";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, timeslot.getDayOfWeek().toString());
         stmt.setTime(2, timeslot.getStart());
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
         String query = "INSERT INTO course (code,name,description,duration,classroom_id,rate,report_type_id)"
            + "VALUES(?,?,?,?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setString(1, course.getCode());
         stmt.setString(2, course.getName());
         stmt.setString(3, course.getDescription());
         stmt.setInt(4, course.getDuration());
         stmt.setInt(5, course.getClassroom().getClassroom_id());
         stmt.setFloat(6, course.getRate());
         stmt.setInt(7, course.getReport_type_id());
         return stmt;

      } catch (SQLException ex) {
         throw new DatabaseException("SQL Error." + ex.getMessage());
      }
   }

   private static void createCourseSection(int course_id, int timeslot_id) throws DatabaseException {
      try {
         String query = "INSERT INTO course_section (slot_id, course_id) VALUES (?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);

         stmt.setInt(1, timeslot_id);
         stmt.setInt(2, course_id);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         Logger.getLogger(SchoolDBM.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private static List<Timeslot> getAllTimeslots(int course_id) throws DatabaseException {
      try {
         List<Timeslot> timeslots = new ArrayList();

         String query;
         if (course_id < 1) {
            query = "SELECT slot_id,day_of_week,start FROM course_view GROUP BY slot_id ORDER BY start ASC";
         } else {
            query = "SELECT slot_id, day_of_week, start FROM course_view WHERE course_id = " + String.valueOf(course_id) + " ORDER BY start ASC";
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

   private static void deleteCourseSection(int course_id, int timeslot_id) throws SQLException, DatabaseException {
      String delete = "DELETE FROM course_section WHERE course_id = " + String.valueOf(course_id)
         + " AND timeslot_id = " + String.valueOf(timeslot_id);
      PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
      DatabaseManager.executeUpdate(stmt);
   }

   private static PreparedStatement updateCourse(Course course) throws DatabaseException {
      String updateCourse = "UPDATE course SET code = ?, name = ?, description = ?, duration = ?,"
         + "classroom_id = ? ,rate = ? ,report_type_id = ? WHERE user_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(updateCourse);
         stmt.setString(1, course.getCode());
         stmt.setString(2, course.getName());
         stmt.setString(3, course.getDescription());
         stmt.setInt(4, course.getDuration());
         stmt.setInt(5, course.getClassroom().getClassroom_id());
         stmt.setFloat(6, course.getRate());
         stmt.setInt(7, course.getReport_type_id());
         stmt.setInt(8, course.getId());

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
            temp.setDeleted(false);
            temp.setDescription(crs.getString("description"));
            temp.setDuration(crs.getInt("duration"));
            temp.setRate(crs.getFloat("rate"));
            temp.setName(crs.getString("name"));
            temp.setReport_type_id(courseID);
            temp.setTimeslots(getAllTimeslots(courseID));
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
}
