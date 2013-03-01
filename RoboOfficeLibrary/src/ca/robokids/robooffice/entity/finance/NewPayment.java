/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.finance;

import ca.robokids.robooffice.entity.finance.newpayment.PaymentEntry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class NewPayment {
   
   private List<PaymentEntry> entries = new ArrayList();
   private PaymentMethod paymentMethod;
   private String invoice;
   
   public float getGrandTotal(){
      float total = 0;
      for (PaymentEntry e : entries){
         total += e.getTotal();
      }
      return total;       
   }

   public PaymentMethod getPaymentMethod() {
      return paymentMethod;
   }

   public void setPaymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
   }
   public String getInvoice() {
      return invoice;
   }
   
   
   
      

}
