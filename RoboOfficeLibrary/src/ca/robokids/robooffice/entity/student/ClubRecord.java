
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.MembershipSection;
import ca.robokids.robooffice.entity.schoolmetadata.Project;


public class ClubRecord extends Record {

    private Project project;
    private MembershipSection section;

   public Project getProject() {
      return project;
   }

   public void setProject(Project project) {
      this.project = project;
   }

   public MembershipSection getSection() {
      return section;
   }

   public void setSection(MembershipSection section) {
      this.section = section;
   }
    
    
 }
