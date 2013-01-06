/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.CourseSection;
import ca.robokids.robooffice.entity.schoolmetadata.Project;

/**
 *
 * @author Coldsoft
 */
public class ClassRecord extends Record{
   
   private CourseSection section;
   private Project project;
   private boolean complete;
   private boolean test;

   public boolean isComplete() {
      return complete;
   }

   public void setComplete(boolean complete) {
      this.complete = complete;
   }

   public Project getProject() {
      return project;
   }

   public void setProject(Project project) {
      this.project = project;
   }

   public CourseSection getSection() {
      return section;
   }

   public void setSection(CourseSection section) {
      this.section = section;
   }

   public boolean isTest() {
      return test;
   }

   public void setTest(boolean test) {
      this.test = test;
   }
   
}
