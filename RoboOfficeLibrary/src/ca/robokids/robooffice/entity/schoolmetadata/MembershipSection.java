
package ca.robokids.robooffice.entity.schoolmetadata;


public class MembershipSection {


    private int section_id;


    private Membership membership;

    private Timeslot timeslot;
    private boolean deleted;

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

    
   public Membership getMembership() {
      return membership;
   }

   public void setMembership(Membership membership) {
      this.membership = membership;
   }

   public int getSection_id() {
      return section_id;
   }

   public void setSection_id(int section_id) {
      this.section_id = section_id;
   }

   public Timeslot getTimeslot() {
      return timeslot;
   }

   public void setTimeslot(Timeslot timeslot) {
      this.timeslot = timeslot;
   }
    
 }
