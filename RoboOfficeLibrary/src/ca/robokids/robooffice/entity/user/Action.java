
package ca.robokids.robooffice.entity.user;


public class Action {


    private int action_id;

    private String actionName;
    private String description;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
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
   
   public String toString()
   {
      return this.getDescription();
   }
    
   public boolean equals(Object a )
   {
      return ((Action)a).getAction_id()==this.getAction_id();
   }
 }
