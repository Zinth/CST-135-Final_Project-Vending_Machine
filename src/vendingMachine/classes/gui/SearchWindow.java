/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingMachine.classes.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class SearchWindow extends Application {

    private TextField searchField;
    private Label productInfo;
    private Stage search;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        
        //box for holding the search nodes
        VBox searchBox = new VBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.getStyleClass().add("inventoryPane");
        
        //box for holding the result nodes
        VBox resultBox = new VBox();
        resultBox.setAlignment(Pos.CENTER);

        //Lable for instructing user.
        Label resultLabel = new Label("Result:");
        //Label to hold product info
        productInfo = new Label();
        //Lable for instructing user.
        Label searchLabel = new Label("Please enter a product name to search for and press Search:");
        //TextField for getting user input
        searchField = new TextField();

        //Button for doing the seach
        CustomButtons searchButton = new CustomButtons("Search", e -> {
            //Get the Input from user 
            String input = searchField.getText().toLowerCase();
            
            //TODO search(input)

        });
        
        //Add nodes to panes
        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);
        resultBox.getChildren().addAll(resultLabel, productInfo);
        root.getChildren().addAll(searchBox, resultBox);

        //Scene and Stage setup
        Scene scene = new Scene(root);
        scene.getStylesheets().add("vendingMachine.css");
        search = new Stage();
        search.setScene(scene);
        search.initModality(Modality.APPLICATION_MODAL); // make sure you can only click this window. 
        search.setScene(scene);
        search.setAlwaysOnTop(true); // Keeps alert window on top
    }
    
    public void showWinow(){
        search.show();
    }
 

}
