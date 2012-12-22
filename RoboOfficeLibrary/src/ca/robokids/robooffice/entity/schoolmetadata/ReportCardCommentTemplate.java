
package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.List;


public class ReportCardCommentTemplate {


    private int comment_template_id;

    private int report_type_id;

    private String section1Observation;
    private String section2Observation;
    private String section3Observation;
    private String section1Recommendation;
    private String section2Recommendation;
    private String section3Recommendation;

   public int getComment_template_id() {
      return comment_template_id;
   }

   public void setComment_template_id(int comment_template_id) {
      this.comment_template_id = comment_template_id;
   }

   public int getReport_type_id() {
      return report_type_id;
   }

   public void setReport_type_id(int report_type_id) {
      this.report_type_id = report_type_id;
   }

   public String getSection1Observation() {
      return section1Observation;
   }

   public void setSection1Observation(String section1Observation) {
      this.section1Observation = section1Observation;
   }

   public String getSection1Recommendation() {
      return section1Recommendation;
   }

   public void setSection1Recommendation(String section1Recommendation) {
      this.section1Recommendation = section1Recommendation;
   }

   public String getSection2Observation() {
      return section2Observation;
   }

   public void setSection2Observation(String section2Observation) {
      this.section2Observation = section2Observation;
   }

   public String getSection2Recommendation() {
      return section2Recommendation;
   }

   public void setSection2Recommendation(String section2Recommendation) {
      this.section2Recommendation = section2Recommendation;
   }

   public String getSection3Observation() {
      return section3Observation;
   }

   public void setSection3Observation(String section3Observation) {
      this.section3Observation = section3Observation;
   }

   public String getSection3Recommendation() {
      return section3Recommendation;
   }

   public void setSection3Recommendation(String section3Recommendation) {
      this.section3Recommendation = section3Recommendation;
   }
}