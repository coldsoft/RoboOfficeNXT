
package ca.robokids.robooffice.entity.schoolmetadata;


public class Course {

    private int course_id;
    private Classroom classroom;
    private String code;
    private String name;
    private String description;
    private int duration;
    private float rate;
    private boolean deleted;
    private ProgressReportType progressReportType;

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

   public int getCourse_id() {
      return course_id;
   }

   public void setCourse_id(int course_id) {
      this.course_id = course_id;
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

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public ProgressReportType getProgressReportType() {
      return progressReportType;
   }

   public void setProgressReportType(ProgressReportType progressReportType) {
      this.progressReportType = progressReportType;
   }

   public float getRate() {
      return rate;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }
    
    
 }
