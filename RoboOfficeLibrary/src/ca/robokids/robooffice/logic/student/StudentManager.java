/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.student;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.studentmanagement.StudentDBM;
import ca.robokids.robooffice.entity.student.Student;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.logic.system.SystemLog;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Coldsoft
 */
public class StudentManager {

   public static void addStudent(Student s) throws BadFieldException, DatabaseException {

      s.setActive(false);
      s.setDeleted(false);
      s.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
      s.setCreated_by(UserActivity.loginUser);
      s.setJoinDate(s.getModifiedDate());
      s.setPassword("robokids");
      s.setUserName("robokids");
      s.setPasswordAnswer("robokids");
      PasswordQuestion p = new PasswordQuestion();
      p.setPassword_qestion_id(1);
      s.setPasswordQuestion(p);
      s.setProspective(true);

      CheckFields.checkStudent(s);

      s.setStudent_id(StudentDBM.createStudent(s));

      //Event Logging
      String details = "created a new student: " + s.toString();
      SystemLog.createStudentLog(Operation.NEW_STUDENT, details, s.getStudent_id());
   }

   public static void deleteStudent(Student student) throws DatabaseException {
      StudentDBM.deleteStudent(student.getStudent_id());
      
      //Event Logging
      String details = student.toString() + " was deleted.";
      SystemLog.createStudentLog(Operation.NEW_STUDENT, details, student.getStudent_id());
   }
   public static void modifyStudent(Student s) throws BadFieldException, DatabaseException {
      
      s.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
      s.setPassword("robokids");
      s.setUserName("robokids");
      s.setPasswordAnswer("robokids");
      PasswordQuestion p = new PasswordQuestion();
      p.setPassword_qestion_id(1);
      s.setPasswordQuestion(p);

      CheckFields.checkStudent(s);

      StudentDBM.modifyStudent(s);

      //Event Logging
      String details = s.toString() + "'s information was modified.";
      SystemLog.createStudentLog(Operation.STUDENT, details, s.getStudent_id());
   }
   /**
    * change the student active flag to the new status
    * @param student
    * @param status
    */
   public static void setStudentStatus(Student student, boolean newStatus) throws DatabaseException {
         if (student.isActive() == newStatus)
            return;
         StudentDBM.modifyStudentStatus(student.getStudent_id(),newStatus,false);
         
         //Event Logging
      String details = student.toString() + " active flag is set to " + newStatus;
      SystemLog.createStudentLog(Operation.STUDENT, details, student.getStudent_id());
   }

   public static List<Student> getSampleStudentList() {
      List<Student> list = new ArrayList();
      Random random = new Random();
      int size = random.nextInt(200);
      for (int i = 0; i < size; i++) {
         Student s = new Student();
         s.setFirstName(getRandomFirstName());
         s.setLastName(getRandomLastName());
         list.add(s);
      }
      return list;


   }

   public static String getRandomFirstName() {
      Random random = new Random();
      String names[] = {"Amy", "James", "John", "Brian", "Dexter", "Julia", "Brat", "Sean", "Joe", "Dylan", "Shawn", "Stewart"};
      return names[random.nextInt(names.length)];
   }

   public static String getRandomLastName() {
      Random random = new Random();
      String names[] = {"Smith", "Li", "Zhang", "Qian", "Cheng", "Wang", "Lee", "Zhao", "Sun", "Zhou", "Wu", "Hua"};
      return names[random.nextInt(names.length)];
   }

   public static List<Student> searchStudentName(String name) throws DatabaseException {
      return StudentDBM.searchStudentByName(name);
   }

   

   
}
