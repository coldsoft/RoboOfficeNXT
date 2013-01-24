/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.test;

import ca.robokids.robooffice.entity.schoolmetadata.*;
import ca.robokids.robooffice.entity.student.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Coldsoft
 */
public class SampleEntities {
   
   public static List<Classroom> getClassrooms() {
      Random random = new Random();
      List<Classroom> list = new ArrayList();
      for (int i = 0; i < (random.nextInt(10)); i++) {
         list.add(getSampleClassroom());
      }
      return list;
   }
   public static Classroom getSampleClassroom()
   {
      Random random = new Random();
      Classroom cr = new Classroom();
      cr.setCapacity(3+ random.nextInt(10));
      cr.setDeleted(false);
      cr.setName(getRandomClassroomName());
      return cr;
   }
   
   public static Progress getSampleProgress()
   {
      Random random = new Random();

      if (random.nextBoolean())
      {
         CourseProgress cp = new CourseProgress();
         cp.setProgress(random.nextInt(12));
         cp.setTotalClass(12);
         cp.setStudent(getSampleStudent());
         
         Course course = new Course();
         course.setCode("LK1A");
         course.setName("Robokids 1");
         CourseSection cs = new CourseSection();
         cs.setCourse(course);
         
         cp.setSection(cs);
         cp.setTotalClass(5 + random.nextInt(8));
         return cp;
      }else
      {
         MembershipProgress p = new MembershipProgress();
         p.setStudent(getSampleStudent());
         
         Membership m = new Membership();
         m.setName("RoboClub 1");
         p.setMembership(m);
         return p;
      }

   }
   public static List<Progress> getProgresses() {
      Random random = new Random();
      List<Progress> list = new ArrayList();
      for (int i = 0; i < (3+random.nextInt(9)); i++) {
         list.add(getSampleProgress());
      }
      return list;
   }
   public static List<ClassRecord> getClassRecordList()
   {
      Random random = new Random();
      List<ClassRecord> list = new ArrayList();
      for (int i = 0; i < random.nextInt(12); i++)
         list.add(getSampleClassRecord());
      return list;
   }
   public static ClassRecord getSampleClassRecord() {
      
      Random random = new Random();
      
      ClassRecord cr = new ClassRecord();
      cr.setAttendDate(new Date(System.currentTimeMillis()));
      
      Timeslot time = new Timeslot();
      time.setDayOfWeek(DayOfWeek.Wed);
      time.setStart((new Time(System.currentTimeMillis())));
      
      CourseSection section = new CourseSection();
      section.setTimeslot(time);
      cr.setSection(section);
      cr.setComplete(random.nextBoolean());
      
      if (cr.isComplete()){
      Project p = new Project();
      p.setName(getRandomProjectName());
      cr.setProject(p);
      }
      cr.setTest(random.nextBoolean());
      cr.setMakeupClass(random.nextBoolean());
      cr.setOverdue(random.nextBoolean());
      return cr;
   }
   
   public static List<Student> getSampleStudentList() {
      List<Student> list = new ArrayList();
      Random random = new Random();
      int size = random.nextInt(200);
      for (int i = 0; i < size; i++) {
         Student s = new Student();
         s.setFirstName(getRandomFirstName());
         s.setLastName(getRandomLastName());
         list.add(s);
      }
      return list;
   }
   
   public static Student getSampleStudent()
   {
      Student s = new Student();
         s.setFirstName(getRandomFirstName());
         s.setLastName(getRandomLastName());
         return s;
   }
   
   public static String getRandomFirstName() {
      Random random = new Random();
      String names[] = {"Amy", "James", "John", "Brian", "Dexter", "Julia", "Brat", "Sean", "Joe", "Dylan", "Shawn", "Stewart"};
      return names[random.nextInt(names.length)];
   }

   public static String getRandomLastName() {
      Random random = new Random();
      String names[] = {"Smith", "Li", "Zhang", "Qian", "Cheng", "Wang", "Lee", "Zhao", "Sun", "Zhou", "Wu", "Hua"};
      return names[random.nextInt(names.length)];
   }
   public static String getRandomProjectName()
   {
       Random random = new Random();
      String names[] = {"Explorer", "MouseTrap", "Dancing birds", "Hungry Alligator", "CSI", "Richmond City", "Halo 2", "FLL training", "Sumobot", "Pushing white block", "Seasaw", "Powersaw"};
      return names[random.nextInt(names.length)];
   }

   public static String getRandomClassroomName() {
      Random random = new Random();
      String names[] = {"Matrix", "Bourne safehouse", "Hogwarts", "Skyfall", "Hobbit", "Waterloo", "Robokids room"};
      return names[random.nextInt(names.length)];
   }

   public static Timeslot getRandomTimeslot() {
      Timeslot t = new Timeslot();
      Random random = new Random();
      
      t.setDayOfWeek(DayOfWeek.values()[random.nextInt(7)]);
      t.setStart(new Time(random.nextInt(24),random.nextInt(60),random.nextInt(60)));
      return t;
   }
   
}
