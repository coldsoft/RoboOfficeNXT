
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;


public class Record {


    private int record_id;
    private int progressID;
    private Date attendDate;
    private User modifiedBy;
    private Date modified_Date;

   public Date getAttendDate() {
      return attendDate;
   }

   public void setAttendDate(Date attendDate) {
      this.attendDate = attendDate;
   }

   public User getModifiedBy() {
      return modifiedBy;
   }

   public void setModifiedBy(User modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Date getModified_Date() {
      return modified_Date;
   }

   public void setModified_Date(Date modified_Date) {
      this.modified_Date = modified_Date;
   }

   public int getProgressID() {
      return progressID;
   }

   public void setProgressID(int progressID) {
      this.progressID = progressID;
   }

   public int getRecord_id() {
      return record_id;
   }

   public void setRecord_id(int record_id) {
      this.record_id = record_id;
   }


    
    
 }
