
package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.Date;


public class Holiday {


    private int holiday_id;
    private String name;
    private java.util.Date date;

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public int getHoliday_id() {
      return holiday_id;
   }

   public void setHoliday_id(int holiday_id) {
      this.holiday_id = holiday_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
    
 }
