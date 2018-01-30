/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/30/18
 *
 * @about Generic Class for handling Queue's
 */
package vendingMachine.classes.customers;

import java.util.LinkedList;

/*Generics INFO: E = Element(Object)
    USE EXAMPLE INSTANIATION: GenericQueue<Custoemrs> = new GenericQueue<>();
*/
public class GenericQueue<E> {
    private LinkedList<E> list = new LinkedList<>();
    
     /**
     *  Add object to Queue
     * @param e 
     */
    public void addTo(E e){
        list.addLast(e);
    }
    
    /**
     * @return the queue with the first customer removed. 
     */
    public E removeFirst(){
        if (!isEmpty()){
            return list.removeFirst();
        }else{
            return null; // TODO: Throw Alert Message here
        }
    }
    
    /**
     * Get first customer in line. 
     * @return list[0]
     */
    public E getFirst(){
         if (!isEmpty()){
            return list.getFirst();
        }else{
            return null; // TODO: Throw Alert Message here
        } 
    }
    
    /**
     * @return size of Queue
     */
    public int getSize(){
        return list.size();
    }
    
    /**
     * Check is Queue is empty.
     * @return boolean
     */
    public boolean isEmpty(){
        if (list.isEmpty()){
            return true;
        }else{
            return false; // TODO: Throw Alert Message here
        }
    }
    
    /**
     * @return the Queue as String 
     */
    @Override
    public String toString(){
        return list.toString();
    }
}
