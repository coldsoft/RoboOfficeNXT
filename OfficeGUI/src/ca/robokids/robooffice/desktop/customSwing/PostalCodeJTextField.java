/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.customSwing;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Coldsoft
 */
public class PostalCodeJTextField extends JTextField
{

    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;.?/><, ";
    private int limit = 6;

    /**
     * 
     * @param limit
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    @Override
    public void processKeyEvent(KeyEvent ev)
    {
        char c = ev.getKeyChar();
        if (badchars.indexOf(c) > -1)
        {
            ev.consume();
        } else
        {
            if (Character.isLowerCase(c))
            {
                ev.setKeyChar(Character.toUpperCase(c));
            }
            super.processKeyEvent(ev);

            if (this.getText().length() > limit)
            {
                String text = this.getText();
                this.setText(text.substring(0, limit));
            }
            
            
        }
    }
}
