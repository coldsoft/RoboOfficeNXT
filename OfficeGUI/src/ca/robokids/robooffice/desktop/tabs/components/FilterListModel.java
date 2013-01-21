/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.robokids.robooffice.desktop.tabs.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 *
 * @author Coldsoft
 */
public class FilterListModel<E> extends AbstractListModel<E> {
   
   private Vector<E> delegate = new Vector<>();
   private List<E> complete = new ArrayList<>();
   @Override
   public int getSize() {
      return delegate.size();
   }
   
   public int getTotalSize(){
      return complete.size();
   }

   @Override
   public E getElementAt(int index) {
      return delegate.elementAt(index);
   }
   
   public void clear() {
        int index1 = delegate.size()-1;
        delegate.removeAllElements();
        if (index1 >= 0) {
            fireIntervalRemoved(this, 0, index1);
        }
        complete.clear();
    }
   
   public void filter(String txt)
   {
      
      delegate.removeAllElements();
      
      if (txt == null || txt.equals(""))
      {
         for (E element : complete)
            addFilterElement(element);
         return;
      }
      
      for (E element : complete)
      {
         if (element.toString().toLowerCase().contains(txt.toLowerCase()))
            addFilterElement(element);
      }
   }
   public void addFilterElement(E element)
   {
      int index = delegate.size();
        delegate.addElement(element);
        fireIntervalAdded(this, index, index);
   }
   public void addElement(E element) {
        int index = delegate.size();
        delegate.addElement(element);
        fireIntervalAdded(this, index, index);
        complete.add(element);
    }
}
