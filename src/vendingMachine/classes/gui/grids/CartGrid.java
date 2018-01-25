/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @date 01/20/18
 *
 * @about Class displays all products in the cartList.
 */
package vendingMachine.classes.gui.grids;

import javafx.scene.layout.GridPane;
import vendingMachine.classes.gui.panes.CartPane;
import vendingMachine.classes.products.Product;

import java.util.Map;
import javafx.geometry.Insets;
import vendingMachine.classes.ServiceManager;
import vendingMachine.interfaces.UpdatableGUINode;

public final class CartGrid extends GridPane implements UpdatableGUINode{

    private final ServiceManager serviceManager;
    private final int columns;
    

    /**
     * constructor to create the initial cartGrid
     * @param serviceManager
     * @param columns 
     */
    public CartGrid(ServiceManager serviceManager, int columns) {
        this.serviceManager = serviceManager;
        this.columns = columns;
        updateNode();
        
        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(5,5,5,5));
        this.setMaxWidth(100);
        
    }
    
    /**
     * Fill that cart grid with product from the cartList
     */
    @Override
    public void updateNode() {
        //Clear all nodes from the grid before filling it again
        this.getChildren().clear();

        //Loop through each product in cart list
        int row = 0;
        int col = 0;
        for (Map.Entry<Product, Integer> entry : serviceManager.getCart().getCartList().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            add(new CartPane(serviceManager, product, quantity), col, row);
            col++;
            if(col >= getColumns()){
                row++;
                col = 0;
            }
        }
        
    }

    public int getColumns() {
        return columns;
    }
}
