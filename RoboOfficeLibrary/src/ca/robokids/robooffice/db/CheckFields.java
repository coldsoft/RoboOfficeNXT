/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db;

import ca.robokids.robooffice.entity.finance.Fee;
import ca.robokids.exception.BadFieldException;
import ca.robokids.robooffice.entity.schoolmetadata.*;
import ca.robokids.robooffice.entity.student.Student;
import ca.robokids.robooffice.entity.system.Log;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.entity.user.Action;
import ca.robokids.robooffice.entity.user.User;
import ca.robokids.robooffice.entity.user.UserGroup;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class CheckFields {
   
   public static void checkUser(User user) throws BadFieldException
   {
      checkLength(1,45,user.getUserName(),
                 "Username is empty.","Username must be less than 45 characters.");
      checkLength(1,45,user.getFirstName(),
                 "First name is empty.","First name must be less than 45 characters.");
      checkLength(1,45,user.getLastName(),
                 "Last name is empty.","Last name must be less than 45 characters.");
      checkLength(1,80,user.getAddress(),
                 "Address is empty.","Address must be less than 80 characters.");
      checkLength(1,80,user.getEmail(),
                 "Email is empty.","Email must be less than 45 characters.");
      checkLength(4,80,user.getPassword(),
                 "Password must be more that 4 characters.","Password must be less than 80 characters.");
      checkLength(1,45,user.getPasswordAnswer(),
                 "Password answer is empty","Password must be less than 45 characters.");
      checkLength(9,9,user.getSIN(),
                 "SIN number incorrect.","SIN number incorrect.");
      checkLength(12,12,user.getPhone(),
                 "Phone number format incorrect.","Phone number format incorrect.");
      
      if (user.getEmail().indexOf("@") < 0)
         throw new BadFieldException("Email format incorrect.");
      List<UserGroup> groups = user.getUserGroups();
      if (groups == null || groups.size()<1)
         throw new BadFieldException("User must belong to a user group.");
   }
   
   public static void checkGroup(UserGroup group) throws BadFieldException 
   {
      checkLength(1,30,group.getGroupName(),
                 "Usergroup name is empty","Usergroup name must be less than 30 characters");
      List<Action> actions = group.getActions();
      if (actions == null || actions.size() < 1)
         throw new BadFieldException("User group must have a privilege.");
   }
   
   public static void checkClassroom(Classroom cr) throws BadFieldException {
      checkLength(1,45,cr.getName(),
                 "Classroom name is empty","name must be less than 45 characters.");
      checkLength(1,50,cr.getCapacity(),
                  "Capacity is 0.","Capacity is too large(>50).");
      
         
   }
   public static void checkFee(Fee fee) throws BadFieldException {
      checkLength(1,45,fee.getName(),
         "Fee name is empty","name must be less than 45 characters");
      checkLength(1,200,fee.getDescription(),
         "Description is empty", "Description must be less than 200 characters");
      if (fee.getRate() < 0.0)
         throw new BadFieldException("Rate is negative.");
   }

   public static void checkProgressReportType(ProgressReportType t) throws BadFieldException {
      checkLength(1,45,t.getName(),"Name is empty.","name must be less than 45 characters.");
      checkLength(1,100,t.getMaxScore(),"Maximum score too small.", "Maximum score too big");
      checkLength(1,30,t.getSection1(),"Section 1 name is empty", "Section 1 name > 30 characters");
      checkLength(1,30,t.getSection2(),"Section 2 name is empty", "Section 2 name > 30 characters");
      checkLength(1,30,t.getSection3(),"Section 3 name is empty", "Section 3 name > 30 characters");
      checkLength(1,30,t.getSection4(),"Section 4 name is empty", "Section 4 name > 30 characters");
      checkLength(1,30,t.getCriteria1(),"Criteria 1 name is empty", "Criteria 1 name > 30 characters");
      checkLength(1,30,t.getCriteria2(),"Criteria 2 name is empty", "Criteria 2 name > 30 characters");
      checkLength(1,30,t.getCriteria3(),"Criteria 3 name is empty", "Criteria 3 name > 30 characters");
      checkLength(1,30,t.getCriteria4(),"Criteria 4 name is empty", "Criteria 4 name > 30 characters");
      checkLength(1,30,t.getCriteria5(),"Criteria 5 name is empty", "Criteria 5 name > 30 characters");
      checkLength(1,30,t.getCriteria6(),"Criteria 6 name is empty", "Criteria 6 name > 30 characters");
      checkLength(1,30,t.getCriteria7(),"Criteria 7 name is empty", "Criteria 7 name > 30 characters");
      checkLength(1,30,t.getCriteria8(),"Criteria 8 name is empty", "Criteria 8 name > 30 characters");
      checkLength(1,30,t.getCriteria9(),"Criteria 9 name is empty", "Criteria 9 name > 30 characters");
      checkLength(1,30,t.getCriteria10(),"Criteria 10 name is empty", "Criteria 10 name > 30 characters");
      checkLength(1,30,t.getCriteria11(),"Criteria 11 name is empty", "Criteria 11 name > 30 characters");
      checkLength(1,30,t.getCriteria12(),"Criteria 12 name is empty", "Criteria 12 name > 30 characters");
      
   }

   private static void checkCanadianZipcode (String zipcode) throws BadFieldException
   {
      boolean isCorrectZipcode = zipcode.matches("^[A-Z][0-9][A-Z][0-9][A-Z][0-9]$");
        if(!isCorrectZipcode)
        {
            throw new BadFieldException("Please enter correct Canadian zipcode format.");
        }
      
   }
   
   private static void checkEmail(String email) throws BadFieldException
   {
      boolean isCorrectEmail = email.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if(!isCorrectEmail)
        {
            throw new BadFieldException("Please enter correct email format.");
        }
   }
   private static void checkLength(int min, int max, int field, String minMsg, String maxMsg) throws BadFieldException
   {
      if (field < min ) {
         throw new BadFieldException(minMsg);
      }
      if (field > max) {
         throw new BadFieldException(maxMsg);
      }
   }
   private static void checkLength(int min, int max, float field, String minMsg, String maxMsg) throws BadFieldException
   {
      if (field < min ) {
         throw new BadFieldException(minMsg);
      }
      if (field > max) {
         throw new BadFieldException(maxMsg);
      }
   }
   private static void checkLength(int min, int max, String field, String minMsg, String maxMsg) throws BadFieldException
   {
      if (field == null || field.length() < min ) {
         throw new BadFieldException(minMsg);
      }
      if (field.length() > max) {
         throw new BadFieldException(maxMsg);
      }
   }

   public static void checkProjects(Project p) throws BadFieldException {
      checkLength(3,100,p.getName(),"Please give the project a more meaningful name.","Project name needs to be less than 100 chars.");
   }
   
   public static void checkCourse(Course c) throws BadFieldException
   {
      checkLength(4,40,c.getName(),"Please give the course a more meaningful name.", "Course name needs to be less than 40 chars.");
      checkLength(4,4,c.getCode(), "Course Code needs to be 4 characters exactly.","Course Code needs to be 4 characters exactly.");
      checkLength(1,500,c.getDescription(),"Course Description cannot be empty.", "Course Description is too long");
      checkLength(1,10000,c.getDuration(),"Course Duration is too short. ", "Course Duration is too long");
      checkLength(0,10000,c.getRate(),"Course rate is negative.", "Too expensive. Consider change your business model.");
   }

   public static void checkStudent(Student s) throws BadFieldException {
      checkLength(1,40,s.getFirstName(),"First name is empty.","First name is too long");
      checkLength(1,40,s.getLastName(),"Last name is empty.", "Last name is too long");
      checkLength(1,40,s.getSchool(), "School name is empty.","School name is too long");
      checkLength(1,100,s.getAddress(),"Address is empty.","Address is too long");
      checkLength(1,100,s.getMother(),"Mother name is empty.","Mother name is too long");
      checkLength(1,45, s.getEmergency(),"Emergency contact # is empty.","Emergency contact # is too long");
      checkEmail(s.getEmail());

   }

   public static void checkMembership(Membership c) throws BadFieldException {
      checkLength(4,40,c.getName(),"Please give the membership a more meaningful name.", "Membership name needs to be less than 40 chars.");
      checkLength(4,4,c.getCode(), "Membership Code needs to be 4 characters exactly.","Membership Code needs to be 4 characters exactly.");
      checkLength(1,500,c.getDescription(),"Membership Description cannot be empty.", "Membership Description is too long");
      checkLength(1,10000,c.getDuration(),"Membership Duration is too short. ", "Membership Duration is too long");
      checkLength(0,10000,c.getRate(),"Membership rate is negative.", "Too expensive. Consider change your business model.");
      if (c.getStartDate().after(c.getEndDate()))
         throw new BadFieldException("Start date is after the end date.");
      
   }

   public static void checkLog(Log log) throws BadFieldException {
      checkLength(0,500,log.getDetail(),"","Log Detail is too long");
      if (Operation.UNKNOWN == log.getOperation())
         throw new BadFieldException("Log operation unknown.");
   }


   public static String sanitizeFilename(String name) {
    return name.replaceAll("[:\\\\/*?|<>]", "_");
  }

 


   

   
   
}
