package ca.robokids.robooffice.entity.schoolmetadata;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Membership extends Activity {

   
   private Date startDate;
   private Date endDate;

   public Date getEndDate() {
      return endDate;
   }

   public String getEndDateString()
   {
      SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
      if (endDate != null)
         return format.format(endDate);
      return "";
   }
   
   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getStartDate() {
      return startDate;
   }

   public String getStartDateString()
   {
      SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
      if (startDate != null)
         return format.format(startDate);
      return "";
   }
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   

   
   
}