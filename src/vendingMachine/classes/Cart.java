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

import java.util.HashMap;
import java.util.Map;

public class Cart {

    // AlertWindow class for poping up error messages
    private AlertWindow alert = new AlertWindow();
    private final HashMap<Product, Integer> cartList;
    private InventoryManagement iManager;

    public Cart(InventoryManagement iManager){
        this.cartList = new HashMap<>();
        this.iManager = iManager;
        
    }


    /**
     * Reduce the product quantity in cart or remove it
     * @param product
     */
    public void removeFromCart(Product product){
            //check if item does exist in the cart
            if(cartList.containsKey(product)){
                int prevQuantity = cartList.get(product);
                int newQuantity = prevQuantity-1;
                cartList.replace(product, newQuantity);
                iManager.increaseQuantity(product);
                if(newQuantity == 0){
                    cartList.remove(product);
                }
            }else{
                alert.showAlert("Error C02: Product is not longer in the cart");
            }
    }

    /**
     * Add a product or quanity to the cartList and remove one from productList
     * @param product
     */
    public void addToCart(Product product){
        //Check if cartlist does not contain product
        if(!cartList.containsKey(product)){
            // add product
            cartList.put(product, 1);
            //Remove inventory from productList
            iManager.decreaseQuantity(product);
        }else{
            int prevQuantity = cartList.get(product);
            int newQuantity = prevQuantity+1;
            cartList.replace(product, newQuantity);
            iManager.decreaseQuantity(product);
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
        for (Map.Entry<Product, Integer> entry : cartList.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            totalCost += product.getPrice()*quantity;
        }

        //return totalCost
        return totalCost;
    }

    public HashMap<Product, Integer> getCartList() {
        return cartList;
    }
}
