/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vendingMachine.classes.ServiceManager;

public class PurchaseOrder extends Application {

    ServiceManager serviceManager;
    String currentVendingMachineSelection = "";

    public void load(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        Stage poStage = new Stage();
        ComboBox switchMachines = new MachineComboBox(serviceManager, new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                currentVendingMachineSelection = newValue;
            }
        });
        Button createPO = new CustomButtons("Create PO", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!currentVendingMachineSelection.isEmpty()){
                    serviceManager.getRestock().createPO(currentVendingMachineSelection);
                }else{
                    serviceManager.getALERT().showAlert("No Vending Machine Selected", 16, Color.RED);
                }
            }
        });
        Button createAllPOs = new CustomButtons("Create All POs", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                serviceManager.getRestock().createAllPOs();
            }
        });
        HBox actions = new HBox(createPO, createAllPOs);
        StackPane root = new StackPane();
        root.getStyleClass().add("main");
        root.getChildren().addAll(switchMachines, actions);

        Scene scene = new Scene(root, 300, 250);

        poStage.setTitle("Purchase Orders");
        poStage.setScene(scene);
        poStage.show();
        scene.getStylesheets().add("vendingMachine.css");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
