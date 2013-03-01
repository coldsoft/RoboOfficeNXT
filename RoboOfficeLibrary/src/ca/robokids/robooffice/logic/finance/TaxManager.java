/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.finance;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.finance.TaxDBM;
import ca.robokids.robooffice.entity.finance.Tax;
import ca.robokids.robooffice.entity.finance.Tax.TaxKind;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class TaxManager {
   private static Tax HST;
   private static Tax PST;
   private static Tax GST;
   private static Tax NoTax;
   private static Tax GST_PST;
   
   public static Tax getHST() throws DatabaseException
   {
      return TaxDBM.getTax(TaxKind.HST.toString());
   }
   public static Tax getGST() throws DatabaseException
   {
      return TaxDBM.getTax(TaxKind.GST.toString());
   }
   public static Tax getPST() throws DatabaseException
   {
      return TaxDBM.getTax(TaxKind.PST.toString());
   }
   public static Tax getCurrentTax() throws DatabaseException
   {
      Tax hst = getHST();
      if (hst.isCurrent())
         return hst;
      return getGST();
   }
   public static Tax getNoTax() {
      Tax tax = new Tax();
      tax.setCurrent(false);
      tax.setKind(Tax.TaxKind.NO_TAX);
      tax.setTaxPercentage(0f);
      
      return tax;
   }
   
   public static Tax getGSTPST() throws DatabaseException{
      Tax tax = new Tax();
      tax.setCurrent(!getHST().isCurrent());
      tax.setKind(Tax.TaxKind.GST_PST);
      tax.setTaxPercentage(getGST().getTaxPercentage() + getPST().getTaxPercentage());
      
      return tax;
   }
   public static List<Tax> loadAllTax() throws DatabaseException
   {
      List<Tax> all = new ArrayList();
      Tax tax = getCurrentTax();
      if (tax.getKind() != TaxKind.HST)
      {
         all.add(tax);
         all.add(getNoTax());
         all.add(getPST());
         all.add(getGSTPST());
      }else
      {
         all.add(getHST());
         all.add(getNoTax());
      }
      return all;   
   }
   public static void setCurrentHST(boolean b) throws DatabaseException
   {
      Tax hst = getHST();
      hst.setCurrent(b);
      TaxDBM.setTax(hst);
   }
   public static void setTax(Tax tax) throws DatabaseException {
      TaxDBM.setTax(tax);
   }
   
   
   
   
}
