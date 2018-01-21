/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @date 01/19/18
 *
 * @about Class displays all products on a grid for manager use.
 */

package vendingMachine.classes.gui.grids;

import javafx.scene.layout.GridPane;
import vendingMachine.classes.Cart;
import vendingMachine.classes.InventoryManagement;
import vendingMachine.classes.gui.panes.ManagerPane;
import vendingMachine.classes.products.Product;

import java.util.ArrayList;

public class ManagerGrid extends GridPane{

    private ArrayList<Product> productList;
    private InventoryManagement iManager;
    private int columns;

    /**
     * Constructor
     * @param iManager
     * @param columns
     */
    public ManagerGrid(InventoryManagement iManager, int columns){
        this.iManager = iManager;
        this.productList = iManager.getProductList();
        this.columns = columns;

        fillGrid();
    }

    /**
     * Method for filling the grid.
     */
    public void fillGrid(){
        int productCounter = 0;
        for(int i = 0; i < productList.size(); i++){
            for(int j = 0; j < getColumns(); j++){
                if(productCounter >= productList.size()){
                    break;
                }
                add(new ManagerPane(productList.get(productCounter), iManager, this), j, i);
                productCounter++;
            }
        }
    }

    public int getColumns() {
        return columns;
    }
}
