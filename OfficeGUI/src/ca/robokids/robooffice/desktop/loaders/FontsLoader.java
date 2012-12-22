/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.loaders;

import java.awt.Font;
import java.util.ResourceBundle;

/**
 *
 * @author Coldsoft
 */
public class FontsLoader {
   
   //property file location
   private final static String propertyDIR = "ca/robokids/robooffice/desktop/properties/swingFonts";
   private static ResourceBundle bundle = ResourceBundle.getBundle(propertyDIR);
   
   private static ResourceBundle getBundle()
   {
      if (bundle == null)
         bundle = ResourceBundle.getBundle(propertyDIR);
      return bundle;
   }
   /**
     *
     * @return
     */
    public static Font getMenuFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("menuFont"),Font.BOLD,Integer.parseInt(getBundle().getString("menuFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }

    /**
     *
     * @return
     */
    public static Font getMenuItemFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("menuItemFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("menuItemFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     *
     * @return
     */
    public static Font getShortCutButtonFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("shortCutButtonFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("shortCutButtonFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getButtonFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("buttonFont"),Font.BOLD,Integer.parseInt(getBundle().getString("buttonFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getStaticLabelFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("staticLabelFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("staticLabelFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getDynamicLabelFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("dynamicLabelFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("dynamicLabelFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getListFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("listFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("listFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    public static Font getBigListFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("BigListFont"),Font.BOLD,Integer.parseInt(getBundle().getString("BigListFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    public static Font getErrorLabelFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("errorLabelFont"),Font.BOLD,Integer.parseInt(getBundle().getString("errorLabelFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getBigStaticLabelFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("bigStaticLabelFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("bigStaticLabelFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getTextFieldFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("textFieldFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("textFieldFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     * 
     * @return
     */
    public static Font getTitleBorderFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("titleBorderFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("titleBorderFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    /**
     *
     * @return return the Font of the tab
     */
    public static Font getTabFont()
    {

        if (getBundle() != null)
            return new Font(getBundle().getString("tabFont"),Font.PLAIN,Integer.parseInt(getBundle().getString("tabFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
    public static Font getComboBoxFont()
    {
	if (getBundle() != null)
            return new Font(getBundle().getString("comboBoxFont"),Font.BOLD,Integer.parseInt(getBundle().getString("comboBoxFontSize")));
        return new Font("Comic Sans MS",Font.PLAIN,8);
    }
   
}
