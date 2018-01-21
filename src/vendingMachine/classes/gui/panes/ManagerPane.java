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
import vendingMachine.classes.gui.grids.CartGrid;
import vendingMachine.classes.gui.grids.InventoryGrid;
import vendingMachine.classes.gui.grids.ManagerGrid;
import vendingMachine.classes.products.Product;

public class ManagerPane extends ProductPane {

    private InventoryManagement iManager;

    public ManagerPane(Product product, InventoryManagement iManager, ManagerGrid managerGrid, InventoryGrid inventoryGrid, CartGrid cartGrid){
        super(product);
        this.iManager = iManager;

        Button btnIncrease = new Button("+");
        btnIncrease.setOnAction(event -> {
            this.iManager.increaseQuantity(product);
            managerGrid.fillGrid();
            this.updateLabel(product);
            cartGrid.fillGrid();
            inventoryGrid.sortProductGrid(product.getClass().getSimpleName().toLowerCase());
        });

        Button btnDecrease = new Button("-");
        btnDecrease.setOnAction(event -> {
            this.iManager.decreaseQuantity(product);
            managerGrid.fillGrid();
            this.updateLabel(product);
            cartGrid.fillGrid();
            inventoryGrid.sortProductGrid(product.getClass().getSimpleName().toLowerCase());
        });

        HBox btnHBox = new HBox();
        btnHBox.setPadding(new Insets(10));
        btnHBox.getChildren().addAll(btnIncrease, btnDecrease);

        this.getChildren().addAll(btnHBox);
    }
}
