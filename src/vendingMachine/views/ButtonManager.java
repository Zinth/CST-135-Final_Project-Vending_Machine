/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @author James
 * @date 01/19/18
 *
 * @about Class displays all products on a grid for manager use.
 */
package vendingMachine.views;

import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import utilities.ServiceManager;

/**
 *
 * @author Chris
 */
public class ButtonManager {

    private ServiceManager serviceManager;

    //Buttons
    private CustomButtons btnDrink;
    private CustomButtons btnChip;
    private CustomButtons btnCandy;
    private CustomButtons btnGum;
    private CustomButtons btnPurchase;
    private CustomButtons btnSimPlay;
    private CustomButtons btnSimStop;
    private CustomButtons btnReset;
    private CustomButtons btnManagerMode;
    private Group customer_ui_group;
    private Group manager_ui_group;

    public ButtonManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        // --- Selection Buttons ---
        //Button for selecting Drink with picture
        btnDrink = new CustomButtons(serviceManager, false, "res/images/drinks.png", "Drinks", 100, 100);
        btnDrink.setOnAction(event -> {
            //Sort the inventory grid to show only drinks
            serviceManager.getInventoryGrid().setProductType("drink");
        });

        //Button for selecting Chips with picture
        btnChip = new CustomButtons(serviceManager, false, "res/images/chips.png", "Chips", 100, 100);
        btnChip.setOnAction(event -> {
            //Sort the inventory grid to show only chips
            serviceManager.getInventoryGrid().setProductType("chips");
        });

        //Button for selecting Snack with picture
        btnCandy = new CustomButtons(serviceManager, false, "res/images/candy.png", "Candy", 100, 100);
        btnCandy.setOnAction(event -> {
            //Sort the inventory grid to show only candy
            serviceManager.getInventoryGrid().setProductType("candy");
        });

        //Button for selecting Gum with picture
        btnGum = new CustomButtons(serviceManager, false, "res/images/gum.png", "Gums", 100, 100);
        btnGum.setOnAction(event -> {
            //Sort the inventory grid to show only gum
            serviceManager.getInventoryGrid().setProductType("gum");
        });

        // --- Purchase Button ---
        //Button for purchasing the product
        btnPurchase = new CustomButtons("Check-Out", e -> {
            serviceManager.getCart().checkOut();
            serviceManager.UpdateGui();
            if (!serviceManager.isCustomerQueMode()) {
                serviceManager.getALERT().showAlert("Your purchase was successfull!", 36, Color.BLACK);
            }
        });
        //Set btnPurchase properties
        btnPurchase.setGraphic(serviceManager.getTotalPrice());
        btnPurchase.setContentDisplay(ContentDisplay.TOP);

        // --- Simulation Buttons ---
        btnSimPlay = new CustomButtons(serviceManager, false, "res/images/play.png", "Play & Pause", 50, 50);
        btnSimPlay.setShape(new Circle(3));
        btnSimPlay.setOnAction((event) -> {
            serviceManager.getCustomerQueue().playSimulation();
        });

        btnSimStop = new CustomButtons(serviceManager, false, "res/images/stop.png", "Stop", 50, 50);
        btnSimStop.setShape(new Circle(3));
        btnSimStop.setOnAction((event) -> {
            serviceManager.getCustomerQueue().stopSimulation();
        });

        btnReset = new CustomButtons(serviceManager, true, "res/images/refresh.png", "Reset Items", 75, 75);
        btnReset.setShape(new Circle(3));
        btnReset.setOnAction(e -> {
            serviceManager.getIManager().resetProducts();
            serviceManager.UpdateGui();
        });

        btnManagerMode = new CustomButtons(serviceManager, false, "res/images/manager.png", "Manager Mode", 75, 75);
        btnManagerMode.setShape(new Circle(3));
        //btnManager action event for changing if manager grid is displayed or not
        btnManagerMode.setOnAction(event -> {
            if (serviceManager.isManagerMode()) {
                serviceManager.getRoot().setCenter(customer_ui_group);
                serviceManager.setManagerMode(false);
                btnReset.updateNode();
                serviceManager.UpdateGui();
            } else {
                // Else have scene contain managerVBox
                serviceManager.getLoginView().load();
            }
        });
    }

    public void finalizeManagerMode() {
        serviceManager.getRoot().setCenter(manager_ui_group);
        serviceManager.setManagerMode(true);
        btnReset.updateNode();
        serviceManager.UpdateGui();
    }

    // --- GETTERS & SETTERS ---
    public CustomButtons getBtnDrink() {
        return btnDrink;
    }

    public CustomButtons getBtnChip() {
        return btnChip;
    }

    public CustomButtons getBtnCandy() {
        return btnCandy;
    }

    public CustomButtons getBtnGum() {
        return btnGum;
    }

    public CustomButtons getBtnPurchase() {
        return btnPurchase;
    }

    public CustomButtons getBtnSimStop() {
        return btnSimStop;
    }

    public CustomButtons getBtnSimPlay() {
        return btnSimPlay;
    }

    public CustomButtons getBtnReset() {
        return btnReset;
    }

    public CustomButtons getBtnManagerMode() {
        return btnManagerMode;
    }

    public void setCustomerGroup(Group customer_ui_group) {
        this.customer_ui_group = customer_ui_group;
    }

    public void setManager_ui_group(Group manager_ui_group) {
        this.manager_ui_group = manager_ui_group;
    }

}
