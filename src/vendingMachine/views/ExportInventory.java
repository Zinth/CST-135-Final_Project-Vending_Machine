/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.views;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.ServiceManager;

public class ExportInventory extends Application {

    ServiceManager serviceManager;
    String currentVendingMachineSelection = "";

    public void load(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        Stage poStage = new Stage();
        //ComboBox
        ComboBox switchMachines = new MachineComboBox(serviceManager, new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                currentVendingMachineSelection = newValue;
            }
        });
        
        //Buttons
        Button createPO = new CustomButtons("Export", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!currentVendingMachineSelection.isEmpty()){
                    serviceManager.getgIManager().exportToCsv(currentVendingMachineSelection);
                }else{
                    serviceManager.getALERT().showAlert("No Vending Machine Selected", 16, Color.RED);
                }
            }
        });
        Button createAllPOs = new CustomButtons("Export All", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                serviceManager.getgIManager().exportAll();
            }
        });
        
        //Lable for isntructions
        Label info1 = new Label("Select a vending machine:");
        info1.getStyleClass().add("labelBlack");
        Label info2 = new Label("Select an action:");
        info2.getStyleClass().add("labelBlack");
        
        //Action HBox to hold Buttons
        HBox actions = new HBox(createPO, createAllPOs);
        actions.setAlignment(Pos.CENTER);
        
        
        VBox itemBox = new VBox();
        itemBox.setSpacing(5);
        itemBox.setAlignment(Pos.CENTER);
        
        StackPane root = new StackPane();
        root.getStyleClass().add("main");
        
        //Add Nodes to Panes
        itemBox.getChildren().addAll(info1, switchMachines, info2, actions);
        root.getChildren().addAll(itemBox);

        Scene scene = new Scene(root, 300, 250);

        poStage.setTitle("Export Inventory");
        poStage.setScene(scene);
        poStage.show();
        scene.getStylesheets().add("vendingMachine.css");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
