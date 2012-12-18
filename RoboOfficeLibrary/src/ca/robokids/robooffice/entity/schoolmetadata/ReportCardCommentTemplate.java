
package ca.robokids.robooffice.entity.schoolmetadata;

import java.util.List;


public class ReportCardCommentTemplate {


    private int comment_template_id;

    private int report_type_id;

    private List<String> section1Observation;
    private List<String> section2Observation;
    private List<String> section3Observation;
    private List<String> section1Recommendation;
    private List<String> section2Recommendation;
    private List<String> section3Recommendation;

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

   public List<String> getSection1Observation() {
      return section1Observation;
   }

   public void setSection1Observation(List<String> section1Observation) {
      this.section1Observation = section1Observation;
   }

   public List<String> getSection1Recommendation() {
      return section1Recommendation;
   }

   public void setSection1Recommendation(List<String> section1Recommendation) {
      this.section1Recommendation = section1Recommendation;
   }

   public List<String> getSection2Observation() {
      return section2Observation;
   }

   public void setSection2Observation(List<String> section2Observation) {
      this.section2Observation = section2Observation;
   }

   public List<String> getSection2Recommendation() {
      return section2Recommendation;
   }

   public void setSection2Recommendation(List<String> section2Recommendation) {
      this.section2Recommendation = section2Recommendation;
   }

   public List<String> getSection3Observation() {
      return section3Observation;
   }

   public void setSection3Observation(List<String> section3Observation) {
      this.section3Observation = section3Observation;
   }

   public List<String> getSection3Recommendation() {
      return section3Recommendation;
   }

   public void setSection3Recommendation(List<String> section3Recommendation) {
      this.section3Recommendation = section3Recommendation;
   }
    
    
 }
