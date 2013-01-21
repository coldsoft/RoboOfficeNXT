package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.user.PasswordQuestion;
import ca.robokids.robooffice.entity.user.User;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Student {

   private int student_id;
   private String notes;

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }
   private User created_by;
   private Sex sex;
   private String firstName;
   private String lastName;
   private boolean deleted;
   private Date modifiedDate;
   private boolean active = true;
   private boolean prospective;
   private Date birthday;
   private String school;
   private String address;
   private String zipcode;
   private String city;
   private String email;
   private String mother;
   private String motherPhone;
   private String father;
   private String fatherPhone;
   private String homePhone;
   private String emergency;
   private Date joinDate;
   private String hearFrom;
   private String password;
   private String userName;
   private PasswordQuestion passwordQuestion;
   private String passwordAnswer;

   public boolean isActive() {
      return active;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getBirthdayString()
   {
       SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
      if (birthday != null)
         return format.format(birthday);
      return "";
   }
   public Date getBirthday() {
      return birthday;
   }

   public void setBirthday(Date birthday) {
      this.birthday = birthday;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getModifiedDateString() {
      SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
      if (modifiedDate != null)
         return format.format(modifiedDate);
      return "";

   }
   
   public Date getModifiedDate()
   {
         return modifiedDate;
   }

   public void setModifiedDate(Date createDate) {
      this.modifiedDate = createDate;
   }

   public User getCreated_by() {
      return created_by;
   }

   public void setCreated_by(User created_by) {
      this.created_by = created_by;
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

   public String getEmergency() {
      return emergency;
   }

   public void setEmergency(String emergency) {
      this.emergency = emergency;
   }

   public String getFather() {
      return father;
   }

   public void setFather(String father) {
      this.father = father;
   }

   public String getFatherPhone() {
      return fatherPhone;
   }

   public void setFatherPhone(String fatherPhone) {
      this.fatherPhone = fatherPhone;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getHearFrom() {
      return hearFrom;
   }

   public void setHearFrom(String hearFrom) {
      this.hearFrom = hearFrom;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public void setHomePhone(String homePhone) {
      this.homePhone = homePhone;
   }

   public Date getJoinDate() {
      return joinDate;
   }

   public String getJoinDateString(){
      if (joinDate == null) {
         return "";
      }
      
      SimpleDateFormat format;
      Date today = new Date(System.currentTimeMillis());
      if ((joinDate.getYear() == today.getYear()))
      {
         format = new SimpleDateFormat("MMM dd");
         return "this year on " + format.format(joinDate);
      }
      if ((joinDate.getYear() == (today.getYear()-1)))
      {
         format = new SimpleDateFormat("MMM dd");
         return "last year on " + format.format(joinDate);
      }else{
         format =  new SimpleDateFormat("yyyy.MMM.dd");
         return format.format(joinDate);
      }
      
   }
   public void setJoinDate(Date joinDate) {
      this.joinDate = joinDate;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getMother() {
      return mother;
   }

   public void setMother(String mother) {
      this.mother = mother;
   }

   public String getMotherPhone() {
      return motherPhone;
   }

   public void setMotherPhone(String motherPhone) {
      this.motherPhone = motherPhone;
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

   public PasswordQuestion getPasswordQuestion() {
      return passwordQuestion;
   }

   public void setPasswordQuestion(PasswordQuestion passwordQuestion) {
      this.passwordQuestion = passwordQuestion;
   }

   public boolean isProspective() {
      return prospective;
   }

   public void setProspective(boolean prospective) {
      this.prospective = prospective;
   }

   public String getSchool() {
      return school;
   }

   public void setSchool(String school) {
      this.school = school;
   }

   public Sex getSex() {
      return sex;
   }

   public void setSex(Sex sex) {
      this.sex = sex;
   }

   public int getStudent_id() {
      return student_id;
   }

   public void setStudent_id(int student_id) {
      this.student_id = student_id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getZipcode() {
      return zipcode;
   }

   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }

   public String toString() {
      return getFirstName() + " " + getLastName();
   }

   public int getAge() {
      Calendar dob = Calendar.getInstance();
      dob.setTime(birthday);
      return Student.getAge(dob.get(Calendar.YEAR), dob.get(Calendar.MONTH), dob.get(Calendar.DAY_OF_MONTH));
   }

   public static int getAge(int year, int month, int day) {
      Calendar dob = Calendar.getInstance();
      Calendar today = Calendar.getInstance();


      dob.set(year, month, day);

      int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
      if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
         age--;
      } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)) {
         if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
         }
      }

      Integer ageInt = new Integer(age);

      return ageInt;

   }
   
   public static void main(String args[])
   {
      Student student = new Student();
      student.setJoinDate(new Date(112,3,8));
      System.out.println(student.getJoinDateString());
   }
}
