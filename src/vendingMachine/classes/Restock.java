/**
 * @project Milestone6
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/24/18
 *
 * @about This class allows a manager to restock and create Purchase orders
 */
package vendingMachine.classes;

import java.util.ArrayList;
import java.util.Set;
import vendingMachine.classes.products.Product;

public class Restock {
    private final ServiceManager serviceManager;
    private final static int LOW_INVENTORY_AMOUNT = 3;

    public Restock(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
    
    public ArrayList<Product> findLowInventory(String vendingMachineName){
        ArrayList<Product> products = this.serviceManager.getVmManager().getVendingMachine(vendingMachineName);
        ArrayList<Product> lowInventoryProducts = new ArrayList<>();
        products.stream().filter((product) -> (product.getQuantity() <= LOW_INVENTORY_AMOUNT )).forEach((product) -> {
            lowInventoryProducts.add(product);
        });
        return lowInventoryProducts;
    }
    public void createAllPOs(){
        Set<String> vendingMachinesNames = this.serviceManager.getVmManager().getVendingMachineNames();
        for(String vendingMachineName : vendingMachinesNames){
            createPO(vendingMachineName);
        }
    }
    public void createPO(String vendingMachineName){
        ArrayList<Product> lowInventoryProducts = findLowInventory(vendingMachineName);
        
    }
}
