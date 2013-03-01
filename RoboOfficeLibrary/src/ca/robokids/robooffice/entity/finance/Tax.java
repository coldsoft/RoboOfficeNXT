
package ca.robokids.robooffice.entity.finance;

import java.text.NumberFormat;


public class Tax {


    private int tax_id;
    private float taxPercentage;
    private TaxKind kind;
    private boolean current;

   public String toString()
   {
      return kind.toString() + " " + getTaxPercentageString();
   }
   public float getTaxPercentage() {
      return taxPercentage;
   }
   
   public String getTaxPercentageString(){
      return NumberFormat.getPercentInstance().format(getTaxPercentage());
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

   public boolean isCurrent() {
      return current;
   }

   public void setCurrent(boolean current) {
      this.current = current;
   }

   public TaxKind getKind() {
      return kind;
   }

   public void setKind(TaxKind kind) {
      this.kind = kind;
   }
   
   
   public enum TaxKind {
      NO_TAX ("No tax"), 
      GST("GST"), 
      HST("HST"), 
      PST("PST"), 
      GST_PST("PST+GST");
      
      private String name;
      private TaxKind(String value)
      {
         name = value;
      }
      
      public String toString()
      {
         return name;
      }
      
      public static TaxKind getValue(String value)
   {
      TaxKind[] all = TaxKind.values();
      for (TaxKind o : all)
      {
         if (o.toString().equals(value))
            return o;
      }
      throw new IllegalArgumentException();
   }
   }
    
 }
