/**
 * @project VendingMachine
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/22/18
 *
 * @about This class manages the different services that will be used allowing
 * them to easily talk to each other.
 */
package vendingMachine.classes;

import java.text.NumberFormat;
import java.util.ArrayList;
import vendingMachine.classes.gui.TotalPrice;
import vendingMachine.classes.gui.grids.CartGrid;
import vendingMachine.classes.gui.grids.InventoryGrid;
import vendingMachine.classes.gui.grids.ManagerGrid;
import vendingMachine.interfaces.UpdatableGUINode;

public final class ServiceManager {

    private final InventoryManagement iManager = new InventoryManagement();
    private final Cart cart = new Cart();
    private final CartGrid cartGrid;
    private final InventoryGrid inventoryGrid;
    private final ManagerGrid managerGrid;
    private final ArrayList<UpdatableGUINode> updatableGuiNodes = new ArrayList<>();
    private final static NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    private final TotalPrice totalPrice;
    private final Restock restock;
    private final VendingMachineManager vmManager;

    public ServiceManager() {
        totalPrice = new TotalPrice(this);
        cartGrid = new CartGrid(this, 6);
        inventoryGrid = new InventoryGrid(this, 6);
        managerGrid = new ManagerGrid(this, 6);
        restock = new Restock(this);
        vmManager = new VendingMachineManager(this);
        vmManager.addVendingMachine("Vending Machine 1", iManager.getProducts());
        updatableGuiNodes.add(totalPrice);
        updatableGuiNodes.add(cartGrid);
        updatableGuiNodes.add(inventoryGrid);
        updatableGuiNodes.add(managerGrid);
        restock.createAllPOs();
    }

    /**
     * @return InventoryManagement
     */
    public InventoryManagement getIManager() {
        return iManager;
    }

    /**
     * @return Cart
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @return CartGrid
     */
    public CartGrid getCartGrid() {
        return cartGrid;
    }

    /**
     * @return InventoryGrid
     */
    public InventoryGrid getInventoryGrid() {
        return inventoryGrid;
    }

    /**
     * @return ManagerGrid
     */
    public ManagerGrid getManagerGrid() {
        return managerGrid;
    }

    /**
     * @return VendingMachineManager
     */
    public VendingMachineManager getVmManager() {
        return vmManager;
    }

    /**
     * Updates all Gui elements
     */
    public void UpdateGui() {
        for (UpdatableGUINode node : updatableGuiNodes) {
            node.updateNode();
        }
    }

    /**
     * @param price
     * @return String
     */
    public String formatPrice(double price) {
        return FORMATTER.format(price);
    }

    /**
     * @return TotalPrice
     */
    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param price
     * @return String
     */
    public static String formatPriceStatic(double price){
        return FORMATTER.format(price);
    }

}
