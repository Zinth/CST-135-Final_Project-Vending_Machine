/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import simulation.Customers;

/**
 *
 * @author Chris
 */
public class EventLog extends TextArea {
    private int customerNumber = 1;
    private int eventNumber = 0;

    public EventLog() {
        
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
        if(eventNumber == 0){
            this.setText(this.getText() + "-------- Customer: " + customerNumber + " --------\n");
        }
        eventNumber++;
        this.appendText(eventNumber + ": " + customer + event + "\n");
    }

    public void newCustomer() {
        customerNumber++;
        this.appendText("-------- Customer: " + customerNumber + " --------\n");
        
    }
    
    public void reset(){
        this.clear();
        this.eventNumber = 0;
        this.customerNumber = 1;
        
    }
}
