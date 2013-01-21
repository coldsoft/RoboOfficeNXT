/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.components;

import java.awt.event.KeyEvent;

/**
 *
 * @author Coldsoft
 */
public class SearchJIntegerField extends SearchJTextField
{
    
    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;.?/><, ";
    private int limit = 9;

    public SearchJIntegerField()
    {
        super();
    }

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
