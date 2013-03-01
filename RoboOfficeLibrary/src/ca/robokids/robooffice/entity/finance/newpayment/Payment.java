/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.finance.newpayment;

import ca.robokids.robooffice.entity.finance.PaymentMethod;
import ca.robokids.robooffice.entity.student.Student;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class Payment {
   private List<PaymentEntry> paymentEntries;
   private PaymentMethod paymentMethod;
   private Student student;
   private String notes;
   private String invoice;

   public String getInvoice() {
      return invoice;
   }

   public void setInvoice(String invoice) {
      this.invoice = invoice;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public List<PaymentEntry> getPaymentEntries() {
      return paymentEntries;
   }

   public void setPaymentEntries(List<PaymentEntry> paymentEntries) {
      this.paymentEntries = paymentEntries;
   }

   public PaymentMethod getPaymentMethod() {
      return paymentMethod;
   }

   public void setPaymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }
   
   
}
