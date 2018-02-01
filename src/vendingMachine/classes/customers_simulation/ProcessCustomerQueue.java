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
package vendingMachine.classes.customers_simulation;

import vendingMachine.classes.GenericQueue;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import vendingMachine.classes.CsvUtil;
import vendingMachine.classes.ServiceManager;

public class ProcessCustomerQueue{
    
    private ServiceManager serviceManager;
    private GenericQueue<Customers> customerQue = new GenericQueue<>();
    private SimulatedActions actions;
    
    /**
     * CONSTRUCTOR
     * @param serviceManager 
     */
    public ProcessCustomerQueue(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
        actions  = new SimulatedActions(serviceManager);

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
    
    /**
     * @param strings
     * @return customer
     */
    public Customers createCustomers(String[] strings){
        String name = strings[0];
        String category = strings[1];
        String product = strings[2];
        Customers customer = new Customers(name, category, product);
        return customer;
    }
    
    public void nextCustomer(){
        serviceManager.getCustomerInfo().getProductPurchasedLabel().setText("");
        serviceManager.getCustomerInfo().getPriceLabel().setText("");
        customerQue.removeFirst();
        serviceManager.getEventLog().newCustomer();
        serviceManager.getCustomerInfo().updateNode();
    }

    public void simulateCustomerQue() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(customerQue.getSize());
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(1), (event) -> {
                    actions.playSimulation(customerQue.getFirst());
                }), new KeyFrame(Duration.millis(5000), (event) -> {
                    if(customerQue.getSize() > 0){
                    nextCustomer();
                    }else{
                    customerQue.getList().clear();
                    serviceManager.getALERT().showAlert("CUSTOMER SIMULATION:\n Customer simulation complete!", 14, Color.BLACK);
                    //serviceManager.setCustomerQueMode(false);
                    }
                }));
        
        timeline.play();
    }
    
    public void test(){
        actions.playSimulation(customerQue.getFirst());
    }
    
    /**
     * @return string
     */
    @Override
    public String toString(){
        return "Customers in line: " + customerQue.toString();
    }
    
    /**
     * @return customerQue
     */
    public GenericQueue<Customers> getCustomerQue() {
        return customerQue;
    }

}
