/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.loaders;

import java.util.ResourceBundle;
import javax.swing.ImageIcon;

/**
 *
 * @author Coldsoft
 */
public class FramePropertyLoader {
   //property file location
        private final static String propertyDIR = "ca/robokids/robooffice/desktop/properties/mainFrame";
	private static ResourceBundle bundle = null;
   
   private static ResourceBundle getBundle()
   {
      if (bundle == null)
         bundle = ResourceBundle.getBundle(propertyDIR);
      return bundle;
   }
   
   /**
     * returns frame title.<p> If property file is missing, default title is "JFrame title".
     * @return Title of the main frame.
     */
    public static String getFrameTitle()
    {
        if (getBundle() != null)
            return getBundle().getString("frameTitle");
        return "JFrame title";
    }

    public static String getLoadingMessage()
    {
       if (getBundle() != null)
       {
          String loading = getBundle().getString("systemBusyMessage");
          return loading;
       }
        return "Loading";
    }
    /**
     * returns frame width.<p> If property file is missing, default width is 750.
     * @return frame width.
     */
    public static int getFrameWidth()
    {

        if (getBundle() != null)
            return Integer.parseInt(getBundle().getString("frameWidth"));
        return 750;
    }

    /**
     * returns frame height.<p> If property file is missing, default height is 550.
     * @return frame height
     */
    public static int getFrameHeight()
    {

        if (getBundle() != null)
            return Integer.parseInt(getBundle().getString("frameHeight"));
        return 550;
    }
    /**
     * returns Tab width.<p> If property file is missing, default width is 750.
     * @return tab width.
     */
    public static int getTabWidth()
    {

        if (getBundle() != null)
            return Integer.parseInt(getBundle().getString("tabWidth"));
        return 750;
    }

    /**
     * returns Tab height.<p> If property file is missing, default height is 550.
     * @return tab height
     */
    public static int getTabHeight()
    {

        if (getBundle() != null)
            return Integer.parseInt(getBundle().getString("tabHeight"));
        return 550;
    }
    /**
     * returns LookAndFeel package name<p> If property file is missing, default height is 550.
     * @return frame height
     */
    public static String getLookAndFeel()
    {

        if (getBundle() != null)
            return getBundle().getString("lookAndFeel");
        return "Nimbus";


    }
    
    
    public static Integer getMinimumTabSize()
    {
        if (bundle != null)
            return Integer.parseInt(bundle.getString("minimumTabSize"));
        return 60;
    }
    /**
     * 
     * @return
     */
    public static String getImageDIR()
    {
        
        return getBundle().getString("imageDirectory");
    }
    /**
     * 
     * @param imageID
     * @return
     */
    public static ImageIcon getImageIcon(String imageID)
    {
        String url = getBundle().getString("imageDirectory") + getBundle().getString(imageID);
        return new ImageIcon(getBundle().getClass().getResource(url));
    }
   
}
