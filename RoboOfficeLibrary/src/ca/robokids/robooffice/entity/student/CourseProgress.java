
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.CourseSection;


public class CourseProgress extends Progress {

    private CourseSection section;
    private int progress;
    private int totalClass;
    private float availableCredit;
    private int sectionChanged;
    private int bonusClass;
    private int nextProgressID;

   public float getAvailableCredit() {
      return availableCredit;
   }

   public void setAvailableCredit(float availableCredit) {
      this.availableCredit = availableCredit;
   }

   public int getSectionChanged() {
      return sectionChanged;
   }

   public void setSectionChanged(int sectionChanged) {
      this.sectionChanged = sectionChanged;
   }

   
   public int getBonusClass() {
      return bonusClass;
   }

   public void setBonusClass(int bonusClass) {
      this.bonusClass = bonusClass;
   }

   public int getNextProgressID() {
      return nextProgressID;
   }

   public void setNextProgressID(int nextProgressID) {
      this.nextProgressID = nextProgressID;
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
