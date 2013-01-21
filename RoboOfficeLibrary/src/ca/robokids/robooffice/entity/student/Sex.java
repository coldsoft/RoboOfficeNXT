
package ca.robokids.robooffice.entity.student;

import java.awt.Color;

public enum Sex {

    BOY("Boy"),GIRL("Girl");
    
    String value;
    private Sex(String value)
    {
       this.value = value;
       
    }
    public String toString()
    {
       return this.value;
    }
    
    /**
     * 
     * @return blue color for boy / pink color for girl
     */
    public Color getColor()
    {
       if (this == BOY)
          return new Color(0,115,228);
       return new Color(253,137,172);
    }
    
    /**
     * 
     * @return "his" or "her"
     */
    public String getPossessivePronoun()
    {
       if (this == BOY) {
         return "his";
      }
       return "her";
    }
    
    /**
     * 
     * @return "he" or "she" 
     */
    public String getPronoun()
    {
       if (this == BOY) {
         return "he";
      }
       return "she";
    }
    
    
    
    
 }
