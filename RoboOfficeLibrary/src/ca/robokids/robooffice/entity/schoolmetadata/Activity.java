/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.schoolmetadata;

import java.text.NumberFormat;
import java.util.List;

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
   private int duration;
   private List<Timeslot> timeslots;
   boolean hasTax;

   public boolean hasTax() {
      return hasTax;
   }

   public void setHasTax(boolean hasTax) {
      this.hasTax = hasTax;
   }
   
   public List<Timeslot> getTimeslots() {
      return timeslots;
   }

   public void setTimeslots(List<Timeslot> timeslots) {
      this.timeslots = timeslots;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
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
   public String getRateString() {
      NumberFormat mf = NumberFormat.getCurrencyInstance();
      return mf.format(rate);
   }
   
      public String toString()
   {
      return this.getCode()+" "+this.getName();
   }
}
