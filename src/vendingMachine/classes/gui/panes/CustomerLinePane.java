/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/30/18
 *
 * @about Create a pane that shows current customerLine 
 */
package vendingMachine.classes.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.gui.animations.IdleAnimation;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class CustomerLinePane extends VBox implements UpdatableGUINode{
    
    private ServiceManager serviceManager;
    
    public CustomerLinePane(ServiceManager serviceManager){
        //Set Service Manager
        this.serviceManager = serviceManager;
        
        //Set simple style no need for custom class in CSS style sheet.
        this.setStyle("-fx-background-color: #54428E");
        this.setSpacing(2);
        this.setPadding(new Insets(5,0,5,0));
        
       updateVisible();
        updateNode();
    }
    
    /**
     * Populates the VBox with Customers
     * @param numberOfCustomers 
     */
    public void populateLine(int numberOfCustomers){
        if (numberOfCustomers > 0) {
            
            //Create the Line
            for (int i = 0; i < numberOfCustomers -1; i++) {
                this.getChildren().add(new IdleAnimation());  
            }
             //Set the first customer being serived
            this.getChildren().addAll( new Label("Serving"), new Separator(), new IdleAnimation()); 
        }
        
    }
    
    public void updateVisible(){
         if (serviceManager.isCustomerQue() == true) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
         System.out.println(serviceManager.isCustomerQue());
    }
    /**
     * Called when in the Customer finished part of the CustomerQue Loop
     */
    public void updateNode(){
      
        //Change rather or not this pane is visable based on boolean CustomerQue in Service Manager
       
            //Add functionality to change number of customers in line (something like below.)
//          populateLine(currentQueLength)

            
            //Temp for testing
            populateLine(10); // --- REMOVE ME ---
       
    }  
}
