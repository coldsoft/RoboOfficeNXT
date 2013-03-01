/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.Utilities;
import ca.robokids.robooffice.entity.finance.PaymentRecord;
import java.sql.Date;

/**
 *
 * @author Coldsoft
 */
public abstract class Progress {
   
   private int progressID;
   private Student student;
   private Date startDate;
   private Date endDate;
   private int payment_record_id;
   private boolean active;
   private int modifiedBy;
   private Date modifiedDate;

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   public int getModifiedBy() {
      return modifiedBy;
   }

   public void setModifiedBy(int modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Date getModifiedDate() {
      return modifiedDate;
   }
 
   public String getModifiedDateString() {
      return Utilities.formatDate(getModifiedDate());
   }
   
   public void setModifiedDate(Date modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   
   public Date getEndDate() {
      return endDate;
   }
   
   public String getEndDateString() {
      return Utilities.formatDate(getEndDate());
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public int getProgressID() {
      return progressID;
   }

   public void setProgressID(int progressID) {
      this.progressID = progressID;
   }

   public int getPayment_record_id() {
      return payment_record_id;
   }

   public void setPayment_record_id(int payment_record_id) {
      this.payment_record_id = payment_record_id;
   }


   

   public Date getStartDate() {
      return startDate;
   }

   public String getStartDateString() {
      return Utilities.formatDate(getStartDate());
   }
   
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }
   
   
}
