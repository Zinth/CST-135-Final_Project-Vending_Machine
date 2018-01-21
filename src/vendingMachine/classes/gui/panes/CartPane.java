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
import vendingMachine.classes.gui.grids.CartGrid;
import vendingMachine.classes.gui.grids.InventoryGrid;
import vendingMachine.classes.products.Product;

public class CartPane extends ProductPane {
    public CartPane(Product product, CartGrid cartGrid, Integer quantity, InventoryGrid inventoryGrid){
        super(product);

        Label quantityLabel;
        quantityLabel = new Label("Quantity: "+quantity.toString());
        Button btnRemove = new Button("Remove from Cart");
        btnRemove.setOnAction(event -> {
            //Remove quantity or product from the cart
            cartGrid.getCart().removeFromCart(product);
            //Add quantity back to product in inventory
            //Refresh cartGrid
            cartGrid.fillGrid();
            this.updateLabel(product);
            inventoryGrid.sortProductGrid(product.getClass().getSimpleName().toLowerCase());
        });
        this.getChildren().add(quantityLabel);
        this.getChildren().add(btnRemove);

        //Button Styling
        btnRemove.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0))");

        btnRemove.setOnMouseEntered(event -> {
            btnRemove.setStyle("-fx-background-color:\n" +
                    "        linear-gradient(#f0ff35, #a9ff00),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");
        });
        btnRemove.setOnMouseExited(event -> {
            btnRemove.setStyle("-fx-background-color: \n" +
                    "        linear-gradient(#ffd65b, #e68400),\n" +
                    "        linear-gradient(#ffef84, #f2ba44),\n" +
                    "        linear-gradient(#ffea6a, #efaa22),\n" +
                    "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                    "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
        });
    }
}
