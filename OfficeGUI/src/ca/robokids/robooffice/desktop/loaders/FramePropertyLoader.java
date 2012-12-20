/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.loaders;

import java.util.ResourceBundle;

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
   
}
