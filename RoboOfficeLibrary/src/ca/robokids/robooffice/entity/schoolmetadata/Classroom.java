
package ca.robokids.robooffice.entity.schoolmetadata;


public class Classroom {


    private int classroom_id;


    private String name;

    private int capacity;

    private Location location;

    private boolean deleted;

   public int getCapacity() {
      return capacity;
   }

   public void setCapacity(int capacity) {
      this.capacity = capacity;
   }

   public int getClassroom_id() {
      return classroom_id;
   }

   public void setClassroom_id(int classroom_id) {
      this.classroom_id = classroom_id;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public Location getLocation() {
      return location;
   }

   public void setLocation(Location location) {
      this.location = location;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   public String toString()
   {
      return this.getName();
   }
    
 }
