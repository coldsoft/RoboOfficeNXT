
package ca.robokids.robooffice.entity.schoolmetadata;

public class Project {


    private int project_id;

    private int course_id;
    private String name;
    private boolean deleted;

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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getProject_id() {
      return project_id;
   }

   public void setProject_id(int project_id) {
      this.project_id = project_id;
   }
    
    
 }
