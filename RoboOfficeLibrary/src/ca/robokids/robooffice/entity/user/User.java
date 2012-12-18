
package ca.robokids.robooffice.entity.user;

import java.util.List;


public class User {

    private int user_id;
    private String userName;
    private String password;
    private String passwordQuestion;
    private String passwordAnswer;
    private String firstName;
    private String lastName;
    private String address;
    private String SIN;
    private String phone;
    private boolean deleted;
    private List<UserGroup> groups;
    private String email;

   public String getSIN() {
      return SIN;
   }

   public void setSIN(String SIN) {
      this.SIN = SIN;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public List<UserGroup> getGroups() {
      return groups;
   }

   public void setGroups(List<UserGroup> groups) {
      this.groups = groups;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPasswordAnswer() {
      return passwordAnswer;
   }

   public void setPasswordAnswer(String passwordAnswer) {
      this.passwordAnswer = passwordAnswer;
   }

   public String getPasswordQuestion() {
      return passwordQuestion;
   }

   public void setPasswordQuestion(String passwordQuestion) {
      this.passwordQuestion = passwordQuestion;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public int getUser_id() {
      return user_id;
   }

   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }
    
    
 }