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
import vendingMachine.classes.Cart;
import vendingMachine.classes.products.Product;

public class CartPane extends ProductPane {
    public CartPane(Product product, Cart cart){
        super(product);

        Button btnRemove = new Button("Remove from Cart");
        btnRemove.setOnAction(event -> {
            cart.removeFromCart(product);

            this.getChildren().add(btnRemove);
        });
    }
}
