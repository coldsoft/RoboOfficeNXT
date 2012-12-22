
package ca.robokids.robooffice.entity.schoolmetadata;


public class Location {

    private int location_id;

    private String name;

    private String address;

    
   public String getAddress() {
      return address;
   }

   public void setAddress(String addresss) {
      this.address = addresss;
   }

   public int getLocation_id() {
      return location_id;
   }

   public void setLocation_id(int location_id) {
      this.location_id = location_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public String toString()
   {
      return getName() + " at " + getAddress();
   }
    
 }
