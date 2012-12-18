/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.exception;

/**
 * Bad Field Exceptions are thrown whenever a field in a resource, patron,
 * or subscription does not match the values that the database will accept
 * or meet the standard for formatting
 * @date 14.11.2012
 * @author Donghoon Oh
 */
public class BadFieldException extends RoboOfficeException

{
    public BadFieldException()
    {
        //default constructor
    }
    public BadFieldException(String message)
    {
        super(message);
    }
}
