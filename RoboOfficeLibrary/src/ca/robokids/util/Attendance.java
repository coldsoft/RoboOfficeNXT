/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.util;

import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.entity.schoolmetadata.Classroom;
import ca.robokids.robooffice.entity.student.CourseProgress;
import ca.robokids.robooffice.entity.student.MembershipProgress;
import ca.robokids.robooffice.entity.student.Progress;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Coldsoft
 */
public class Attendance {

   Map<Classroom, List<Progress>> mapping = new HashMap();;
   List<Classroom> classrooms = new ArrayList();
   private String date;
   private String timeslot;
   private final String template = "/ca/robokids/util/attendance_template.xlsx";
   private final String outputFolder = "D:\\Robokids\\AttendanceTemp\\";
   private final String fileType = ".xlsx";
   
   public Attendance(String date, String timeslot) {
      this.date = date;
      this.timeslot = timeslot;
   }

   public void addClassroom(Classroom classroom, List<Progress> progresses) {
      if (!progresses.isEmpty()) {
         mapping.put(classroom, progresses);
         classrooms.add(classroom);
      }
   }

   public void clear() {
      mapping.clear();
      classrooms.clear();
   }

   public void openExcel() throws IOException {
      XSSFWorkbook wb = new XSSFWorkbook(this.getClass().getResourceAsStream(template));

      System.out.println("Generating attendance..");
      generateAttendance(wb);
      System.out.println("Finish Generating, saving excel");
      long time = System.currentTimeMillis();
      Date now = new Date(time);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");

      
      String fileName = this.date + "-" + timeslot + "-Attendance-" + sdf.format(now) + fileType;
      
      fileName = CheckFields.sanitizeFilename(fileName);
      FileOutputStream fileout;

      fileout = new FileOutputStream(outputFolder + fileName);
      wb.write(fileout);
      fileout.close();
      
      System.out.println("excel save at " +  outputFolder + fileName+ ", opening it now using system call");
      java.awt.Desktop.getDesktop().open(new File(outputFolder + fileName));




   }

   private void generateAttendance(XSSFWorkbook wb) {
      for (int i = 0; i < classrooms.size(); i++) {
        
         Classroom cr = classrooms.get(i);
         wb.setSheetName(i,(i+1) + "-" + cr.getName());
         System.out.println("filling in sheet " + i + " for classroom " + cr.getName());
         Sheet sheet = wb.getSheetAt(i);
         //Cell A1: Classroom name
         Row row = sheet.getRow(0);
         Cell cell = row.getCell(0);
         cell.setCellValue("Classroom: " + cr.toString());

         //Cell A2: attendance date
         row = sheet.getRow(1);
         cell = row.getCell(0);
         cell.setCellValue(date);

         //Cell B2: timeslot
         row = sheet.getRow(1);
         cell = row.getCell(1);
         cell.setCellValue(timeslot);

         List<Progress> students = mapping.get(cr);

         //first entry starts at cell A3 in excel sheet
         int rowCounter = 2;
         for (Progress p : students) {
            if (p instanceof CourseProgress) {
               CourseProgress cp = (CourseProgress) p;
               row = sheet.getRow(rowCounter);

               //set Course name;
               cell = row.getCell(0);
               cell.setCellValue(cp.getSection().getCourse().getName());
               //set Progress
               cell = row.getCell(2);
               cell.setCellValue(cp.getProgress() + "/" + cp.getTotalClass());
            } else if (p instanceof MembershipProgress) {
               MembershipProgress mp = (MembershipProgress) p;
               row = sheet.getRow(rowCounter);

               //set Course name;
               cell = row.getCell(0);
               cell.setCellValue(mp.getMembership().getName());
               //set Progress
               cell = row.getCell(2);
               cell.setCellValue("N/A");
            }
            //set student name
            cell = row.getCell(1);
            cell.setCellValue(p.getStudent().toString());
            rowCounter++;
         }
      }
      if (classrooms.size() > wb.getNumberOfSheets())
         return;
      
      //Remove the rest of the sheet
      int total = wb.getNumberOfSheets();
      for (int i = 0; i < (total-classrooms.size()); i++)
      {
         wb.removeSheetAt(classrooms.size());
      }
   }
}
