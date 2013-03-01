/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.finance.newpayment;

import ca.robokids.robooffice.entity.finance.Tax;


/**
 *
 * @author Coldsoft
 */
public class PaymentEntry {

   
   protected float discountAmount;
   protected String discountReason;
   protected float total;
   protected Tax tax;

   public Tax getTax() {
      return tax;
   }

   public void setTax(Tax tax) {
      this.tax = tax;
   }
   

   public float getDiscountAmount() {
      return discountAmount;
   }

   public void setDiscountAmount(float discountAmount) {
      this.discountAmount = discountAmount;
   }

   public String getDiscountReason() {
      return discountReason;
   }

   public void setDiscountReason(String discountReason) {
      this.discountReason = discountReason;
   }

   public float getTotal() {
      return total;
   }

   public void setTotal(float total) {
      this.total = total;
   }
   
   
}
