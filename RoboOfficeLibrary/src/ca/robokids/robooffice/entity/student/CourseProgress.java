
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.CourseSection;


public class CourseProgress extends Progress {

    private CourseSection section;
    private int progress;
    private int totalClass;
    private int availableCredit;
    private int bonusClass;
    private CourseProgress nextProgress;

   public int getAvailableCredit() {
      return availableCredit;
   }

   public void setAvailableCredit(int availableCredit) {
      this.availableCredit = availableCredit;
   }

   public int getBonusClass() {
      return bonusClass;
   }

   public void setBonusClass(int bonusClass) {
      this.bonusClass = bonusClass;
   }



   public CourseProgress getNextProgress() {
      return nextProgress;
   }

   public void setNextProgress(CourseProgress nextProgress) {
      this.nextProgress = nextProgress;
   }

   public int getProgress() {
      return progress;
   }

   public void setProgress(int progress) {
      this.progress = progress;
   }

   public CourseSection getSection() {
      return section;
   }

   public void setSection(CourseSection section) {
      this.section = section;
   }

   public int getTotalClass() {
      return totalClass;
   }

   public void setTotalClass(int totalClass) {
      this.totalClass = totalClass;
   }
    
 }
