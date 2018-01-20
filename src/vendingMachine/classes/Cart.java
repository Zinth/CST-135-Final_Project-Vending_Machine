/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class manages adding products to and from the cart
 */

package vendingMachine.classes;

import vendingMachine.classes.gui.AlertWindow;
import vendingMachine.classes.products.Product;

import java.util.ArrayList;

public class Cart {

    // AlertWindow class for poping up error messages
    private AlertWindow alert = new AlertWindow();

    private ArrayList<Product> cartList = new ArrayList<Product>();
    private InventoryManagement iManager;

    public Cart(InventoryManagement iManager){
        this.iManager = iManager;
    }

    /**
     * Reduce the product quantity in cart or remove it
     * @param product
     */
    public void removeFromCart(Product product){
        //Loop through all product in the cart
        for(int index = 0; index < cartList.size(); index++){
            //check if item does exist in the cart
            if(cartList.get(index) == product){
                //If soDecrease the quantity of the Product in Cart
                cartList.get(index).decreaseQuantity();

                //Increase quantity of product in Productlist
                iManager.increaseQuantity(product);

                //Check to see if the quantity of the product fall to or below 0
                if(cartList.get(index).getQuantity() <= 0 ){
                    //If so remove product from cartList
                    cartList.remove(index);
                }
            }else{
                //else alert customer that no product in cart.
                alert.showAlert("Error C02: Product is not longer in the cart");
            }
        }
    }

    /**
     * Add a product or quanity to the cartList and remove one from productList
     * @param product
     */
    public void addToCart(Product product){
        //Check if cartlist does not contain product
        if(!cartList.contains(product)){
            // set the Quantity of this product to 1.
            product.setQuantity(1);
            // add product
            cartList.add(product);
            //Remove inventory from productList
            iManager.decreaseQuantity(product);
        }else{
            //loop through all the products in the cartList to find the product.
            for(int index = 0; index < cartList.size(); index++){
                if(cartList.get(index) == product) {
                    //increase the products quantity
                    cartList.get(index).increaseQuantity();
                    //Remove inventory from productList
                    iManager.decreaseQuantity(product);
                }
            }
        }
    }

    /**
     * Get the total cost of all items in cart.
     * @return totalCost
     */
    public double getTotalCost(){
        // double to hold total cost
        double totalCost = 0;

        //Loop through each product and add the products price times quantity to toal cost.
        for (int index = 0 ; index < cartList.size(); index++){
            totalCost += cartList.get(index).getPrice() * cartList.get(index).getQuantity();
        }

        //return totalCost
        return totalCost;
    }
}
