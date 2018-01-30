/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/30/18
 *
 * @about Class for processing Customer Queue's
 */
package vendingMachine.classes.customers;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.paint.Color;
import vendingMachine.classes.CsvUtil;
import vendingMachine.classes.ServiceManager;

public class ProcessCustomerQueue{
    
    private ServiceManager serviceManager;
    private GenericQueue<Customers> customerQue = new GenericQueue<>();
    
    public ProcessCustomerQueue(ServiceManager serviceManager){
        this.serviceManager = serviceManager;

        populateCustomerQue("testQue.csv");
       
    }
    
    /**
     * Reads from a CSV file and add customers from the List of String arrays generated.
     * @param path to .CSV File
     */
    public void populateCustomerQue(String path){
         //get a new CSVUtil
        CsvUtil customer = new CsvUtil(path);
        
        //Read from CSV file. (This acts like a 2D Array)
        List<String[]> customerList = customer.readCSV();

        //Interate through the list of String[]
        for (int i = 1; i < customerList.size(); i++) {
            customerQue.addTo(createCustomers(customerList.get(i)));
        }
    }
    
    public Customers createCustomers(String[] strings){
        String name = strings[0];
        String category = strings[1];
        String product = strings[2];
        Customers customer = new Customers(name, category, product);
        return customer;
    }

    @Override
    public String toString(){
        return "Customers in line: " + customerQue.toString();
    }
    
    /**
     * 
     * @return customerQue
     */
    public GenericQueue<Customers> getCustomerQue() {
        return customerQue;
    }
   
}
