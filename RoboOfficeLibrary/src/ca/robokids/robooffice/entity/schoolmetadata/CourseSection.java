
package ca.robokids.robooffice.entity.schoolmetadata;

/**
 * 
 * 
 * @poseidon-object-id [I4979bea0m13ba8059043mm5b81]
 */
public class CourseSection {


    private int section_id;

    private Course course;

    private Timeslot timeslot;

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
