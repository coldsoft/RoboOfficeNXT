/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.schoolmetadata.SchoolDBM;
import ca.robokids.robooffice.entity.schoolmetadata.ProgressReportType;
import ca.robokids.robooffice.entity.schoolmetadata.ReportCardCommentTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class testDB {


   public static void main(String arg[]) {
     
      //createProgressReportDBM();
      //modifyProgressReportDBM();
      //deleteProgressReportDBM();
      getAllProgressReport();
      
   }
   
   private static void createProgressReportDBM()
   {
      ProgressReportType t = new ProgressReportType();
      t.setMaxScore(4);
      t.setName("Robotic progress report(5~12)");
      t.setCriteria1("Effective");
      t.setCriteria2(("dafda"));
      t.setCriteria4("sfadfa");
      t.setCriteria3("fsafasdfa");
      t.setCriteria6("sdfada");
      t.setCriteria5("sdfsadfafdsfdsfdsf");
      t.setCriteria7("jljlkj");
      t.setCriteria8("werwpoirpoew");
      t.setCriteria9("wqeqweqw");
      t.setCriteria10("fdpoipfdsa");
      t.setCriteria11("fsdfjlkajl");
      t.setCriteria12("sffdsaljlk");
      t.setSection1("Building");
      t.setSection2("Programming");
      t.setSection3("Presentation");
      t.setSection4("Behavior");
      t.setDeleted(false);
      ReportCardCommentTemplate reportcard = new ReportCardCommentTemplate();
      List<String> sentences = new ArrayList();
      sentences.add("This is a comment");
      sentences.add("This is a second comment");
      sentences.add("This is a third comment");
      reportcard.setSection1Observation(sentences);
      reportcard.setSection2Observation(sentences);
      reportcard.setSection3Observation(sentences);
      reportcard.setSection1Recommendation(sentences);
      reportcard.setSection2Recommendation(sentences);
      reportcard.setSection3Recommendation(sentences);
      
      t.setReportCardCommentTemplate(reportcard);
      try {
         SchoolDBM.createProgressReportType(t);
      } catch (DatabaseException ex) {
         Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private static void deleteProgressReportDBM() {
      try {
         SchoolDBM.deleteProgressReportType(2);
      } catch (DatabaseException ex) {
         Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
      }
      
   }

   private static void modifyProgressReportDBM() {
      try {
         ProgressReportType t = new ProgressReportType();
      t.setMaxScore(4);
      t.setReport_type_id(2);
      t.setName("Robotic progress name report(5~12)");
      t.setCriteria1("1Effesdfsctive");
      t.setCriteria2(("2dafsfdssda"));
      t.setCriteria4("s2fadfa");
      t.setCriteria3("fs2afasdfa");
      t.setCriteria6("sd2fada");
      t.setCriteria5("ssf2dsf");
      t.setCriteria7("jlj2lkj");
      t.setCriteria8("wer2wpoirpoew");
      t.setCriteria9("wqe2qweqw");
      t.setCriteria10("fs22a");
      t.setCriteria11("fs2jl");
      t.setCriteria12("sf2ljlk");
      t.setSection1("Buil");
      t.setSection2("Program");
      t.setSection3("Present");
      t.setSection4("Behavior");
      t.setDeleted(false);
      ReportCardCommentTemplate reportcard = new ReportCardCommentTemplate();
      List<String> sentences = new ArrayList();
      sentences.add("This is aafdaf comment");
      sentences.add("This is a dafadsfsecond comment");
      sentences.add("This is a safsdathird comment");
      reportcard.setSection1Observation(sentences);
      reportcard.setSection2Observation(sentences);
      reportcard.setSection3Observation(sentences);
      reportcard.setSection1Recommendation(sentences);
      reportcard.setSection2Recommendation(sentences);
      reportcard.setSection3Recommendation(sentences);
      t.setReportCardCommentTemplate(reportcard);
      
      SchoolDBM.modifyProgressReportType(t);
      } catch (DatabaseException ex) {
         Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private static void getAllProgressReport() {
      try {
         List<ProgressReportType> types = SchoolDBM.getAllProgressReportType();
         System.out.println(types.size());
      } catch (DatabaseException ex) {
         Logger.getLogger(testDB.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
