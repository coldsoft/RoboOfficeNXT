/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.finance;

import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;
import java.text.NumberFormat;

/**
 *
 * @author Coldsoft
 */
public class Fee {
   
   private int fee_id;
   private String name;
   private String description;
   private Date date_created;
   private User created_by;
   private float rate;
   private boolean tax;
   
   public User getCreated_by() {
      return created_by;
   }

   public void setCreated_by(User created_by) {
      this.created_by = created_by;
   }

   public Date getDate_created() {
      return date_created;
   }

   public void setDate_created(Date date_created) {
      this.date_created = date_created;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getFee_id() {
      return fee_id;
   }

   public void setFee_id(int fee_id) {
      this.fee_id = fee_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public float getRate() {
      return rate;
   }

   public String getRateString()
   {
      NumberFormat mf = NumberFormat.getCurrencyInstance();
      return mf.format(rate);
   }
   public void setRate(float rate) {
      this.rate = rate;
   }

   public boolean isTax() {
      return tax;
   }

   public void setTax(boolean tax) {
      this.tax = tax;
   }
   @Override
   public String toString()
   {
      return getName()+": "+getRateString();
      
   }

   
   
}
