/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.system;

/**
 *
 * @author Coldsoft
 */
public enum Operation {
   LOGIN("Login"),
   LOGOUT("Logout"),
   COURSE_SETTING("Course settings"),
   MEMBERSHIP_SETTING("Membership settings"),
   CLASSROOM_SETTING("Classroom settings"),
   PROJECT_SETTING("Project settings"),
   FINANCE_SETTING("Finance settings"),
   DATABASE_BACKUP("Backup database"),
   USER_SETTING("User Account settings"),
   USER_GROUP_SETTING("Usergroup settings"),
   FORGOTPASSWORD("Forgot password"),
   NEW_STUDENT("New Student Registration"),
   UNKNOWN("");
   
   private String value;
   
   private Operation(String value)
   {
      this.value  = value;
   }
   @Override
   public String toString() {
            return value;
        }
   
   public static Operation getValue(String value)
   {
      Operation[] all = Operation.values();
      for (Operation o : all)
      {
         if (o.toString().equals(value))
            return o;
      }
      throw new IllegalArgumentException();
   }
   public static void main(String arg[])
   {
      
      System.out.println(Operation.getValue("Login"));
   }
}
