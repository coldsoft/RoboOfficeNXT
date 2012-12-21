/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.exception.userexception;

import ca.robokids.exception.RoboOfficeException;

/**
 *
 * @author Coldsoft
 */
public class BadUsernameException extends RoboOfficeException{


   public BadUsernameException(String message) {
      super(message);
   }

   public BadUsernameException() {

   }
   
   
   
}
