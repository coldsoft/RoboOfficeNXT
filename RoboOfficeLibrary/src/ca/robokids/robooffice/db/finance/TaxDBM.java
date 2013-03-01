/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.db.finance;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.DatabaseManager;
import ca.robokids.robooffice.entity.finance.Tax;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Coldsoft
 */
public class TaxDBM {
   
   public static Tax getTax(String taxName) throws DatabaseException
   {
      try {
         List<Tax> all = new ArrayList();
         String select = "SELECT * FROM tax WHERE tax_name = ?";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(select);
         stmt.setString(1,taxName);
         CachedRowSet crs = DatabaseManager.executeQuery(stmt);
         
         while(crs.next())
         {
            Tax tax = new Tax();
            tax.setTax_id(crs.getInt("tax_id"));
            tax.setCurrent(crs.getBoolean("current"));
            tax.setTaxPercentage(crs.getFloat("tax_percentage"));
            tax.setKind(Tax.TaxKind.valueOf(crs.getString("tax_name")));
            
            return tax;
         }
         return null;
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
   }
   
   public static void setTax(Tax t) throws DatabaseException
   {
      try {
         String update = "UPDATE tax SET tax_percentage = ?, tax_name = ?, current = ? WHERE tax_id = ? ";
         PreparedStatement stmt = DatabaseManager.getPreparedStatement(update);
         stmt.setFloat(1,t.getTaxPercentage());
         stmt.setString(2, t.getKind().toString());
         stmt.setBoolean(3,t.isCurrent());
         stmt.setInt(4,t.getTax_id());
         
         DatabaseManager.executeUpdate(stmt);
      } catch (SQLException ex) {
         throw new DatabaseException(ex.getMessage());
      }
      
      
   }
}
