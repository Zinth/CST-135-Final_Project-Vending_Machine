/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.customers_simulation;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import vendingMachine.classes.ServiceManager;

/**
 *
 * @author Chris
 */
public class SimulatedActions {
    private ServiceManager serviceManager;
    private Random rand = new Random();
    private String itemName; 
    
    public SimulatedActions(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
        itemName = "";
    }
    
    public void playSimulation(Customers customer){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), e -> {
                    catagoryChange(customer);
                }), new KeyFrame(Duration.millis(1000), e -> {
                    selectItem(customer);
                }), new KeyFrame(Duration.millis(3000), e -> {
                    purchase(customer);
                })
        );
        timeline.play();
    }
    
    public void catagoryChange(Customers customer){
        switch(customer.getCatagory()){
            case "drink":
                buttonPress(serviceManager.getBtnManager().getBtnDrink());
                serviceManager.getEventLog().event(customer, " changed category to Drinks.");
                break;
            case "chips":
                buttonPress(serviceManager.getBtnManager().getBtnChip());
                serviceManager.getEventLog().event(customer, " changed category to Chips.");
                break;
            case "candy":
                buttonPress(serviceManager.getBtnManager().getBtnCandy());
                serviceManager.getEventLog().event(customer, " changed category to Candy.");
                break;
            case "gum":
                buttonPress(serviceManager.getBtnManager().getBtnGum());
                serviceManager.getEventLog().event(customer, " changed category to Gum.");
                break;
            default:
                serviceManager.getALERT().showAlert("SIMULATION ERROR:\n Category not found", 14, Color.RED);
        }
    }
    
    public int getItemIndex(String wantedProduct){
        int result = -1;
         for(int i = 0; i < serviceManager.getInventoryGrid().getChildren().size(); i++){
             String temp = serviceManager.getInventoryGrid().getChildren().get(i).toString();
             
             System.out.println("TEST: productName = |" + temp + "|  wantedProduct = |" + wantedProduct + "|");
             if(wantedProduct.equals(temp)){
                result = i;
             }
       }
         System.out.println("TEST: " + result);
         return result;
    }
    
    public void selectItem(Customers customer) {
        int index = getItemIndex(customer.getWantedItem());
        
        Node node = null;
        if (index >= 0) {
            node = serviceManager.getInventoryGrid().getChildren().get(index);
            itemName = node.toString();
            serviceManager.getEventLog().event(customer, " found " + itemName + ".");
            buttonPress(node);
            serviceManager.getEventLog().event(customer, " added " + itemName + " to the cart.");
        } else {
            int randomIndex = rand.nextInt(serviceManager.getInventoryGrid().getChildren().size());
            node = serviceManager.getInventoryGrid().getChildren().get(randomIndex);
            itemName = node.toString();
            serviceManager.getEventLog().event(customer, " couldn't find " + customer.getWantedItem() + ", but found " + itemName + ".");
            buttonPress(node);
            serviceManager.getEventLog().event(customer, " added " + itemName + " to the cart.");
            System.out.println("PRODUCT NOT FOUND");
        }
        serviceManager.getCustomerInfo().getProductPurchasedLabel().setText(itemName);
    }
    
    public void purchase(Customers customer){
        serviceManager.getTotalPrice().updateNode();
        serviceManager.getCustomerInfo().getPriceLabel().setText(serviceManager.getTotalPrice().getText());
        serviceManager.getEventLog().event(customer, " purchased " + itemName + " for a " + serviceManager.getTotalPrice().getText() + ".");
        buttonPress(serviceManager.getBtnManager().getBtnPurchase());
        
    }
    
    //Button Press Animation Timeline
    public void buttonPress(Node node) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500), e -> {
                    fireMouseEvent(node, MouseEvent.MOUSE_ENTERED, MouseButton.NONE);
                }), new KeyFrame(Duration.millis(750), e -> {
                    Event.fireEvent(node, e);
                    fireMouseEvent(node, MouseEvent.MOUSE_CLICKED, MouseButton.PRIMARY);
                }), new KeyFrame(Duration.millis(1000), e -> {
                    fireMouseEvent(node, MouseEvent.MOUSE_EXITED, MouseButton.NONE);
                }));
        timeline.play();
    }
    
    public void fireMouseEvent(Node node, EventType<? extends MouseEvent> eventType, MouseButton button){
        //This should fire the mouseClicked Event as if the mouse clicked it.
           Event.fireEvent(node, new MouseEvent(eventType, 0,
                0, 0, 0, button, 1, true, true, true, true,
                true, true, true, true, true, true, null)
           );
    }

    public String getItemName() {
        return itemName;
    }
    
    
}
