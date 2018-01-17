package vendingMachine.classes;

import vendingMachine.classes.products.Product;

import java.util.ArrayList;

public class ManageInventory extends Dispenser{
    // TODO: THIS IS A TEMPORARY CLASS TO HELP FIGURE OUT THE INVENTORY SYSTEM ERASE IF NOT USED!! THIS COULD ALSO REPLACE THE CART CLASS.

    private ArrayList<Product> cartList = new ArrayList<Product>();

    /**
     * Sets the quantity of products in productList to an amount
     *
     * @param amount
     */
    public void restockProducts(int amount){
        for(int i = 0; i < getProductList().size(); i++){
            getProductList().get(i).setQuantity(amount);
        }
    }

    /**
     * Remove product from cartList and add it back to productList
     * @param product
     */
    public void removeFromCart(Product product){
        for(int i = 0; i < cartList.size(); i++){
            if(cartList.get(i) == product){
                //Reduce the quantity of the product in cart list
                cartList.get(i).setQuantity(cartList.get(i).getQuantity() - 1);
                if(cartList.get(i).getQuantity() <= 0 ){
                    //If quantity fall to or bellow 0 remove product from cartList
                    cartList.remove(i);
                }
            }
            for(int j = 0; j < getProductList().size(); j++) {
                if(getProductList().get(j) == product){
                    //Add increase product quantity in product list
                    getProductList().get(j).setQuantity(getProductList().get(j).getQuantity() +1);
                }
            }
        }
    }

    /**
     * Add a product or quanity to the cartList and remove one from productList
     * @param product
     */
    public void addToCart(Product product){
        for(int i = 0; i < getProductList().size(); i++){
            //If product is in product list and the quantity isn't 0 then add the product to cartList.
            if(getProductList().get(i) == product && getProductList().get(i).getQuantity() > 0){
                for(int j = 0; j < cartList.size(); j++){
                    if(cartList.get(j) != product){
                        //Add product to cartList
                        cartList.add(product);
                        //Make that products quantity = 1
                        cartList.get(j).setQuantity(1);
                    }else{
                        // Add one quantity to the product in cartList
                        cartList.get(j).setQuantity(cartList.get(j).getQuantity() +1);
                }

                }
                //Subtract 1 quantity from the product in productList.
                getProductList().get(i).setQuantity(getProductList().get(i).getQuantity() -1);
            }
        }
    }
}
