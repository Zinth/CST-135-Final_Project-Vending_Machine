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
package vendingMachine.classes.gui.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.products.Product;

public class CartPane extends ProductPane {
    public CartPane(ServiceManager serviceManager, Product product, Integer quantity){
        super(serviceManager, product);

        Label quantityLabel;
        quantityLabel = new Label("Quantity: "+quantity.toString());
        this.setOnMouseClicked(event -> {
            //Remove quantity or product from the cart
            serviceManager.getCart().removeFromCart(product);
            serviceManager.getIManager().increaseQuantity(product);
            //Refresh Grids
            serviceManager.UpdateGui();
        });
        this.getChildren().add(quantityLabel);
        this.getChildren().remove(stockLabel);

        this.setStyle("-fx-background-color: \n" +
"        #090a0c,\n" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
"        linear-gradient(#20262b, #191d22),\n" +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: \n" +
"        #000000,\n" +
"        linear-gradient(#7ebcea, #2f4b8f),\n" +
"        linear-gradient(#426ab7, #263e75),\n" +
"        linear-gradient(#395cab, #223768);");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: \n" +
"        #090a0c,\n" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
"        linear-gradient(#20262b, #191d22),\n" +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");
        });
    }
}
