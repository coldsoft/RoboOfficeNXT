package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.List;

public class Membership extends Activity {

   
   private int daysPerPayment;
   private List<Timeslot> timeslots;

   public int getDaysPerPayment() {
      return daysPerPayment;
   }

   public void setDaysPerPayment(int daysPerPayment) {
      this.daysPerPayment = daysPerPayment;
   }

   public List<Timeslot> getTimeslots() {
      return timeslots;
   }

   public void setTimeslots(List<Timeslot> timeslots) {
      this.timeslots = timeslots;
   }

   
}