
package ca.robokids.robooffice.entity.schoolmetadata;

import java.sql.Time;


public class Timeslot {


    private int timeslot_id;
    private DayOfWeek dayOfWeek;
    private Time start;

   public DayOfWeek getDayOfWeek() {
      return dayOfWeek;
   }

   public void setDayOfWeek(DayOfWeek dayOfWeek) {
      this.dayOfWeek = dayOfWeek;
   }

   public Time getStart() {
      return start;
   }

   public void setStart(Time start) {
      this.start = start;
   }

   public int getTimeslot_id() {
      return timeslot_id;
   }

   public void setTimeslot_id(int timeslot_id) {
      this.timeslot_id = timeslot_id;
   }
    
    
 }
