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
import javafx.util.Duration;
import vendingMachine.classes.ServiceManager;

/**
 *
 * @author Chris
 */
public class SimulatedActions {
    private ServiceManager serviceManager;
    private Random rand = new Random();
    
    public SimulatedActions(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
    }
    
    public void catagoryChange(String category){
        serviceManager.getInventoryGrid().setProductType(category);
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
        //customer change Catagory
        catagoryChange(customer.getCatagory());
        serviceManager.getEventLog().event(customer, " changed category to " + customer.getCatagory() + ".");

        int index = getItemIndex(customer.getWantedItem());
        if (index >= 0) {

            Node node = serviceManager.getInventoryGrid().getChildren().get(index);
            serviceManager.getEventLog().event(customer, " found " + customer.getWantedItem() + ".");
            buttonPress(node);
            
            serviceManager.getEventLog().event(customer, " added " + customer.getWantedItem() + " to the cart.");

        } else {
            System.out.println("PRODUCT NOT FOUND");
        }

    }
    
    //Button Press Animation Timeline
    public void buttonPress(Node node) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500), e -> {
                    fireMouseEvent(node, MouseEvent.MOUSE_ENTERED, MouseButton.NONE);
                }), new KeyFrame(Duration.millis(750), e -> {
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
}
