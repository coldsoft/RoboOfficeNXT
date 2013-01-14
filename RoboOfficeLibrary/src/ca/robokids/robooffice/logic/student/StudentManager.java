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
import ca.robokids.robooffice.logic.system.SystemLog;

/**
 *
 * @author Coldsoft
 */
public class StudentManager {
   
   public static void addStudent(Student s) throws BadFieldException, DatabaseException
   {
      CheckFields.checkStudent(s);
      s.setStudent_id(StudentDBM.createStudent(s));

      //Event Logging
      String details = "created a new student: " + s.toString();
      SystemLog.createStudentLog(Operation.NEW_STUDENT, details, s.getStudent_id());
   }
   
}
