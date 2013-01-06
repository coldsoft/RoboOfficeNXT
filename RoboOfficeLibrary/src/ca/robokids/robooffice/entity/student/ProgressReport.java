
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;

public class ProgressReport {


    private int progress_report_id;
    private ProgressReportType reportType;
    private int criteria1;
    private int criteria2;
    private int criteria3;
    private int criteria4;
    private int criteria5;
    private int criteria6;
    private int criteria7;
    private int criteria8;
    private int criteria9;
    private String section1_comment;
    private String section2_comment;
    private String section3_comment;
    private String section4_comment;
    private Date createdDate;
    User createdBy;

   public User getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(User createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedDate() {
      return createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public int getCriteria1() {
      return criteria1;
   }

   public void setCriteria1(int criteria1) {
      this.criteria1 = criteria1;
   }

   public int getCriteria2() {
      return criteria2;
   }

   public void setCriteria2(int criteria2) {
      this.criteria2 = criteria2;
   }

   public int getCriteria3() {
      return criteria3;
   }

   public void setCriteria3(int criteria3) {
      this.criteria3 = criteria3;
   }

   public int getCriteria4() {
      return criteria4;
   }

   public void setCriteria4(int criteria4) {
      this.criteria4 = criteria4;
   }

   public int getCriteria5() {
      return criteria5;
   }

   public void setCriteria5(int criteria5) {
      this.criteria5 = criteria5;
   }

   public int getCriteria6() {
      return criteria6;
   }

   public void setCriteria6(int criteria6) {
      this.criteria6 = criteria6;
   }

   public int getCriteria7() {
      return criteria7;
   }

   public void setCriteria7(int criteria7) {
      this.criteria7 = criteria7;
   }

   public int getCriteria8() {
      return criteria8;
   }

   public void setCriteria8(int criteria8) {
      this.criteria8 = criteria8;
   }

   public int getCriteria9() {
      return criteria9;
   }

   public void setCriteria9(int criteria9) {
      this.criteria9 = criteria9;
   }

   public int getProgress_report_id() {
      return progress_report_id;
   }

   public void setProgress_report_id(int progress_report_id) {
      this.progress_report_id = progress_report_id;
   }

   public ProgressReportType getReportType() {
      return reportType;
   }

   public void setReportType(ProgressReportType reportType) {
      this.reportType = reportType;
   }

   public String getSection1_comment() {
      return section1_comment;
   }

   public void setSection1_comment(String section1_comment) {
      this.section1_comment = section1_comment;
   }

   public String getSection2_comment() {
      return section2_comment;
   }

   public void setSection2_comment(String section2_comment) {
      this.section2_comment = section2_comment;
   }

   public String getSection3_comment() {
      return section3_comment;
   }

   public void setSection3_comment(String section3_comment) {
      this.section3_comment = section3_comment;
   }

   public String getSection4_comment() {
      return section4_comment;
   }

   public void setSection4_comment(String section4_comment) {
      this.section4_comment = section4_comment;
   }
    
    
 }
