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
    public void populateLine(){
            //Create the Line
            for (int i = serviceManager.getCustomerQueue().getCustomerQue().getSize() -1; i > 0; i--) {
                this.getChildren().add(serviceManager.getCustomerQueue().getCustomerQue().getList().get(i).getAnimation());  
            }
             //Set the first customer being serived
            this.getChildren().addAll( new Label("Serving"), new Separator(), serviceManager.getCustomerQueue().getCustomerQue().getFirst().getAnimation()); 
    }
    
    public void updateVisible(){
         if (serviceManager.isCustomerQueMode() == true && serviceManager.isManagerMode() == false) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
         System.out.println(serviceManager.isCustomerQueMode());
    }
    /**
     * Called when in the Customer finished part of the CustomerQue Loop
     */
    public void updateNode(){
       this.getChildren().clear();
       populateLine();
       
    }  
}
