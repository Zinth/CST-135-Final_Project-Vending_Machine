/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import vendingMachine.classes.ServiceManager;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class CustomMenuBar extends MenuBar  implements UpdatableGUINode{
    
    private ServiceManager serviceManager;
    private MenuItem purchaseOrder;
    private MenuItem export;
    
    
    public CustomMenuBar(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        //Create fileMenus
        Menu fileMenu = new Menu("_File");

        //Creat fileMenu Items
        purchaseOrder = new MenuItem("Create _Purchase Order...");
        export = new MenuItem("_Export Invenotry...");
        MenuItem exit = new MenuItem("E_xit");

        //Set action Events to fileMenu items.
        purchaseOrder.setOnAction(e -> {
            //TODO: add creating of purchase order CSV
        });

        export.setOnAction(e -> {
            //TODO: add creating of export CSV
        });

        exit.setOnAction(e -> {
            //Exit the Program. 
            Platform.exit();
        });

        //Add File Menu items to fileMenu
        fileMenu.getItems().addAll(purchaseOrder, new SeparatorMenuItem(), export, new SeparatorMenuItem(), exit);

        //Create helpMenu
        Menu helpMenu = new Menu("_Help");

        //Creat helpMenu Items
        MenuItem usage = new MenuItem("_How to...");
        MenuItem about = new MenuItem("_About");

        //Set action Events for helpMenu items
        usage.setOnAction((event) -> {
            if (!serviceManager.isManagerMode()) {
                //TODO: add a pop window with image of Customer GUI control functionality
            } else {
                //TODO: add a pop window with image of Manager GUI control functionality
            }
        });

        about.setOnAction(e -> {
            //Pop up an alert window with the following information
            serviceManager.getALERT().showAlert("Final Project: Vending Machine\n"
                    + "Course: CST-135\n"
                    + "Teacher: Dr. Lively\n"
                    + "___________________\n"
                    + "Programmers:\n"
                    + "James Ray\n"
                    + "Christopher Hyde\n"
                    + "Robert Wade\n"
                    + "___________________\n"
                    + "About:\n"
                    + "An application to simulate a digital vending machine.", 24, Color.BLACK);
        });

        //Add Help Menu items to helpMenu
        helpMenu.getItems().addAll(usage, about);

        //Add items to the menuBar
        this.getMenus().addAll(fileMenu, helpMenu);

        //run updateNode
        updateNode();

    }
    
  
    
    /**
     * Update manager mode. 
     */
    @Override
    public void updateNode() {
        //Determin if fileMenu items are enables or not
        if(serviceManager.isManagerMode()){
            purchaseOrder.setDisable(false);
            export.setDisable(false);
        }else{
            purchaseOrder.setDisable(true);
            export.setDisable(true);
        }
    }
    
    
    
}
