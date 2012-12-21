
package ca.robokids.robooffice.entity.user;


public class PasswordQuestion {


    private int password_qestion_id;
    private String question;

   public int getPassword_qestion_id() {
      return password_qestion_id;
   }

   public void setPassword_qestion_id(int password_qestion_id) {
      this.password_qestion_id = password_qestion_id;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }
   public String toString()
   {
      return this.getQuestion();
      
   }
    
  public boolean equals(Object p)
  {
     return ((PasswordQuestion)p).getPassword_qestion_id()==this.getPassword_qestion_id();
  }
    
 }
