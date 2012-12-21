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
public class BadPasswordException extends RoboOfficeException{

   public BadPasswordException(String message) {
      super(message);
   }

   public BadPasswordException() {

   }
   
   
}
