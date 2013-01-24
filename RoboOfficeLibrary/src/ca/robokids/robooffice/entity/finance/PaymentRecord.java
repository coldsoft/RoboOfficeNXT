
package ca.robokids.robooffice.entity.finance;

import java.sql.Date;


public class PaymentRecord {


    private int payment_record_id;
    private int payment_history_id;
    private String feeName;
    private String feeDescription;
    private float rate;
    private int multiplier;
    private float availableCredit;
    private int discountClass;
    private int discountAmount;
    private String discountReason;
    private float tax;
    private float total;
    private Date startDate;
    private Date expireDate;

   public float getAvailableCredit() {
      return availableCredit;
   }

   public void setAvailableCredit(float availableCredit) {
      this.availableCredit = availableCredit;
   }

   public int getDiscountAmount() {
      return discountAmount;
   }

   public void setDiscountAmount(int discountAmount) {
      this.discountAmount = discountAmount;
   }

   public int getDiscountClass() {
      return discountClass;
   }

   public void setDiscountClass(int discountClass) {
      this.discountClass = discountClass;
   }

   public String getDiscountReason() {
      return discountReason;
   }

   public void setDiscountReason(String discountReason) {
      this.discountReason = discountReason;
   }

   public Date getExpireDate() {
      return expireDate;
   }

   public void setExpireDate(Date expireDate) {
      this.expireDate = expireDate;
   }

   public String getFeeDescription() {
      return feeDescription;
   }

   public void setFeeDescription(String feeDescription) {
      this.feeDescription = feeDescription;
   }

   public String getFeeName() {
      return feeName;
   }

   public void setFeeName(String feeName) {
      this.feeName = feeName;
   }

   public int getMultiplier() {
      return multiplier;
   }

   public void setMultiplier(int multiplier) {
      this.multiplier = multiplier;
   }

   public int getPayment_history_id() {
      return payment_history_id;
   }

   public void setPayment_history_id(int payment_history_id) {
      this.payment_history_id = payment_history_id;
   }

   public int getPayment_record_id() {
      return payment_record_id;
   }

   public void setPayment_record_id(int payment_record_id) {
      this.payment_record_id = payment_record_id;
   }

   public float getRate() {
      return rate;
   }

   public void setRate(float rate) {
      this.rate = rate;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public float getTax() {
      return tax;
   }

   public void setTax(float tax) {
      this.tax = tax;
   }

   public float getTotal() {
      return total;
   }

   public void setTotal(float total) {
      this.total = total;
   }
    
    
 }
