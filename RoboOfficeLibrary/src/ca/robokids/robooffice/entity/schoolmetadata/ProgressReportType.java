
package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.List;


public class ProgressReportType {


    private int report_type_id;
    private int maxScore;
    private String section1;
    private String criteria1;
    private String criteria2;
    private String criteria3;
    private String section2;
    private String criteria4;
    private String criteria5;
    private String criteria6;
    private String section3;
    private String criteria7;
    private String criteria8;
    private String criteria9;
    private String section4;
    private String criteria10;
    private String criteria11;
    private String criteria12;
    private List<ReportCardCommentTemplate> reportCardCommentTemplate;

   public List<ReportCardCommentTemplate> getReportCardCommentTemplate() {
      return reportCardCommentTemplate;
   }

   public void setReportCardCommentTemplate(List<ReportCardCommentTemplate> reportCardCommentTemplate) {
      this.reportCardCommentTemplate = reportCardCommentTemplate;
   }

   public String getCriteria1() {
      return criteria1;
   }

   public void setCriteria1(String criteria1) {
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

   public String getCriteria2() {
      return criteria2;
   }

   public void setCriteria2(String criteria2) {
      this.criteria2 = criteria2;
   }

   public String getCriteria3() {
      return criteria3;
   }

   public void setCriteria3(String criteria3) {
      this.criteria3 = criteria3;
   }

   public String getCriteria4() {
      return criteria4;
   }

   public void setCriteria4(String criteria4) {
      this.criteria4 = criteria4;
   }

   public String getCriteria5() {
      return criteria5;
   }

   public void setCriteria5(String criteria5) {
      this.criteria5 = criteria5;
   }

   public String getCriteria6() {
      return criteria6;
   }

   public void setCriteria6(String criteria6) {
      this.criteria6 = criteria6;
   }

   public String getCriteria7() {
      return criteria7;
   }

   public void setCriteria7(String criteria7) {
      this.criteria7 = criteria7;
   }

   public String getCriteria8() {
      return criteria8;
   }

   public void setCriteria8(String criteria8) {
      this.criteria8 = criteria8;
   }

   public String getCriteria9() {
      return criteria9;
   }

   public void setCriteria9(String criteria9) {
      this.criteria9 = criteria9;
   }

   public int getMaxScore() {
      return maxScore;
   }

   public void setMaxScore(int maxScore) {
      this.maxScore = maxScore;
   }



   public int getReport_type_id() {
      return report_type_id;
   }

   public void setReport_type_id(int report_type_id) {
      this.report_type_id = report_type_id;
   }

   public String getSection1() {
      return section1;
   }

   public void setSection1(String section1) {
      this.section1 = section1;
   }

   public String getSection2() {
      return section2;
   }

   public void setSection2(String section2) {
      this.section2 = section2;
   }

   public String getSection3() {
      return section3;
   }

   public void setSection3(String section3) {
      this.section3 = section3;
   }

   public String getSection4() {
      return section4;
   }

   public void setSection4(String section4) {
      this.section4 = section4;
   }
    
 }
