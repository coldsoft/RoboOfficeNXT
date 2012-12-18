
package ca.robokids.robooffice.entity.user;


public class Action {


    private int action_id;

    private String actionName;

    private boolean deleted;

   public String getActionName() {
      return actionName;
   }

   public void setActionName(String actionName) {
      this.actionName = actionName;
   }

   public int getAction_id() {
      return action_id;
   }

   public void setAction_id(int action_id) {
      this.action_id = action_id;
   }

   public boolean isDeleted() {
      return deleted;
   }

   public void setDeleted(boolean deleted) {
      this.deleted = deleted;
   }
    
    
 }
