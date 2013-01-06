
package ca.robokids.robooffice.entity.schoolmetadata;

import java.sql.Time;


public class Timeslot implements Comparable {


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
    
   public String toString()
   {
      return this.dayOfWeek.toString()+ " " + this.getStart().toString().substring(0, 5);
   }
   public boolean equals(Object o)
   {
      Timeslot t = (Timeslot) o;
      return ((t.getStart().getTime() == this.getStart().getTime()) && 
          t.getDayOfWeek().equals(this.getDayOfWeek()));

   }
   @Override
   public int compareTo(Object o) {
      Timeslot t = (Timeslot) o;
      if (equals(t))
         return 0;
      
      if (t.getDayOfWeek().compareTo(dayOfWeek) == 0)
         return start.compareTo(t.getStart());
      
      return dayOfWeek.compareTo(t.getDayOfWeek());
   }
   
   public static void main(String [] args)
   {
      Timeslot t = new Timeslot();
      Timeslot t2 = new Timeslot();
      
      t.setDayOfWeek(DayOfWeek.Wed);
      t2.setDayOfWeek(DayOfWeek.Wed);
      t.setStart(new Time(23,32,00));
      t2.setStart(new Time(12,00,00));
      
      System.out.println(t.compareTo(t2));
   }
    
 }
