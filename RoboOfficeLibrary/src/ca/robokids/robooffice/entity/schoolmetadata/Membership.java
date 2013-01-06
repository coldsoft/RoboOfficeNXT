package ca.robokids.robooffice.entity.schoolmetadata;

import java.sql.Date;
import java.util.List;

public class Membership extends Activity {

   
   private Date startDate;
   private Date endDate;

   public Date getEndDate() {
      return endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getStartDate() {
      return startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   

   
   
}