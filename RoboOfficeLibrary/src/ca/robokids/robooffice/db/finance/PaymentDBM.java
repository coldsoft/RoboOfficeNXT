/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.finance;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.finance.PaymentHistory;
import ca.robokids.robooffice.entity.finance.PaymentMethod;
import ca.robokids.robooffice.entity.finance.PaymentRecord;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class PaymentDBM {

   private static List<PaymentMethod> paymentMethods;
   public static List<PaymentMethod> getAllPaymentMethods() throws DatabaseException
   {
      try {
         if (paymentMethods != null) {
            return paymentMethods;
         }
         
         paymentMethods = new ArrayList();
         String select = "SELECT * FROM payment_method WHERE payment_method_id > -1";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while(crs.next())
         {
            PaymentMethod m = new PaymentMethod();
            m.setPayment_method_id(crs.getInt("payment_method_id"));
            m.setMethodName(crs.getString("method_name"));
            
            paymentMethods.add(m);
         }
         return paymentMethods;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static int createPaymentRecord(PaymentRecord pr) throws DatabaseException {
      PreparedStatement stmt = insertPaymentRecord(pr);
      return DatabaseManager.executeGetPK(stmt);
   }

   public static void modifyPaymentRecord(PaymentRecord pr) throws DatabaseException {
      PreparedStatement stmt = updatePaymentRecord(pr);
      DatabaseManager.executeUpdate(stmt);
   }

   public static void deletePaymentRecord(int record_id) throws DatabaseException {
      try {
         String delete = "DELETE FROM payment_record WHERE payment_record_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(delete);

         stmt.setInt(1, record_id);

         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }
   
   public static PaymentRecord getPaymentRecordByID(int record_id) throws DatabaseException {
      try {
         String select = "SELECT * FROM payment_record WHERE payment_record_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);

         stmt.setInt(1, record_id);

         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         while (crs.next()) {

            PaymentRecord pr = new PaymentRecord();
            pr.setPayment_record_id(record_id);
            pr.setPayment_history_id(crs.getInt("payment_history_id"));
            pr.setFeeName(crs.getString("fee_name"));
            pr.setFeeDescription(crs.getString("fee_description"));
            pr.setRate(crs.getFloat("rate"));
            pr.setMultiplier(crs.getInt("multiplier"));
            pr.setAvailableCredit(crs.getFloat("available_credit"));
            pr.setDiscountClass(crs.getInt("discount_class"));
            pr.setDiscountAmount(crs.getFloat("discount_amount"));
            pr.setDiscountReason(crs.getString("discount_reason"));
            pr.setTax(crs.getFloat("tax"));
            pr.setTotal(crs.getFloat("total"));
            pr.setStartDate(crs.getDate("start_date"));
            pr.setExpireDate(crs.getDate("expire_date"));
            return pr;

         }
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      return null;
   }

   public static int createPaymentHistory(PaymentHistory ph) throws DatabaseException
   {
      PreparedStatement stmt = insertPaymentHistory(ph);
      return DatabaseManager.executeGetPK(stmt);
      
   }
   public static void modifyPaymentHistory(PaymentHistory ph) throws DatabaseException
   {
      PreparedStatement stmt = updatePaymentHistory(ph);
      DatabaseManager.executeUpdate(stmt);
   }
   private static PreparedStatement insertPaymentRecord(PaymentRecord pr) throws DatabaseException {
      try {
         String insert = "INSERT INTO payment_record (payment_history_id, fee_name, fee_description, rate, multiplier, "
            + "available_credit, discount_class, "
            + "discount_amount, discount_reason, tax, total, start_date, expire_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);

         stmt.setInt(1, pr.getPayment_history_id());
         stmt.setString(2, pr.getFeeName());
         stmt.setString(3, pr.getFeeDescription());
         stmt.setFloat(4, pr.getRate());
         stmt.setInt(5, pr.getMultiplier());
         stmt.setFloat(6, pr.getAvailableCredit());
         stmt.setInt(7, pr.getDiscountClass());
         stmt.setFloat(8, pr.getDiscountAmount());
         stmt.setString(9, pr.getDiscountReason());
         stmt.setFloat(10, pr.getTax());
         stmt.setFloat(11, pr.getTotal());
         stmt.setDate(12, pr.getStartDate());
         stmt.setDate(13, pr.getExpireDate());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }

   }

   private static PreparedStatement updatePaymentRecord(PaymentRecord pr) throws DatabaseException {
      try {
         String update = "UPDATE payment_record SET payment_record = ?,  payment_history_id = ? , fee_name = ?, fee_description = ?, rate = ?, multiplier = ?, "
            + "available_credit = ?, discount_class = ?, "
            + "discount_amount = ?, discount_reason = ?, tax = ?, total = ?, start_date = ?, expire_date = ? WHERE payment_record_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);

         stmt.setInt(1, pr.getPayment_history_id());
         stmt.setString(2, pr.getFeeName());
         stmt.setString(3, pr.getFeeDescription());
         stmt.setFloat(4, pr.getRate());
         stmt.setInt(5, pr.getMultiplier());
         stmt.setFloat(6, pr.getAvailableCredit());
         stmt.setInt(7, pr.getDiscountClass());
         stmt.setFloat(8, pr.getDiscountAmount());
         stmt.setString(9, pr.getDiscountReason());
         stmt.setFloat(10, pr.getTax());
         stmt.setFloat(11, pr.getTotal());
         stmt.setDate(12, pr.getStartDate());
         stmt.setDate(13, pr.getExpireDate());
         stmt.setInt(14, pr.getPayment_record_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement insertPaymentHistory(PaymentHistory ph) throws DatabaseException {
      try {
         String insert = "INSERT INTO payment_history (student_id, payment_method_id, date_paid, total_amount, notes, invoice, received_by) VALUES (?,?,?,?,?,?,?)";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         
         stmt.setInt(1,ph.getStudent().getStudent_id());
         stmt.setInt(2,ph.getPaymentMethod().getPayment_method_id());
         stmt.setDate(3,ph.getDatePaid());
         stmt.setFloat(4,ph.getTotalAmount());
         stmt.setString(5,ph.getNotes());
         stmt.setString(6,ph.getInvoice());
         stmt.setInt(7,ph.getReceivedBy());
         
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement updatePaymentHistory(PaymentHistory ph) throws DatabaseException {
      try {
         String update = "UPDATE payment_history SET student_id = ? , payment_method_id = ?, date_paid = ?, "
            + "total_amount = ?, notes = ?,  invoice = ?, received_by = ? WHERE payment_history_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
            
            stmt.setInt(1,ph.getStudent().getStudent_id());
            stmt.setInt(2,ph.getPaymentMethod().getPayment_method_id());
            stmt.setDate(3,ph.getDatePaid());
            stmt.setFloat(4,ph.getTotalAmount());
            stmt.setString(5,ph.getNotes());
            stmt.setString(6,ph.getInvoice());
            stmt.setInt(7,ph.getReceivedBy());
            stmt.setInt(8,ph.getPayment_history_id());
            
            return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
         
   }
}
