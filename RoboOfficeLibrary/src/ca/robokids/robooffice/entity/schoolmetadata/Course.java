
package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.List;


public class Course extends Activity{

    
    private int duration;  
    private int report_type_id;
    private List<Timeslot> timeslots;

   public List<Timeslot> getTimeslots() {
      return timeslots;
   }

   public void setTimeslots(List<Timeslot> timeslots) {
      this.timeslots = timeslots;
   }

   public int getReport_type_id() {
      return report_type_id;
   }

   public void setReport_type_id(int report_type_id) {
      this.report_type_id = report_type_id;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   
    
    
 }
