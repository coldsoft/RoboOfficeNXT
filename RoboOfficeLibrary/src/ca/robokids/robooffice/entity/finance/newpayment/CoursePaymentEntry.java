/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.finance.newpayment;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.entity.schoolmetadata.Course;
import ca.robokids.robooffice.entity.schoolmetadata.Timeslot;
import ca.robokids.robooffice.entity.student.CourseProgress;
import ca.robokids.robooffice.logic.finance.TaxManager;
import ca.robokids.robooffice.logic.schoolsettings.SchoolManager;
import java.sql.Date;

/**
 *
 * @author Coldsoft
 */
public class CoursePaymentEntry extends PaymentEntry {


   //set a Course Payment Entry's default setting. after the course and payment type is chosen.
   
   public static void setDefaultValues(CoursePaymentEntry paymentEntry) throws DatabaseException {
      if (paymentEntry.type == CoursePaymentType.NEW_COURSE)
      {
         paymentEntry.setAvailableCredit(0);
         paymentEntry.setDiscountAmount(0);
         paymentEntry.setDiscountClass(0);
         paymentEntry.setDiscountReason("");
         paymentEntry.setStartDate(new Date(System.currentTimeMillis()));
         paymentEntry.setWeeks(12);
         paymentEntry.setLastProgress(null);
         if (paymentEntry.course.hasTax()) {
            paymentEntry.setTax(TaxManager.getCurrentTax());
         }else
         {
            paymentEntry.setTax(TaxManager.getNoTax());
         }
         paymentEntry.setTimeslot(null);
         paymentEntry.setTotal(CoursePaymentEntry.getTotal(paymentEntry));   
      }
   }

   public static float getTotal(CoursePaymentEntry paymentEntry) {
      float discountAmount = paymentEntry.getDiscountAmount();
      float discountClass = paymentEntry.getDiscountClass();
      
      float tax = paymentEntry.getTax().getTaxPercentage();
      float credit = paymentEntry.getAvailableCredit();
      
      float total = 0;
      switch(paymentEntry.type)
      {
         case NEW_COURSE:
         case LEVELUP:
            total = (paymentEntry.getWeeks()-discountClass) * paymentEntry.getCourse().getRate() 
               - discountAmount - credit;
            break;
         case PAY_ADVANCE:
            total = (paymentEntry.getWeeks()-discountClass) * paymentEntry.getCourse().getRate() 
               - discountAmount;
           break;
         default:
            
            
      }
      total *= (1+tax);
      return total;
   }

   private Course course;
   private CourseProgress lastProgress;
   private Timeslot timeslot;
   private Date startDate;
   private Date expireDate;
   private float availableCredit;
   private int discountClass;
   private int weeks;
   private CoursePaymentType type;
   public static int DEFAULT_WEEKS = 12;

   public float getAvailableCredit() {
      return availableCredit;
   }

   public void setAvailableCredit(float availableCredit) {
      this.availableCredit = availableCredit;
   }

   
   public Timeslot getTimeslot() {
      return timeslot;
   }

   public void setTimeslot(Timeslot timeslot) {
      this.timeslot = timeslot;
   }

   public Course getCourse() {
      return course;
   }

   public void setCourse(Course course) {
      this.course = course;
   }

   public int getDiscountClass() {
      return discountClass;
   }

   public void setDiscountClass(int discountClass) {
      this.discountClass = discountClass;
   }

   public Date getExpireDate() {
      return expireDate;
   }

   public void setExpireDate(Date expireDate) {
      this.expireDate = expireDate;
   }

   public CourseProgress getLastProgress() {
      return lastProgress;
   }

   public void setLastProgress(CourseProgress lastProgress) {
      this.lastProgress = lastProgress;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public int getWeeks() {
      return weeks;
   }

   public void setWeeks(int weeks) {
      this.weeks = weeks;
      this.setExpireDate(SchoolManager.calculateCourseExpireDate(startDate, weeks));
   }

   public CoursePaymentType getType() {
      return type;
   }

   public void setType(CoursePaymentType type) {
      this.type = type;
   }

   
  

   

   public enum CoursePaymentType {

      NEW_COURSE, PAY_ADVANCE, LEVELUP;
   }
}
