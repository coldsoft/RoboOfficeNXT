/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.schoolmetadata;

/**
 *
 * @author Coldsoft
 */
public class Activity {
   
   
   private String code;
   private int id;
   private String name;
   private String description;
   private float rate;
   private Classroom classroom;

   public String toString()
   {
      return getName();
   }
   public Classroom getClassroom() {
      return classroom;
   }

   public void setClassroom(Classroom classroom) {
      this.classroom = classroom;
   }
   private boolean deleted;

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
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

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
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
   
   
}
