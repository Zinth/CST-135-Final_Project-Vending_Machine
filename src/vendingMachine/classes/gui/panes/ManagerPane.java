/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class creates Panes of products for the manager.
 */

package vendingMachine.classes.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import vendingMachine.classes.InventoryManagement;
import vendingMachine.classes.products.Product;

public class ManagerPane extends ProductPane {

    public ManagerPane(Product product, InventoryManagement iManager){
        super(product);

        Button btnIncrease = new Button("+");
        btnIncrease.setOnAction(event -> {
            iManager.increaseQuantity(product);
        });

        Button btnDecrease = new Button("-");
        btnDecrease.setOnAction(event -> {
            iManager.decreaseQuantity(product);
        });

        HBox btnHBox = new HBox();
        btnHBox.setPadding(new Insets(10));
        btnHBox.getChildren().addAll(btnIncrease, btnDecrease);

        this.getChildren().addAll(btnHBox);
    }
}
