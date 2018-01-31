/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui.panes;

import javafx.scene.control.TextArea;
import vendingMachine.classes.customers_simulation.Customers;

/**
 *
 * @author Chris
 */
public class EventLog extends TextArea{
    private int eventNumber = 0;
    
    /**
     * Example log line "1: Rob found Reeses's"
     * @param event
     * @param customer 
     */
    public void event(Customers customer, String event){
        eventNumber++;
       this.setText(this.getText() + eventNumber + ": "  + customer + event + "\n"); 
    }
}
