
package ca.robokids.robooffice.entity.schoolmetadata;


public class ClubProject {


    private int project_id;


    private int membership_id;


    private String name;


    private boolean deleted;

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public int getMembership_id() {
      return membership_id;
   }

   public void setMembership_id(int membership_id) {
      this.membership_id = membership_id;
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
