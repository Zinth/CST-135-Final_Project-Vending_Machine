/**
 * @project VendingMachine
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/22/18
 * 
 * @about This class allows us to easily update the total price listed in the cart.
 */
package vendingMachine.views;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import utilities.ServiceManager;
import vendingMachine.interfaces.UpdatableGUINode;

public class TotalPrice extends Label implements UpdatableGUINode{

    private final ServiceManager serviceManager;
    /**
     * Initialize the total Price Gui Node
     * @param serviceManager 
     */
    public TotalPrice(ServiceManager serviceManager) {
        super();
        this.serviceManager = serviceManager;
        double cost = serviceManager.getCart().getTotalCost();
        this.setTextAlignment(TextAlignment.CENTER);
        setText("Total: "+serviceManager.formatPrice(cost));
        
        //Format Total Price
        this.setTextFill(Color.WHITE);
    }
    /**
     * Instructs the service manager on how to update this node.
     */
    @Override
    public void updateNode() {
        double cost = serviceManager.getCart().getTotalCost();
        setText("Total: " + serviceManager.formatPrice(cost));
    }
    
}
