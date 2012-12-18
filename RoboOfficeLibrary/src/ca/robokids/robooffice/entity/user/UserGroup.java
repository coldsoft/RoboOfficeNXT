
package ca.robokids.robooffice.entity.user;

import java.util.List;


public class UserGroup {


    private int group_id;
    private String groupName;
    private boolean deleted;

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }

   public String getGroupName() {
      return groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public int getGroup_id() {
      return group_id;
   }

   public void setGroup_id(int group_id) {
      this.group_id = group_id;
   }
    
    
 }
