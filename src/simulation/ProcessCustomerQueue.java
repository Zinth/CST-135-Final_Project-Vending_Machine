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
package simulation;

import vendingMachine.classes.GenericQueue;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utilities.CsvUtil;
import utilities.ServiceManager;

public class ProcessCustomerQueue{
    
    private ServiceManager serviceManager;
    private GenericQueue<Customers> customerQue = new GenericQueue<>();
    private SimulatedActions actions;
    private Timeline simTimeline = new Timeline();;
    private String path;
    
    /**
     * CONSTRUCTOR
     * @param serviceManager 
     */
    public ProcessCustomerQueue(ServiceManager serviceManager, String path){
        this.serviceManager = serviceManager;
        this.path = path;
        actions  = new SimulatedActions(serviceManager);

        populateCustomerQue();
    }
    
    /**
     * Reads from a CSV file and add customers from the List of String arrays generated.
     * @param path to .CSV File
     */
    public void populateCustomerQue(){
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
        simTimeline.getKeyFrames().clear();
        simTimeline.setCycleCount(customerQue.getSize());
        simTimeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(1), (event) -> {
                    actions.playSimulation(customerQue.getFirst());
                }), new KeyFrame(Duration.millis(5000), (event) -> {
                    if(customerQue.getSize() > 1){
                    nextCustomer();
                    }else{
                    customerQue.getList().clear();
                    serviceManager.getALERT().showAlert("CUSTOMER SIMULATION:\n Customer simulation complete!\n", 24, Color.BLACK, serviceManager.getIManager().toString());
                    //serviceManager.setCustomerQueMode(false);
                    }
                }));
    }
    
    public void playSimulation(){
        if(simTimeline.getStatus() == Animation.Status.STOPPED ){
                //Set up the simTimeline keyframs
                simulateCustomerQue();
                //update the customer line
                serviceManager.getCustomerLine().updateNode();
                // play the simTimeline
                simTimeline.play();
            }else if(simTimeline.getStatus() == Animation.Status.PAUSED){
                simTimeline.play();
            }else{
                simTimeline.pause();
            }
    }
    
    public void stopSimulation() {
        if (simTimeline.getStatus() == Animation.Status.PAUSED || simTimeline.getStatus() == Animation.Status.RUNNING) {
            actions.stopSimulation();
            customerQue.getList().clear();
            serviceManager.getEventLog().reset();
            serviceManager.getCart().removeFromCart();
            serviceManager.getCartGrid().getChildren().clear();
            populateCustomerQue();
            simTimeline.stop();
            
        }
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

    public Timeline getSimTimeline() {
        return simTimeline;
    }
    
    

}
