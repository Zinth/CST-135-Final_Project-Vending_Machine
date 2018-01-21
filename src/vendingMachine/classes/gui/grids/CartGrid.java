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

import java.text.NumberFormat;
import javafx.scene.layout.GridPane;
import vendingMachine.classes.Cart;
import vendingMachine.classes.gui.panes.CartPane;
import vendingMachine.classes.products.Product;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Text;

public class CartGrid extends GridPane {

    private HashMap<Product, Integer> cartList;
    private Cart cart;
    private int columns;
    private Text costLabel;

    private final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    /**
     * constructor to create the initial cartGrid
     *
     * @param cart
     * @param columns
     */
    public CartGrid(Cart cart, int columns, Text costLabel) {
        this.cart = cart;
        this.cartList = cart.getCartList();
        this.costLabel = costLabel;
        this.columns = columns;

        //FillCartList
        fillGrid();
    }

    /**
     * Fill that cart grid with product from the cartList
     */
    public void fillGrid() {
        //Clear all nodes from the grid before filling it again
        this.getChildren().clear();

        //productCounter for keeping track of cartList index
        int productCounter = 0;

        //Loop through each product in cart list
        int row = 0;
        int col = 0;
        for (Map.Entry<Product, Integer> entry : cartList.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            add(new CartPane(key, this, value), col, row);
            col++;
            if(col >= getColumns()){
                row++;
                col = 0;
            }
        }
        costLabel.setText("Total:" + FORMATTER.format(cart.getTotalCost()));
    }

    public int getColumns() {
        return columns;
    }

    public Cart getCart() {
        return cart;
    }
}
