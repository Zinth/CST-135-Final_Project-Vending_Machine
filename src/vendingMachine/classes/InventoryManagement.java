/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class manages the inventory of the Dispenser and is infact and extention of the Dispenser Class
 */

package vendingMachine.classes;

import vendingMachine.products.Product;

public class InventoryManagement extends Dispenser {

    public InventoryManagement(){
        super(20, 0);
    }

    /**
     * Increace Product Quantity by one
     * @param product 
     */
    public void increaseQuantity(Product product){
        for (int index = 0; index < getProducts().size(); index++){
            if(getProducts().get(index) == product){
                getProducts().get(index).increaseQuantity();
            }
        }
    }

    /**
     * Decrease Product Quantity by one
     * @param product 
     */
    public void decreaseQuantity(Product product){
        for (int index = 0; index < getProducts().size(); index++){
            if(getProducts().get(index) == product){
                getProducts().get(index).decreaseQuantity();
            }
        }
    }

    public void resetProducts() {
        for (int index = 0; index < getProducts().size(); index++){
                getProducts().get(index).setQuantity(defaultQuantity);
        }
    }


    /**
     * Prints the inventory product and quantity to string.
     */
    @Override
    public String toString(){
        String temp = "INVENTORY STOCK:\n";
        for(int i = 0; i < getProducts().size(); i++){
            temp+= getProducts().get(i).getProductName() + " - " + getProducts().get(i).getQuantity() +"\n";
        }
        return temp;
    }
}
