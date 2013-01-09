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

/**
 *
 * @author Coldsoft
 */
public class StudentManager {
   
   public static void addStudent(Student s) throws BadFieldException, DatabaseException
   {
      CheckFields.checkStudent(s);
      StudentDBM.createStudent(s);
   }
   
}
