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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import vendingMachine.classes.products.Product;

public class Restock {
    private final ServiceManager serviceManager;
    private final static int LOW_INVENTORY_AMOUNT = 3;
    private final static ArrayList<String> PO_HEADER = new ArrayList<String>() {{
        add("Machine");
        add("Type");
        add("Name");
        add("Price");
        add("Quantity");
        add("imageName");
        add("Option 1");
        add("Option 2");
        add("Option 3");
        add("Option 4");
        add("Option 5");
        add("Option 6");
    }};

    public Restock(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    /**
     * 
     * @param vendingMachineName
     * @return 
     */    
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

    /**
     * TODO: update to match specs.
     * @param vendingMachineName 
     */
    public void createPO(String vendingMachineName){
        ArrayList<Product> lowInventoryProducts = findLowInventory(vendingMachineName);
        CsvUtil csvUtil = new CsvUtil(vendingMachineName+"_purchaseOrder.csv");
        csvUtil.writeLine(PO_HEADER);
        lowInventoryProducts.stream().forEach((product) -> {
            csvUtil.writeLine(product.toArrayList());
        });
        csvUtil.finalizeWrite();
    }
}