/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.finance;

import ca.robokids.exception.BadFieldException;
import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.CheckFields;
import ca.robokids.robooffice.db.finance.FinanceDBM;
import ca.robokids.robooffice.entity.finance.Fee;
import ca.robokids.robooffice.entity.system.Operation;
import ca.robokids.robooffice.logic.system.SystemLog;
import ca.robokids.robooffice.logic.usermanagement.UserActivity;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coldsoft
 */
public class FinanceSetting {
   
   public static void createFee(Fee fee) throws DatabaseException, BadFieldException
   {
      CheckFields.checkFee(fee);
      fee.setCreated_by(UserActivity.loginUser);
      fee.setDate_created(new Date(System.currentTimeMillis()));
      FinanceDBM.createFee(fee);
      
      //Event Logging
      String details = "created new fee: " + fee.getName() + " rate: " + fee.getRateString();
      SystemLog.createEventLog(Operation.FINANCE_SETTING, details);
   }
   public static void modifyFee(Fee fee) throws BadFieldException, DatabaseException
   {
      CheckFields.checkFee(fee);
      fee.setCreated_by(UserActivity.loginUser);
      fee.setDate_created(new Date(System.currentTimeMillis()));
      FinanceDBM.modifyFee(fee);
       //Event Logging
      String details = "modifyed fee: " + fee.getName() + " rate: " + fee.getRateString();
      SystemLog.createEventLog(Operation.FINANCE_SETTING, details);
   }
   
   public static void deleteFee(Fee fee) throws DatabaseException
   {
      FinanceDBM.deleteFee(fee);
      
       //Event Logging
      String details = " deleted fee: " + fee.getName()  + " rate: " + fee.getRateString();
      SystemLog.createEventLog(Operation.FINANCE_SETTING, details);

   }
   
   public static List<Fee> loadAllFees() throws DatabaseException
   {
      return FinanceDBM.getAllFees();
   }
   public static float getTax() throws DatabaseException
   {
      return FinanceDBM.getTax();
   }
   public static void setTax(float tax) throws DatabaseException
   {
      if (tax > 100)
         return;
      if (tax < 0)
         return;
      FinanceDBM.setTax(tax);
      
      //Event Logging
      String details = " changed tax to " + tax;
      SystemLog.createEventLog(Operation.FINANCE_SETTING, details);
   }
   
}
