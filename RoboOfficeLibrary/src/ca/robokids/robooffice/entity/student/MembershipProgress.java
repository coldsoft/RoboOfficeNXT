
package ca.robokids.robooffice.entity.student;

import ca.robokids.robooffice.entity.schoolmetadata.Membership;

/**
 * 
 * 
 * @poseidon-object-id [I4979bea0m13ba8059043mm3d24]
 */
public class MembershipProgress extends Progress{



    private Membership membership;

   public Membership getMembership() {
      return membership;
   }

   public void setMembership(Membership membership) {
      this.membership = membership;
   }
    
    
 }
