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
package vendingMachine.classes.gui;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import vendingMachine.classes.ServiceManager;

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
    
    
    public ButtonManager(ServiceManager serviceManager){
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
        btnCandy = new CustomButtons(serviceManager, false, "res/images/candy.png", "Candy", 100 ,100);
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
            if(!serviceManager.isCustomerQueMode()){
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
        
        btnSimStop = new CustomButtons(serviceManager, false, "res/images/stop.png", "Stop", 50 ,50);
        btnSimStop.setShape(new Circle(3));
        btnSimStop.setOnAction((event) -> {
          serviceManager.getCustomerQueue().stopSimulation();
        });
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
    
    
}
