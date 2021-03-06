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
package utilities;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import login.views.LoginView;
import vendingMachine.classes.Cart;
import vendingMachine.classes.Global_InventoryManagement;
import vendingMachine.classes.InventoryManagement;
import vendingMachine.classes.Restock;
import vendingMachine.classes.VendingMachineManager;
import simulation.ProcessCustomerQueue;
import vendingMachine.views.AlertWindow;
import vendingMachine.views.ButtonManager;
import vendingMachine.views.CustomMenuBar;
import vendingMachine.views.TotalPrice;
import vendingMachine.views.grids.CartGrid;
import vendingMachine.views.grids.InventoryGrid;
import vendingMachine.views.grids.ManagerGrid;
import vendingMachine.views.panes.CustomerInfoPane;
import vendingMachine.views.panes.DisplayCustomer;
import simulation.EventLog;
import vendingMachine.interfaces.UpdatableGUINode;

public final class ServiceManager {

    private final BorderPane root;
    private final InventoryManagement iManager = new InventoryManagement();
    private final Cart cart = new Cart();
    private final CartGrid cartGrid;
    private final InventoryGrid inventoryGrid;
    private final ManagerGrid managerGrid;
    private final CustomMenuBar menuBar;
    private final ArrayList<UpdatableGUINode> updatableGuiNodes = new ArrayList<>();
    private final static NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
    private final TotalPrice totalPrice;
    private final Restock restock;
    private final VendingMachineManager vmManager;
    private final AlertWindow ALERT = new AlertWindow();
    private final Global_InventoryManagement gIManager;
    private final ButtonManager btnManager;
    private final LoginView loginView = new LoginView();
    
    
    //CustomerQue
    private final DisplayCustomer customerLine;
    private final CustomerInfoPane customerInfo;
    private final EventLog eventLog = new EventLog();
    private final ProcessCustomerQueue customerQueue = new ProcessCustomerQueue(this,"testQue.csv");
    
    //none constants
    private boolean managerMode = false;
    private boolean customerQueMode = false;

    public ServiceManager(BorderPane root) {
        this.root = root;
        btnManager = new ButtonManager(this);
        totalPrice = new TotalPrice(this);
        cartGrid = new CartGrid(this, 6);
        inventoryGrid = new InventoryGrid(this, 6);
        managerGrid = new ManagerGrid(this, 6);
        restock = new Restock(this);
        menuBar = new CustomMenuBar(this);
        customerLine = new DisplayCustomer(this);
        customerInfo = new CustomerInfoPane(this);
        loginView.setServiceManager(this);
        vmManager = new VendingMachineManager(this);
        vmManager.addVendingMachine("Default");
        vmManager.getVendingMachine("Default").addAll(iManager.getProducts());
        gIManager = new Global_InventoryManagement(this, iManager.getProducts());
        File folder = new File("src/res/input");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith("inventory.csv")) {
                gIManager.csvInventoryImport(listOfFiles[i].getName());
            }

        }
        vmManager.switchVendingMachines("Purple Group");
        updatableGuiNodes.add(totalPrice);
        updatableGuiNodes.add(cartGrid);
        updatableGuiNodes.add(inventoryGrid);
        updatableGuiNodes.add(managerGrid);
        updatableGuiNodes.add(menuBar);
        updatableGuiNodes.add(customerLine);
        updatableGuiNodes.add(customerInfo);
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

    public void UpdateCartGui() {
        cartGrid.updateNode();
        totalPrice.updateNode();
    }

    public void UpdateInventoryGui() {
        inventoryGrid.updateNode();
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
    public static String formatPriceStatic(double price) {
        return FORMATTER.format(price);
    }

    /**
     * @return
     */
    public CustomMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * @return
     */
    public AlertWindow getALERT() {
        return ALERT;
    }

    /**
     * @return boolean
     */
    public boolean isManagerMode() {
        return managerMode;
    }

    /**
     * @param managerMode
     */
    public void setManagerMode(boolean managerMode) {
        this.managerMode = managerMode;
        this.menuBar.updateNode();
        this.customerLine.updateVisible();
        this.customerInfo.updateVisible();
    }

    /**
     * @return  restock
     */
    public Restock getRestock() {
        return restock;
    }

    /**
     * @return gIManager
     */
    
    public Global_InventoryManagement getgIManager() {
        return gIManager;
    }

    public DisplayCustomer getCustomerLine() {
        return customerLine;
    }
    
    /**
     * @return 
     */
    public boolean isCustomerQueMode() {
        return customerQueMode;
    }
    
    /**
     * @param customerQueMode 
     */
    public void setCustomerQueMode(boolean customerQueMode) {
        this.customerQueMode = customerQueMode;
        this.customerLine.updateVisible();
        this.customerInfo.updateVisible();
    }
    
    /**
     * @return customerQueue 
     */
    public ProcessCustomerQueue getCustomerQueue() {
        return customerQueue;
    }

    /**
     * @return customerInfo 
     */
    public CustomerInfoPane getCustomerInfo() {
        return customerInfo;
    }

    public EventLog getEventLog() {
        return eventLog;
    }
    
    /**
     * @return btnManager 
     */
    public ButtonManager getBtnManager() {
        return btnManager;
    }

    public LoginView getLoginView() {
        return loginView;
    }    

    public BorderPane getRoot() {
        return root;
    }
}
