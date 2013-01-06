
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import ca.robokids.robooffice.entity.schoolmetadata.Skill;
import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;

public class ReportCard {

    private int reportcard_id;
    private CourseProgress courseProgress;
    private ProgressReportType reportType;
    private Skill skills;
    private int criteria1;
    private int criteria2;
    private int criteria3;
    private int criteria4;
    private int criteria5;
    private int criteria6;
    private int criteria7;
    private int criteria8;
    private int criteria9;
    private String criteria10;
    private String criteria11;
    private String criteria12;
    private String section1_observation;
    private String section2_observation;
    private String section3_observation;
    private String section1_recommendation;
    private String section2_recommendation;
    private String section3_recommendation;
    private Date createDate;
    User createdBy;

   public CourseProgress getCourseProgress() {
      return courseProgress;
   }

   public void setCourseProgress(CourseProgress courseProgress) {
      this.courseProgress = courseProgress;
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

   public int getCriteria1() {
      return criteria1;
   }

   public void setCriteria1(int criteria1) {
      this.criteria1 = criteria1;
   }

   public String getCriteria10() {
      return criteria10;
   }

   public void setCriteria10(String criteria10) {
      this.criteria10 = criteria10;
   }

   public String getCriteria11() {
      return criteria11;
   }

   public void setCriteria11(String criteria11) {
      this.criteria11 = criteria11;
   }

   public String getCriteria12() {
      return criteria12;
   }

   public void setCriteria12(String criteria12) {
      this.criteria12 = criteria12;
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

   public ProgressReportType getReportType() {
      return reportType;
   }

   public void setReportType(ProgressReportType reportType) {
      this.reportType = reportType;
   }

   public int getReportcard_id() {
      return reportcard_id;
   }

   public void setReportcard_id(int reportcard_id) {
      this.reportcard_id = reportcard_id;
   }

   public String getSection1_observation() {
      return section1_observation;
   }

   public void setSection1_observation(String section1_observation) {
      this.section1_observation = section1_observation;
   }

   public String getSection1_recommendation() {
      return section1_recommendation;
   }

   public void setSection1_recommendation(String section1_recommendation) {
      this.section1_recommendation = section1_recommendation;
   }

   public String getSection2_observation() {
      return section2_observation;
   }

   public void setSection2_observation(String section2_observation) {
      this.section2_observation = section2_observation;
   }

   public String getSection2_recommendation() {
      return section2_recommendation;
   }

   public void setSection2_recommendation(String section2_recommendation) {
      this.section2_recommendation = section2_recommendation;
   }

   public String getSection3_observation() {
      return section3_observation;
   }

   public void setSection3_observation(String section3_observation) {
      this.section3_observation = section3_observation;
   }

   public String getSection3_recommendation() {
      return section3_recommendation;
   }

   public void setSection3_recommendation(String section3_recommendation) {
      this.section3_recommendation = section3_recommendation;
   }

   public Skill getSkills() {
      return skills;
   }

   public void setSkills(Skill skills) {
      this.skills = skills;
   }
    
    
 }
