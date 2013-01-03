/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.finance;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.db.usermanagement.UserDBM;
import ca.robokids.robooffice.entity.schoolmetadata.Fee;
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
public class FinanceDBM {
   
   public static int createFee(Fee fee) throws DatabaseException
   {
      PreparedStatement stmt = insertFee(fee);
      return  DatabaseManager.executeGetPK(stmt);      
   }
   public static int modifyFee(Fee fee) throws DatabaseException
   {
      PreparedStatement stmt = updateFee(fee);
      return DatabaseManager.executeUpdate(stmt);
   }
   
   public static boolean deleteFee(Fee fee) throws DatabaseException 
   {
      try {
         String query = "UPDATE misc_fee SET deleted = 1 WHERE fee_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         stmt.setInt(1,fee.getFee_id());
         int rowChanged = DatabaseManager.executeUpdate(stmt);
         
         if (rowChanged < 1) {
            return false;
         }
         
         return true;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   
   public static List<Fee> getAllFees() throws DatabaseException
   {
      try {
         List<Fee> fees = new ArrayList();
         
         String query = "SELECT * from misc_fee WHERE deleted = 0";

         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
            CachedRowSet crs = DatabaseManager.executeQuery(stmt);
            while (crs.next()) {
               fees.add(getFeeByID(crs.getInt("fee_id")));
            }
            return fees;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static Fee getFeeByID(int feeID) throws DatabaseException
   {
      Fee fee = null;
      try {
         String query = "SELECT * from misc_fee WHERE fee_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         
         stmt.setInt(1,feeID);
         
         CachedRowSet rs = DatabaseManager.executeQuery(stmt);
         if(rs.next())
         {
            fee = new Fee();
            fee.setFee_id(feeID);
            fee.setName(rs.getString("name"));
            fee.setDescription(rs.getString("description"));
            fee.setDate_created(rs.getDate("date_created"));
            fee.setRate(rs.getFloat("rate"));
            fee.setTax(rs.getBoolean("isTax"));
            fee.setCreated_by(UserDBM.getUserByID(rs.getInt("created_by")));
            
         }
         return fee;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
   }
   public static float getTax() throws DatabaseException
   {
      try {
         String query = "SELECT * FROM tax";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(query);
         CachedRowSet rs = DatabaseManager.executeQuery(stmt);
         
         if (rs.next())
            return rs.getFloat("tax_percentage");
         else
            return 12.0f;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   public static void setTax(float tax) throws DatabaseException
   {
      try {
         String insert = "UPDATE tax SET tax_percentage = ? WHERE tax_id = 1";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         stmt.setFloat(1, tax);
         
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   private static PreparedStatement insertFee(Fee fee) throws DatabaseException {
      try {
         String insert = "INSERT INTO misc_fee (name,description,date_created,rate,isTax,created_by)"
            + "VALUES(?,?,?,?,?,?)";
         
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(insert);
         stmt.setString(1,fee.getName());
         stmt.setString(2,fee.getDescription());
         stmt.setDate(3,fee.getDate_created());
         stmt.setFloat(4,fee.getRate());
         stmt.setBoolean(5, fee.isTax());
         stmt.setInt(6,fee.getCreated_by().getUser_id());
         return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }

   private static PreparedStatement updateFee(Fee fee) throws DatabaseException {
      try {
         String update = "UPDATE misc_fee SET name = ?, description = ?, date_created = ?, rate = ?, isTax = ?, created_by = ?"
            + " WHERE fee_id = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setString(1,fee.getName());
            stmt.setString(2,fee.getDescription());
            stmt.setDate(3,fee.getDate_created());
            stmt.setFloat(4,fee.getRate());
            stmt.setBoolean(5, fee.isTax());
            stmt.setInt(6,fee.getCreated_by().getUser_id());
            stmt.setInt(7,fee.getFee_id());
            return stmt;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static void main(String args[]) throws DatabaseException
   {
      System.out.println(getTax());
      setTax(7.5f);
      System.out.println(getTax());
   }
}
