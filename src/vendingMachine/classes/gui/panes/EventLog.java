/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui.panes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import vendingMachine.classes.customers_simulation.Customers;

/**
 *
 * @author Chris
 */
public class EventLog extends TextArea {

    private int eventNumber = 0;
    private int customerNumber = 1;

    public EventLog() {
        this.setText(this.getText() + "-------- Customer: " + customerNumber + " --------\n");
        this.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                    Object newValue) {
                setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }});
    }

    /**
     * Example log line "1: Rob found Reeses's"
     *
     * @param event
     * @param customer
     */
    public void event(Customers customer, String event) {
        eventNumber++;
        this.appendText(eventNumber + ": " + customer + event + "\n");
    }

    public void newCustomer() {
        customerNumber++;
        this.appendText("-------- Customer: " + customerNumber + " --------\n");
        
    }
}
