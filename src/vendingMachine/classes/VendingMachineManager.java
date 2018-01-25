/**
 * @project Milestone6
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/24/18
 *
 * @about This class allows a manager to manage Vending Machines
 */
package vendingMachine.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import vendingMachine.classes.products.Product;


public class VendingMachineManager {
    private final HashMap<String, ArrayList<Product>> vendingMachines = new HashMap<>();
    

    /**
     * Add a vending machine to the manager
     * @param vendingMachineName
     * @param products 
     */
    public void addVendingMachine(String vendingMachineName, ArrayList<Product> products){
        vendingMachines.put(vendingMachineName, products);
    }
    /**
     * Get a specific Vending Machine
     * @param vendingMachineName
     * @return 
     */
    public ArrayList<Product> getVendingMachine(String vendingMachineName){
        return vendingMachines.get(vendingMachineName);
    }
    
    /**
     * Get a list of vending machine names
     * @return 
     */
    public Set<String> getVendingMachineNames(){
        return vendingMachines.keySet();
    }
    
    public void removeVendingMachine(String vendingMachineName){
        vendingMachines.remove(vendingMachineName);
    }
}
