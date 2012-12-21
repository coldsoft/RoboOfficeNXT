/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.entity.system;

import java.sql.Timestamp;

/**
 *
 * @author Coldsoft
 */
public class Log {
   
   private int log_id;
   private String operation;
   private Timestamp timeCreated;
   private int affectingPerson;
   private int performingUser;

   public int getAffectingPerson() {
      return affectingPerson;
   }

   public void setAffectingPerson(int affectingPerson) {
      this.affectingPerson = affectingPerson;
   }

   public int getLog_id() {
      return log_id;
   }

   public void setLog_id(int log_id) {
      this.log_id = log_id;
   }

   public String getOperation() {
      return operation;
   }

   public void setOperation(String operation) {
      this.operation = operation;
   }

   public int getPerformingUser() {
      return performingUser;
   }

   public void setPerformingUser(int performingUser) {
      this.performingUser = performingUser;
   }

   public Timestamp getTimeCreated() {
      return timeCreated;
   }

   public void setTimeCreated(Timestamp timeCreated) {
      this.timeCreated = timeCreated;
   }
   
   
   
}
