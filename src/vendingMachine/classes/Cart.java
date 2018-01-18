package vendingMachine.classes;

import vendingMachine.classes.products.Product;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Product> purchaseList;
    private int itemCount;
    private double totalDue;
    private InventoryManagement inventoryManagement = new InventoryManagement();

    public Cart(){
        purchaseList = new ArrayList<Product>();
        itemCount = 0;
        totalDue = 0;
    }

    public void addToCart(Product product){
        purchaseList.add(product);
        inventoryManagement.addToCart(product, 1);
        itemCount++;
    }

    public void removeFromCart(Product product){
        purchaseList.remove(product);
        itemCount--;
    }

    public double getTotalDue(){
        totalDue = 0;
        for(int i = 0; i < purchaseList.size(); i++){
            totalDue += purchaseList.get(i).getPrice();
        }

        return totalDue;
    }

    public ArrayList<Product> getPurchaseList() {
        return purchaseList;
    }

    public int getItemCount() {
        return itemCount;
    }


}
