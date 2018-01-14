/**
 * @project Final Project - Vending Machine
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Jesus Leon
 * @author Robert Wayde
 * @teacher Dr. Lively
 * @date 1/13/28
 */
package vendingMachine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendingMachine.classes.Cart;
import vendingMachine.classes.Dispenser;
import vendingMachine.classes.gui.ProductGrid;
import vendingMachine.classes.gui.ProductPane;
import vendingMachine.classes.products.*;

public class Main extends Application{

    // Dispencer class creation
    private Dispenser dispenser = new Dispenser(20.00, 0);
    private Cart cart = new Cart();
    private Insets padding = new Insets(5,5,5,5);
    private String borderedItems = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 6;\n"
            + "-fx-border-width: 2;\n"
            + "-fx-border-style: solid;\n";


    /**
     * Overrided start method
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create and Fromat the mainPane
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(padding);
        mainPane.setPrefSize(300,300);

        //Create pane that will hold all center content
        HBox mainHBox = new HBox();
        mainHBox.setPadding(padding);

        //Create hbox to hold product selection buttons
        HBox productSelectionHBox = new HBox();
        productSelectionHBox.setPadding(padding);

        //Create hbox to hold cart update button and total cost label
        HBox cartHBox = new HBox();
        cartHBox.setPadding(padding);

        // vbox to hold grid
        VBox productVBox = new VBox();
        productVBox.setPadding(padding);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setPadding(padding);

        //Label for holding the current total price in the cart
        Label totalPrice = new Label("Total Cost: $" + String.valueOf(cart.getTotalDue()));


        //Grid pane for the products
        ProductGrid productGrid = new ProductGrid(dispenser.getProductList(), 3, cart);
        ProductGrid cartGrid = new ProductGrid(cart.getPurchaseList(),3, cart);

        //Button for selecting Drink
        Button btnDrink = new Button("Show Just Drinks");
        btnDrink.setOnAction(event -> {
         productGrid.sortProductGrid("drink", cart);
        });

        //Button for selecting Chips
        Button btnChip = new Button("Show Just Chips");
        btnChip.setOnAction(event -> {
            productGrid.sortProductGrid("chips", cart);
        });

        //Button for selecting Snack
        Button btnCandy = new Button("Show Just Candy");
        btnCandy.setOnAction(event -> {
            productGrid.sortProductGrid("candy", cart);
        });
        //Button for selecting Gum
        Button btnGum = new Button("Show Just Gum");
        btnGum.setOnAction(event -> {
            productGrid.sortProductGrid("gum", cart);
        });

        Button btnCartupdate = new Button("Update Cart");
        btnCartupdate.setOnAction(event -> {
            cartGrid.sortProductGrid("default", cart);
            totalPrice.setText("Total Cost: $" + String.valueOf(cart.getTotalDue()));
        });




        //Add Nodes to panes
        mainPane.setCenter(mainHBox);
        mainHBox.getChildren().addAll(productVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        productVBox.getChildren().addAll(productSelectionHBox, productGrid);
        cartHBox.getChildren().addAll(btnCartupdate, totalPrice);
        cartVBox.getChildren().addAll(cartHBox,cartGrid);


        //Set Scene
        Scene scene = new Scene(mainPane, 800, 600);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Main Method
     * @param args
     */
    public static void Main(String[] args){
        launch(args);
    }
}