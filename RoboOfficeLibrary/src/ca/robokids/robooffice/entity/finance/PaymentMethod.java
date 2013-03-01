
package ca.robokids.robooffice.entity.finance;


public class PaymentMethod {

    private int payment_method_id;
    private String methodName;

   public String getMethodName() {
      return methodName;
   }

   public void setMethodName(String methodName) {
      this.methodName = methodName;
   }

   public int getPayment_method_id() {
      return payment_method_id;
   }

   public void setPayment_method_id(int payment_method_id) {
      this.payment_method_id = payment_method_id;
   }
    
   @Override
    public String toString()
    {
       return this.getMethodName();
    }
 }
