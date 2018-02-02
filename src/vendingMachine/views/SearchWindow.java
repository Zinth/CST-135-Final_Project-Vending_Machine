/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.ServiceManager;
import vendingMachine.products.Product;

/**
 *
 * @author Chris
 */
public class SearchWindow extends Application {

    private TextField searchField;
    private Label productInfo;
    private Stage search = new Stage();
    private ServiceManager serviceManager;

    @Override
    public void start(Stage search) {
        VBox root = new VBox();
        root.getStyleClass().add("main");

        //box for holding the search nodes
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getStyleClass().add("inventoryVBox");

        //box for holding the result nodes
        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);

        //Lable for instructing user.
        Label resultLabel = new Label("Result:");
        resultLabel.getStyleClass().add("labelBlack");
        //Label to hold product info
        productInfo = new Label("Your product will apear here.");
        productInfo.getStyleClass().add("labelBlack");
        //Lable for instructing user.
        Label searchLabel = new Label("Please enter a product name to search for and press Search:");
        searchLabel.getStyleClass().add("labelBlack");
        //TextField for getting user input
        searchField = new TextField();

        //Button for doing the seach
        CustomButtons searchButton = new CustomButtons("Search", e -> {
            //Get the Input from user 
            String input = searchField.getText().toLowerCase();

            //TODO search(input)
            HashMap<String, ArrayList<Product>> searchResults = serviceManager.getgIManager().recursiveSearchByName(input);
            if (!searchResults.isEmpty()) {
                productInfo.setText("Search Results for "+input);
                Iterator it = searchResults.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    String vendingMachineName = (String) pair.getKey();
                    ArrayList<Product> products = (ArrayList<Product>) pair.getValue();
                    for (Product product : products) {
                        productInfo.setText(productInfo.getText()+ "\n Found in "+vendingMachineName+ " with a stock of "+product.getQuantity());
                    }
                    it.remove(); // avoids a ConcurrentModificationException
                }
                search.sizeToScene();
            } else {
                serviceManager.getALERT().showAlert("Product name " + input + " was not found", 18, Color.RED);
            }
        });

        //Add nodes to panes
        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);
        resultBox.getChildren().addAll(resultLabel, productInfo);
        root.getChildren().addAll(searchBox, resultBox);

        //Scene and Stage setup
        Scene scene = new Scene(root);
        scene.getStylesheets().add("vendingMachine.css");
        search.setScene(scene);
        search.initModality(Modality.APPLICATION_MODAL); // make sure you can only click this window. 
        search.setScene(scene);
        search.showAndWait();

    }

    public void showWinow(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
        start(search);

    }

}
