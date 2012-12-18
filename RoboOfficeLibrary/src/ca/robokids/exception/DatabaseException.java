/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.exception;

/**
 *
 * @author Coldsoft
 */
/**
 * Database exceptions are thrown for a variety of reasons concerning the database
 * the formost being incorrect query parameters, and incorrect input types.
 * SLQ errors are also rethrown as database errors in many cases
 * @date 16.11.2012
 * @author Shawn
 */
public class DatabaseException extends RoboOfficeException
{
    public DatabaseException()
    {
    }
    public DatabaseException(String message)
    {
        super(message);
    }
}
