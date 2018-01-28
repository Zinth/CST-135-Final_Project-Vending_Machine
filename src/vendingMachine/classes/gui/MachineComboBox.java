/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import vendingMachine.classes.ServiceManager;

/**
 *
 * @author jamesray
 */
public class MachineComboBox extends ComboBox{

    public MachineComboBox(ServiceManager serviceManager) {
        fillComboBox(serviceManager);
        // Combobox on change change vending machines
        valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String oldValue, String newValue) { 
                    serviceManager.getVmManager().switchVendingMachines(newValue);
                }   
        });
    }

    public MachineComboBox(ServiceManager serviceManager, ChangeListener<String> changeListener) {
        fillComboBox(serviceManager);
        valueProperty().addListener(changeListener);
    }
    
    private void fillComboBox(ServiceManager serviceManager){
        //Loop through vendingMachines and add them to comboBox
        for (String vendingMachineName : serviceManager.getVmManager().getVendingMachineNames()) {
            getItems().add(vendingMachineName); 
        }
    }
}
