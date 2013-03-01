/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.logic.finance;

import ca.robokids.exception.DatabaseException;
import ca.robokids.robooffice.db.finance.PaymentDBM;
import ca.robokids.robooffice.entity.finance.PaymentMethod;
import java.util.List;

/**
 *
 * @author Coldsoft
 */
public class PaymentManager {
   
   public static List<PaymentMethod> getAllPaymentMethod() throws DatabaseException
   {
       PaymentDBM.getAllPaymentMethods();
   }
   
}
