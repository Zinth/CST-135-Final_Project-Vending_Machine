/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.customers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import vendingMachine.classes.CsvUtil;
import vendingMachine.classes.ServiceManager;

/**
 *
 * @author Chris
 */
public class ProcessCustomerQueue{
    
    private ServiceManager serviceManager;
    private LinkedList<Customers> customerQue = new LinkedList<>();
    
    public ProcessCustomerQueue(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
        
        
    }
    
    public void populateCustomerQue(String path){
         //get a new CSVUtil
        CsvUtil customer = new CsvUtil(path);
        
        //Read from CSV file.
        List<String[]> customerList = customer.readCSV();

        //Interate through the list of String[]
        for (int i = 0; i < customerList.size(); i++) {
            for (int j =0; j < customerList.get(i).length; j++)
                //Add new customers to the customerQue
               addCustomer(new Customers(customerList.get(j)[i], customerList.get(j)[i]));
        }
    }

    /**
     *  Add customer to Queue
     * @param customer 
     */
    public void addCustomer(Customers customer){
        customerQue.addLast(customer);
    }
    
    /**
     * @return the queue with the first customer removed. 
     */
    public Customers removeCustomer(){
        if (!isEmpty()){
            return customerQue.removeFirst();
        }else{
            return null; // TODO: Throw Alert Message here
        }
    }
    
    /**
     * Get first customer in line. 
     * @return Customers
     */
    public Customers getFirst(){
         if (!isEmpty()){
            return customerQue.getFirst();
        }else{
            return null; // TODO: Throw Alert Message here
        }
        
    }
    
    /**
     * @return size of Queue
     */
    public int getSize(){
        return customerQue.size();
    }
    
    /**
     * Check is Queue is empty.
     * @return boolean
     */
    public boolean isEmpty(){
        if (customerQue.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * @return the Queue as String 
     */
    @Override
    public String toString(){
        return "Customers in Line " + customerQue.toString();
    }
   
   
}
