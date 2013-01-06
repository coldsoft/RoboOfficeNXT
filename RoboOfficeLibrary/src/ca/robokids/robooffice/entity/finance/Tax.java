
package ca.robokids.robooffice.entity.finance;


public class Tax {


    private int tax_id;
    private float taxPercentage;

   public float getTaxPercentage() {
      return taxPercentage;
   }

   public void setTaxPercentage(float taxPercentage) {
      this.taxPercentage = taxPercentage;
   }

   public int getTax_id() {
      return tax_id;
   }

   public void setTax_id(int tax_id) {
      this.tax_id = tax_id;
   }
    
    
 }
