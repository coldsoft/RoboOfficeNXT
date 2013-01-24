/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.studentmanagement;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.student.ClassRecord;
import ca.robokids.robooffice.entity.student.CourseProgress;
import ca.robokids.test.SampleEntities;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class ProgressDBM {

   public static int createClassRecord(ClassRecord classrecord) throws DatabaseException {
      PreparedStatement stmt = insertClassRecord(classrecord);
      return DatabaseManager.executeGetPK(stmt);
   }

   public static void modifyClassRecord(ClassRecord classrecord) throws DatabaseException {
      PreparedStatement stmt = updateClassRecord(classrecord);
      DatabaseManager.executeUpdate(stmt);
   }

   public static void deleteClassRecord(int crID) throws DatabaseException {
      String delete = "UPDATE class_record SET deleted = 1 WHERE class_record_id = ? ";
      PreparedStatement stmt;
      try {
         stmt = DatabaseManager.getPreparedStatement(delete);
         stmt.setInt(1, crID);
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   public static List<ClassRecord> getAllClassRecord(int progressID) throws DatabaseException {
      List<ClassRecord> list = new ArrayList();
      try {
         String select = "SELECT class_record_id FROM class_record WHERE course_progress_id = ? AND deleted = 0 ORDER BY attend_date ASC";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);

         stmt.setInt(1, progressID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);

         while (crs.next()) {
            ClassRecord cr = getClassRecordByID(crs.getInt("class_record_id"));
            list.add(cr);
         }
         return list;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }


   }

   public static ClassRecord getClassRecordByID(int recordID) throws DatabaseException {
      try {
         ClassRecord cr = null;
         String select = "SELECT * from class_record WHERE class_record_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setInt(1, recordID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            cr = new ClassRecord();
            cr.setRecord_id(recordID);
            cr.setProgressID(crs.getInt("course_progress_id"));
            cr.setSection(SchoolDBM.getCourseSectionByID(crs.getInt("section_id")));
            cr.setAttendDate(crs.getDate("attend_date"));
            cr.setProject(SchoolDBM.getCourseProjectByID(crs.getInt("project_id")));
            cr.setTest(crs.getBoolean("is_test"));
            cr.setModifiedBy(crs.getInt("modified_by"));
            cr.setModified_Date(crs.getDate("modified_date"));
            cr.setComplete(crs.getBoolean("complete"));

         }
         return cr;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   public static int createCourseProgress(CourseProgress cp) throws DatabaseException {
      PreparedStatement stmt = insertCourseProgress(cp);
      return DatabaseManager.executeGetPK(stmt);
   }

   public static void modifyCourseProgress(CourseProgress cp) throws DatabaseException {
      PreparedStatement stmt = updateCourseProgress(cp);
      DatabaseManager.executeUpdate(stmt);
   }

   public static void deleteCourseProgress(int cp_id) throws DatabaseException {
      try {
         String delete = "UPDATE course_progress SET deleted = 1 WHERE course_progress_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }


   }

   public static List<CourseProgress> getAllCourseProgressBySectionID(int sectionID, boolean active) throws DatabaseException {
      List<CourseProgress> list = new ArrayList();
      try {

         String orderby = " ORDER BY start_date DESC";
         PreparedStatement stmt;

         String select = "SELECT course_progress_id FROM course_progress WHERE section_id = ? AND active = ? AND deleted = 0" + orderby;
         stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setInt(1, sectionID);
         stmt.setBoolean(2, active);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            list.add(getCourseProgressByID(crs.getInt("course_progress_id")));
         }
      } catch (SQLException e) {
         throw new DatabaseException(e.getMessage());
      }
      return list;
   }

   public static List<CourseProgress> getAllCourseProgressByStudentID(int studentID, Boolean active) throws DatabaseException {
      List<CourseProgress> list = new ArrayList();
      try {
         String select;
         String orderby = " ORDER BY start_date DESC";
         PreparedStatement stmt;
         if (active != null) {
            select = "SELECT course_progress_id FROM course_progress WHERE student_id = ? AND active = ? AND deleted = 0" + orderby;
            stmt = DatabaseManager.getPreparedStatement(select);
            stmt.setInt(1, studentID);
            stmt.setBoolean(2, active);
         } else {
            select = "SELECT course_progress_id FROM course_progress WHERE student_id = ? AND deleted = 0" + orderby;
            stmt = DatabaseManager.getPreparedStatement(select);
            stmt.setInt(1, studentID);
         }

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            list.add(getCourseProgressByID(crs.getInt("course_progress_id")));
         }
      } catch (SQLException e) {
         throw new DatabaseException(e.getMessage());
      }
      return list;
   }

   public static CourseProgress getCourseProgressByID(int cp_id) throws DatabaseException {
      try {
         String select = "SELECT * FROM course_progress WHERE course_progress_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {
            CourseProgress cp = new CourseProgress();
            cp.setProgressID(cp_id);
            cp.setStudent(StudentDBM.getStudentByID(crs.getInt("student_id")));
            cp.setPayment_record_id(crs.getInt("payment_record_id"));
            cp.setSection(SchoolDBM.getCourseSectionByID(crs.getInt("section_id")));
            cp.setProgress(crs.getInt("progress"));
            cp.setTotalClass(crs.getInt("total_class"));
            cp.setAvailableCredit(crs.getFloat("available_credit"));
            cp.setStartDate(crs.getDate("start_date"));
            cp.setEndDate(crs.getDate("expire_date"));
            cp.setActive(crs.getBoolean("active"));
            cp.setSectionChanged(crs.getInt("section_changed"));
            cp.setBonusClass(crs.getInt("bonus_class"));
            cp.setNextProgressID(crs.getInt("next_progress_id"));
            cp.setModifiedBy(crs.getInt("modified_by"));
            cp.setModifiedDate(crs.getDate("modified_date"));
            return cp;
         }
         return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   private static PreparedStatement insertClassRecord(ClassRecord classrecord) throws DatabaseException {
      String insert = "INSERT INTO class_record (course_progress_id, sectionID,attend_date, project_id, is_test, modified_by,modified_date,complete)"
         + "VALUES(?,?,?,?,?,?,?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         stmt.setInt(1, classrecord.getProgressID());
         stmt.setInt(2, classrecord.getSection().getSection_id());
         stmt.setDate(3, classrecord.getAttendDate());
         stmt.setInt(4, classrecord.getProject().getProject_id());
         stmt.setBoolean(5, classrecord.isTest());
         stmt.setInt(6, classrecord.getModifiedBy());
         stmt.setDate(7, classrecord.getModified_Date());
         stmt.setBoolean(8, classrecord.isComplete());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }


   }

   private static PreparedStatement updateClassRecord(ClassRecord classrecord) throws DatabaseException {
      try {
         String update = "UPDATE class_record SET course_progress_id = ?,sectionID = ?, "
            + "attend_date = ?, project_id = ?, is_test = ?, modified_by = ?, modified_date = ?, complete = ? WHERE class_record_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setInt(1, classrecord.getProgressID());
         stmt.setInt(2, classrecord.getSection().getSection_id());
         stmt.setDate(3, classrecord.getAttendDate());
         stmt.setInt(4, classrecord.getProject().getProject_id());
         stmt.setBoolean(5, classrecord.isTest());
         stmt.setInt(6, classrecord.getModifiedBy());
         stmt.setDate(7, classrecord.getModified_Date());
         stmt.setBoolean(8, classrecord.isComplete());
         stmt.setInt(9, classrecord.getRecord_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertCourseProgress(CourseProgress cp) throws DatabaseException {
      String insert = "INSERT INTO course_progress (student_id,payment_record_id, section_id, progress, total_class,available_credit"
         + ", start_date, expire_date, active, section_changed, bonus_class, next_progress_id, modified_by, modified_date)"
         + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         stmt.setInt(1, cp.getStudent().getStudent_id());
         stmt.setInt(2, cp.getPayment_record_id());
         stmt.setInt(3, cp.getSection().getSection_id());
         stmt.setInt(4, cp.getProgress());
         stmt.setInt(5, cp.getTotalClass());
         stmt.setFloat(6, cp.getAvailableCredit());
         stmt.setDate(7, cp.getStartDate());
         stmt.setDate(8, cp.getEndDate());
         stmt.setBoolean(9, cp.isActive());
         stmt.setInt(10, cp.getSectionChanged());
         stmt.setInt(11, cp.getBonusClass());
         stmt.setInt(12, cp.getNextProgressID());
         stmt.setInt(13, cp.getModifiedBy());
         stmt.setDate(14, cp.getModifiedDate());

         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement updateCourseProgress(CourseProgress cp) throws DatabaseException {
      String update = "UPDATE course_progress SET student_id = ? , payment_record_id = ?, section_id = ?, progress = ?, total_class = ?,available_credit = ?"
         + ", start_date = ?, expire_date = ?, active = ?, section_changed = ?, "
         + "bonus_class = ?, next_progress_id = ?, modified_by = ?, modified_date = ?) WHERE course_progress_id = ?";
      try {
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setInt(1, cp.getStudent().getStudent_id());
         stmt.setInt(2, cp.getPayment_record_id());
         stmt.setInt(3, cp.getSection().getSection_id());
         stmt.setInt(4, cp.getProgress());
         stmt.setInt(5, cp.getTotalClass());
         stmt.setFloat(6, cp.getAvailableCredit());
         stmt.setDate(7, cp.getStartDate());
         stmt.setDate(8, cp.getEndDate());
         stmt.setBoolean(9, cp.isActive());
         stmt.setInt(10, cp.getSectionChanged());
         stmt.setInt(11, cp.getBonusClass());
         stmt.setInt(12, cp.getNextProgressID());
         stmt.setInt(13, cp.getModifiedBy());
         stmt.setDate(14, cp.getModifiedDate());
         stmt.setInt(15, cp.getProgressID());


         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   

}
