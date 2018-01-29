/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/30/18
 *
 * @about Create a pane that shows current customer info 
 */
package vendingMachine.classes.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.gui.animations.IdleAnimation;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class CustomerPane extends HBox implements UpdatableGUINode{
    
    private ServiceManager serviceManager;
    private Label customerInfo;
    private int numberOfCustomers;
    private VBox customerLine;
    
    public CustomerPane(ServiceManager serviceManager, int startNumberOfCustomers){
        //Set Service Manager
        this.serviceManager = serviceManager;
        this.numberOfCustomers = startNumberOfCustomers;
        
        //Left Align everyint in this pane
        this.setAlignment(Pos.CENTER_LEFT);
        VBox infoVBox = new VBox();
        
        //Customer Info Label
        customerInfo = new Label(" Replace this with customer info using setCustomerInfo Method");
        
        //Alertlabel should be set the the text of the alert window.
        Label alertLabel = new Label("Event Log:\n" + serviceManager.getALERT().getTxtError().getText());
        
        //Populate the customerLine VBox
        customerLine = new VBox();
        populateLine(numberOfCustomers);
        
        //Fill infoVBox
        infoVBox.getChildren().addAll(customerInfo, new Separator(), alertLabel);
        
        //Add all nodes to this pane
        this.getChildren().addAll(infoVBox, customerLine);
                
    }
    
    public void populateLine(int numOfCustomers){
        for(int i = 0; i < numOfCustomers; i++){
            customerLine.getChildren().add(new IdleAnimation()); 
        }
    }
    
    public void setCustomerInfo(String customerInfo){
        this.customerInfo.setText(customerInfo);
    }
    
    
    public void updateNode(){
        //Add functionality to change number of customers (something like below.
//      setNumberOfCustomers(serviceManager.getQue.length);
        
        //Update pane
        populateLine(numberOfCustomers);
        Label alertLabel = new Label(serviceManager.getALERT().getTxtError().getText());
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
    
    
    
}
