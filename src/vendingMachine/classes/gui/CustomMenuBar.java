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
    private MenuItem productSearch;
    private SearchWindow search = new SearchWindow();
    
    
    public CustomMenuBar(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        //Create fileMenus
        Menu fileMenu = new Menu("_File");

        //Creat fileMenu Items
        purchaseOrder = new MenuItem("_Purchase Order...");
        export = new MenuItem("_Export Invenotry...");
        productSearch = new MenuItem("Product _Search");
        MenuItem exit = new MenuItem("E_xit");

        //Set action Events to fileMenu items.
        purchaseOrder.setOnAction(e -> {
            //TODO: add creating of purchase order CSV
        });

        export.setOnAction(e -> {
            //TODO: add creating of export CSV
        });
        
        productSearch.setOnAction((event) -> {
            //Launch Search Window
            search.showWinow();;
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
                serviceManager.getALERT().showAlert("Catagory Buttons:\n Change the visable items in the vending machine.\n\n"
                        + "Item Selection:\n Click on the item you wish to puchase to add it to the cart.\n\n"
                        + "More Info:\n Hover over a item in the vending machine to get more information.\n\n"
                        + "Remove Items:\n Remove Items from the cart by clicking on them.\n\n"
                        + "Purchase Items:\n Click the Check-Out button to finalize your purchase.\n\n"
                        + "Manager Mode:\n Click the Keyhole button to go to manager mode.", 16, Color.BLACK);
            } else {
                 serviceManager.getALERT().showAlert("Machine Buttons:\n Changes the vending machine you want to manage .\n\n"
                        + "Increase Stock:\n Click on the + button to increase stock of that product.\n\n"
                        + "Decreace Stock:\n Click on the - button to decrease stock of that product\n\n"
                        + "Reset Stock:\n Click the refresh button to reset all stock to default levels.\n\n"
                        + "Purchase Orders:\n Go to File and click Purchase Order to generate purchase orders.\n\n"
                        + "Export Inventory:\n Go to File and click Export to export the current inventory of a vending machine.\n\n"
                        + "Exit Manager Mode:\n Click the Keyhole button to leave to manager mode.", 16, Color.BLACK);
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
