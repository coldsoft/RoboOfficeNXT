/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.finance.PaymentRecord;
import java.sql.Date;

/**
 *
 * @author Coldsoft
 */
public class Progress {
   
   private int progressID;
   private Student student;
   private Date startDate;
   private Date endDate;
   private PaymentRecord record;
   private boolean action;

   public boolean isAction() {
      return action;
   }

   public void setAction(boolean action) {
      this.action = action;
   }

   public Date getEndDate() {
      return endDate;
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

   public PaymentRecord getRecord() {
      return record;
   }

   public void setRecord(PaymentRecord record) {
      this.record = record;
   }

   public Date getStartDate() {
      return startDate;
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
