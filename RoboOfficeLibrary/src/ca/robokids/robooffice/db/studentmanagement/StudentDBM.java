/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.studentmanagement;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.entity.student.Sex;
import ca.robokids.robooffice.entity.student.Student;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class StudentDBM {

   public static Student getStudentByID(int studentID) throws DatabaseException {
      try {
         Student s = null;
         String query = "SELECT * FROM student WHERE student_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, studentID);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         if (crs.next()) {
            s = new Student();
            s.setStudent_id(crs.getInt("student_id"));
            s.setCreated_by(UserDBM.getUserNameByID(crs.getInt("created_by")));
            s.setSex(Sex.valueOf(crs.getString("sex")));
            s.setFirstName(crs.getString("first_name"));
            s.setLastName(crs.getString("last_name"));
            s.setCreateDate(crs.getDate("created_date"));
            s.setActive(crs.getBoolean("active"));
            s.setProspective(crs.getBoolean("prospective"));
            s.setBirthday(crs.getDate("birthday"));
            s.setSchool(crs.getString("school"));
            s.setAddress(crs.getString("address"));
            s.setZipcode(crs.getString("zipcode"));
            s.setCity(crs.getString("city"));
            s.setEmail(crs.getString("email"));
            s.setMother(crs.getString("mother"));
            s.setMotherPhone(crs.getString("mother_phone"));
            s.setFather(crs.getString("father"));
            s.setFatherPhone(crs.getString("father_phone"));
            s.setHomePhone(crs.getString("home_phone"));
            s.setEmergency(crs.getString("emergency"));
            s.setJoinDate(crs.getDate("join_date"));
            s.setHearFrom(crs.getString("hear_from"));
            s.setPassword(crs.getString("password"));
            s.setUserName(crs.getString("userName"));
            s.setNotes(crs.getString("notes"));
            PasswordQuestion p = new PasswordQuestion();
            p.setPassword_qestion_id(crs.getInt("password_question_id"));
            s.setPasswordQuestion(p);
            s.setPasswordAnswer(crs.getString("passwordAnswer"));
         }
         
         return s;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   public static int createStudent(Student student) throws DatabaseException {
      PreparedStatement stmt = insertStudent(student);
      return DatabaseManager.executeGetPK(stmt);
   }

   public static void modifyStudent(Student student) throws DatabaseException {
      PreparedStatement stmt = updateStudent(student);
      DatabaseManager.executeUpdate(stmt);

   }

   public static void deleteStudent(int studentID) throws DatabaseException {
      try {
         String delete = "UPDATE student SET deleted = 1 WHERE student_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);
         stmt.setInt(1, studentID);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   private static PreparedStatement insertStudent(Student student) throws DatabaseException {
      try {
         String query = "INSERT INTO student(created_by,sex,first_name,last_name,created_date,active,prospective,birthday,"
            + "school,address,zipcode,city,email,mother,mother_phone,father,father_phone,home_phone,emergency,join_date,hear_from,password,userName,password_question_id,passwordAnswer,notes) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1, student.getCreated_by().getUser_id());
         stmt.setString(2, student.getSex().toString());
         stmt.setString(3, student.getFirstName());
         stmt.setString(4, student.getLastName());
         stmt.setDate(5, student.getCreateDate());
         stmt.setBoolean(6, student.isActive());
         stmt.setBoolean(7, student.isProspective());
         stmt.setDate(8, student.getBirthday());
         stmt.setString(9, student.getSchool());
         stmt.setString(10, student.getAddress());
         stmt.setString(11, student.getZipcode());
         stmt.setString(12, student.getCity());
         stmt.setString(13, student.getEmail());
         stmt.setString(14, student.getMother());
         stmt.setString(15, student.getMotherPhone());
         stmt.setString(16, student.getFather());
         stmt.setString(17, student.getFatherPhone());
         stmt.setString(18, student.getHomePhone());
         stmt.setString(19, student.getEmergency());
         stmt.setDate(20, student.getJoinDate());
         stmt.setString(21, student.getHearFrom());
         stmt.setString(22, student.getPassword());
         stmt.setString(23, student.getUserName());
         stmt.setInt(24, student.getPasswordQuestion().getPassword_qestion_id());
         stmt.setString(25, student.getPasswordAnswer());
         stmt.setString(26,student.getNotes());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement updateStudent(Student student) throws DatabaseException {
      try {
         String update = "UPDATE student SET created_by = ?,  sex = ?,  first_name = ?,  last_name = ?,  created_date = ?,  active = ?,  prospective = ?,  birthday = ?,"
            + "  school = ?,  address = ?,  zipcode = ?,  city = ?,  email = ?,  mother = ?,  mother_phone = ?,  father = ?,  father_phone = ?,"
            + "  home_phone = ?,  emergency = ?,  join_date = ?,  hear_from = ?,  password = ?,  userName = ?,  password_question_id = ?,  passwordAnswer = ? , notes = ? WHERE student_id = ?";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setInt(1, student.getCreated_by().getUser_id());
         stmt.setString(2, student.getSex().toString());
         stmt.setString(3, student.getFirstName());
         stmt.setString(4, student.getLastName());
         stmt.setDate(5, student.getCreateDate());
         stmt.setBoolean(6, student.isActive());
         stmt.setBoolean(7, student.isProspective());
         stmt.setDate(8, student.getBirthday());
         stmt.setString(9, student.getSchool());
         stmt.setString(10, student.getAddress());
         stmt.setString(11, student.getZipcode());
         stmt.setString(12, student.getCity());
         stmt.setString(13, student.getEmail());
         stmt.setString(14, student.getMother());
         stmt.setString(15, student.getMotherPhone());
         stmt.setString(16, student.getFather());
         stmt.setString(17, student.getFatherPhone());
         stmt.setString(18, student.getHomePhone());
         stmt.setString(19, student.getEmergency());
         stmt.setDate(20, student.getJoinDate());
         stmt.setString(21, student.getHearFrom());
         stmt.setString(22, student.getPassword());
         stmt.setString(23, student.getUserName());
         stmt.setInt(24, student.getPasswordQuestion().getPassword_qestion_id());
         stmt.setString(25, student.getPasswordAnswer());
         stmt.setString(26, student.getNotes());
         stmt.setInt(27, student.getStudent_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   public static void main(String args[]) throws DatabaseException {
      Student s = getStudentByID(1);
      
      System.out.println(s.getAge());
   }

   private static Student getSampleStudent() throws DatabaseException {

      Student s = new Student();
      s.setStudent_id(1);
      s.setCreated_by(UserDBM.getUserByID(4));
      s.setSex(Sex.GIRL);
      s.setFirstName("Sam");
      s.setLastName("Zhang");
      s.setCreateDate(new Date(70, 1, 1));
      s.setActive(true);
      s.setProspective(true);
      s.setBirthday(new Date(70, 1, 1));
      s.setSchool("Centennial");
      s.setAddress("123 ontario str");
      s.setZipcode("V2D2S3");
      s.setCity("Coquitlam");
      s.setEmail("s@a.com");
      s.setMother("Jane");
      s.setMotherPhone("403-433-3242");
      s.setFather("Frank");
      s.setFatherPhone("123-123-1232");
      s.setHomePhone("123-321-2322");
      s.setEmergency("123-321-2322");
      s.setJoinDate(new Date(70, 1, 1));
      s.setHearFrom("Newpaper");
      s.setPassword("sam");
      s.setUserName("sam");
      PasswordQuestion p = new PasswordQuestion();
      p.setPassword_qestion_id(2);

      s.setPasswordQuestion(p);
      s.setPasswordAnswer("same");
      return s;
   }
}
