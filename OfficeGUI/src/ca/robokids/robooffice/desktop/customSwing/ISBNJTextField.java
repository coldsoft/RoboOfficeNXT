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
public class ISBNJTextField extends JTextField
{

    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;.?/><, ";
    private int limit = 20;

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
        if ((Character.isLetter(c) && !ev.isAltDown())
            || badchars.indexOf(c) > -1)
        {
            ev.consume();
        } else
        {
            super.processKeyEvent(ev);
            if (this.getText().length() > limit)
            {
                String text = this.getText();
                this.setText(text.substring(0, limit));

            }
        }
    }

    
}