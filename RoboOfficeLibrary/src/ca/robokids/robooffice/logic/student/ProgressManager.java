/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.student;

import ca.robokids.robooffice.entity.student.ClassRecord;
import java.awt.Color;

/**
 *
 * @author Coldsoft
 */
public class ProgressManager {

   private static final Color NORMAL_COLOR = Color.WHITE;
   private static final Color OVERDUE = Color.WHITE;;
   private static final Color MAKEUP_COLOR = Color.GREEN;

   public static Color getClassRecordColor(ClassRecord cr) {
      if (cr.isOverdue())
         return OVERDUE;
      if (cr.isMakeupClass())
         return MAKEUP_COLOR;
      return NORMAL_COLOR;
   }
}
