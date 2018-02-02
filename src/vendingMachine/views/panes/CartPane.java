/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class creates Panes of products for the cart.
 */
package vendingMachine.views.panes;

import java.awt.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utilities.ServiceManager;
import vendingMachine.products.Product;

public class CartPane extends ProductPane {
    public CartPane(ServiceManager serviceManager, Product product, Integer quantity){
        super(serviceManager, product);

        Label quantityLabel;
        quantityLabel = new Label("Quantity: "+quantity.toString());
        
        //remove product price
        infoVBox.getChildren().remove(productInfo);
        
        this.setOnMouseClicked(event -> {
            //Remove quantity or product from the cart
            serviceManager.getCart().removeFromCart(product);
            serviceManager.getIManager().increaseQuantity(product);
            //Refresh Grids
            serviceManager.UpdateGui();
        });
        infoVBox.getChildren().add(quantityLabel);

        //Set the default style of the panel
        this.setStyle(BTN_NORMAL);

        //Set the if hovering styles of the panel.
        this.setOnMouseEntered(event -> {this.setStyle(BTN_HOVER);});
        this.setOnMouseExited(event -> {this.setStyle(BTN_NORMAL);});
    }
}
