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
import vendingMachine.classes.Cart;
import vendingMachine.classes.gui.panes.CartPane;
import vendingMachine.classes.products.Product;

import java.util.ArrayList;

public class CartGrid extends GridPane{

    private ArrayList<Product> cartList;
    private Cart cart;
    private int columns;

    /**
     * constructor to create the initial cartGrid
     * @param cart
     * @param columns
     */
    public CartGrid(Cart cart, int columns){
        this.cart = cart;
        this.cartList = cart.getCartList();

        this.columns = columns;

        //FillCartList
        fillGrid();
    }

    /**
     * Fill that cart grid with product from the cartList
     */
    public void fillGrid(){
        //Clear all nodes from the grid before filling it again
        this.getChildren().clear();

        //productCounter for keeping track of cartList index
        int productCounter = 0;

        //Loop through each product in cart list
        for(int i = 0; i < cartList.size(); i++){
            for(int j = 0; j < getColumns(); j++){
                //add that product to the cartGrid
                add(new CartPane(cartList.get(productCounter), this), j, i);
                productCounter++;
            }
            //Don't go over bounds of cartList.size()
            if(productCounter + 1 >= cartList.size()){
                break;
            }
        }
    }


    public int getColumns() {
        return columns;
    }

    public Cart getCart() {
        return cart;
    }
}
