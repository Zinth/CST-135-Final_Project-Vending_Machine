/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class creates Panes of products for the inventory.
 */
package vendingMachine.classes.gui.panes;

import javafx.scene.control.Button;
import vendingMachine.classes.Cart;
import vendingMachine.classes.gui.AnimationPane;
import vendingMachine.classes.products.Product;

public class InventoryPane extends ProductPane {
    public InventoryPane(Product product, Cart cart, double x, double y){
        super(product);

        Button btnAdd = new Button("Add to Cart");
        btnAdd.setOnAction(event -> {
            cart.addToCart(product);
            AnimationPane animation = new AnimationPane(getPositoinX(), getPositionY(), x, y, getProductImage() );
        });

        this.getChildren().add(btnAdd);
    }
}
