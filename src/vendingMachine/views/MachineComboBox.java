/**
 * @project Milestone6
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/27/18
 *
 * @about This class creates Panes of products for the manager.
 */
package vendingMachine.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import utilities.ServiceManager;

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
