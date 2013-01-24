
package ca.robokids.robooffice.entity.schoolmetadata;


public class CourseSection {


    private int section_id;

    private Course course;

    private Timeslot timeslot;

    private boolean delete;

   public boolean isDelete() {
      return delete;
   }

   public void setDelete(boolean delete) {
      this.delete = delete;
   }
    
    
   public Course getCourse() {
      return course;
   }

   public void setCourse(Course course) {
      this.course = course;
   }





   public int getSection_id() {
      return section_id;
   }

   public void setSection_id(int section_id) {
      this.section_id = section_id;
   }

   public Timeslot getTimeslot() {
      return timeslot;
   }

   public void setTimeslot(Timeslot timeslot) {
      this.timeslot = timeslot;
   }
    
    
 }
