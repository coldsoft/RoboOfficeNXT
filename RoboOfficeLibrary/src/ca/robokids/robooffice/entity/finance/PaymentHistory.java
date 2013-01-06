
package ca.robokids.robooffice.entity.finance;

import ca.robokids.robooffice.entity.student.Student;
import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;
import java.util.List;


public class PaymentHistory {

    private int payment_history_id;
    private Student student;
    private PaymentMethod paymentMethod;
    private Date datePaid;
    private float totalAmount;
    private String notes;
    private String invoice;
    User receivedBy;
    private List<PaymentRecord> paymentRecords;

   public Date getDatePaid() {
      return datePaid;
   }

   public void setDatePaid(Date datePaid) {
      this.datePaid = datePaid;
   }

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

   public PaymentMethod getPaymentMethod() {
      return paymentMethod;
   }

   public void setPaymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
   }

   public List<PaymentRecord> getPaymentRecords() {
      return paymentRecords;
   }

   public void setPaymentRecords(List<PaymentRecord> paymentRecords) {
      this.paymentRecords = paymentRecords;
   }

   public int getPayment_history_id() {
      return payment_history_id;
   }

   public void setPayment_history_id(int payment_history_id) {
      this.payment_history_id = payment_history_id;
   }

   public User getReceivedBy() {
      return receivedBy;
   }

   public void setReceivedBy(User receivedBy) {
      this.receivedBy = receivedBy;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }

   public float getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(float totalAmount) {
      this.totalAmount = totalAmount;
   }
    
    
 }
