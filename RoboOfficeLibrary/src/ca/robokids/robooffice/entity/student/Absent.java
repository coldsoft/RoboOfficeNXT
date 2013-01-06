
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;

/**
 * 
 * 
 * @poseidon-object-id [I4979bea0m13ba8059043mm3d34]
 */
public class Absent {


    private int absent_id;

    private int student_id;
    private Date dateFrom;

    private Date dateTo;
    private Date createDate;
    private User createdBy;

   public int getAbsent_id() {
      return absent_id;
   }

   public void setAbsent_id(int absent_id) {
      this.absent_id = absent_id;
   }

   public Date getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }

   public User getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(User createdBy) {
      this.createdBy = createdBy;
   }

   public Date getDateFrom() {
      return dateFrom;
   }

   public void setDateFrom(Date dateFrom) {
      this.dateFrom = dateFrom;
   }

   public Date getDateTo() {
      return dateTo;
   }

   public void setDateTo(Date dateTo) {
      this.dateTo = dateTo;
   }

   public int getStudent_id() {
      return student_id;
   }

   public void setStudent_id(int student_id) {
      this.student_id = student_id;
   }
    
    
 }
