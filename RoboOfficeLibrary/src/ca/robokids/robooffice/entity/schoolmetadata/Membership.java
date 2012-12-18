package ca.robokids.robooffice.entity.schoolmetadata;

public class Membership {

   private int membership_id;
   private Classroom classroom;
   private String code;
   private String name;
   private String description;
   private int daysPerPayment;
   private float rate;

   public Classroom getClassroom() {
      return classroom;
   }

   public void setClassroom(Classroom classroom) {
      this.classroom = classroom;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public int getDaysPerPayment() {
      return daysPerPayment;
   }

   public void setDaysPerPayment(int daysPerPayment) {
      this.daysPerPayment = daysPerPayment;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getMembership_id() {
      return membership_id;
   }

   public void setMembership_id(int membership_id) {
      this.membership_id = membership_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getRate() {
      return rate;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }
   private boolean deleted;
}
