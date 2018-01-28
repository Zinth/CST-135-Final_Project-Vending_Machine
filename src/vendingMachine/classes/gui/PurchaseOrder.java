/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vendingMachine.classes.ServiceManager;

public class PurchaseOrder extends Application {
    
    public void load(ServiceManager serviceManager) {
        Stage poStage = new Stage();
        ComboBox switchMachines = new MachineComboBox(serviceManager); // change the arguement to reflect number of machines
        StackPane root = new StackPane();
        root.getStyleClass().add("main");
//        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        poStage.setTitle("Hello World!");
        poStage.setScene(scene);
        poStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }

}
