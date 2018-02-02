/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/30/18
 *
 * @about Create a pane that shows current customerLine 
 */
package vendingMachine.views.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import utilities.ServiceManager;
import vendingMachine.views.animations.IdleAnimation;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class DisplayCustomer extends VBox implements UpdatableGUINode{
    
    private ServiceManager serviceManager;
    private VBox serving = new VBox();
    private VBox line = new VBox();
    
    public DisplayCustomer(ServiceManager serviceManager){
        //Set Service Manager
        this.serviceManager = serviceManager;
        
        //Set simple style no need for custom class in CSS style sheet.
        this.setStyle("-fx-background-color: #54428E");
        this.setSpacing(35);
        this.setPadding(new Insets(5,0,5,0));
        this.setAlignment(Pos.BOTTOM_CENTER);
        //this.setMaxHeight();

        serving.setSpacing(10);
        
        this.getChildren().addAll(line, serving);
        
        updateVisible();
        updateNode();
        
    }
 
    /**
     * Populates the VBox with Customers
     *
     * @param numberOfCustomers
     */
    public void populateLine() {
        // Clear serving VBox
        serving.getChildren().clear();
        line.getChildren().clear();
        //Create the Line
        for (int i = serviceManager.getCustomerQueue().getCustomerQue().getSize() - 1; i > 0; i--) {
            if (i == 1) {
                //Set the first customer being serived
                serving.getChildren().addAll(new Label("Serving"), new Separator(), serviceManager.getCustomerQueue().getCustomerQue().getFirst().getAnimation());
            } else {
                line.getChildren().add(serviceManager.getCustomerQueue().getCustomerQue().getList().get(i).getAnimation());
            }
        }
        

    }
    
    public void updateVisible(){
         if (serviceManager.isCustomerQueMode() == true && serviceManager.isManagerMode() == false) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
//         System.out.println(serviceManager.isCustomerQueMode());
    }
    /**
     * Called when in the Customer finished part of the CustomerQue Loop
     */
    public void updateNode(){
       populateLine();
       
    }  
}
