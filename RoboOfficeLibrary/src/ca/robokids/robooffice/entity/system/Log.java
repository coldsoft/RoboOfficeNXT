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
   private Operation operation;
   private Timestamp timeCreated;
   private int affectingPerson;
   private int performingUser;
   private String detail;
   private boolean affectingStudent;
   private String affecting_firstName;
   private String affecting_lastName;
   private String performing_firstName;
   private String performing_lastName;

   public String getAffecting_firstName() {
      return affecting_firstName;
   }

   public void setAffecting_firstName(String affecting_firstName) {
      this.affecting_firstName = affecting_firstName;
   }

   public String getAffecting_lastName() {
      return affecting_lastName;
   }

   public void setAffecting_lastName(String affecting_lastName) {
      this.affecting_lastName = affecting_lastName;
   }

   public String getPerforming_firstName() {
      return performing_firstName;
   }

   public void setPerforming_firstName(String performing_firstName) {
      this.performing_firstName = performing_firstName;
   }

   public String getPerforming_lastName() {
      return performing_lastName;
   }

   public void setPerforming_lastName(String performing_lastName) {
      this.performing_lastName = performing_lastName;
   }

   
   
   public boolean isAffectingStudent() {
      return affectingStudent;
   }

   public void setAffectingStudent(boolean affectingStudent) {
      this.affectingStudent = affectingStudent;
   }

   public String getDetail() {
      return detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }
   
   
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

   public Operation getOperation() {
      return operation;
   }

   public void setOperation(Operation operation) {
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
