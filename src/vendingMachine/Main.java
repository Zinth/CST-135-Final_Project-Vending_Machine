/**
 * @project Final Project - Vending Machine
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Jesus Leon
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 1/20/28
 *
 * @about the main class.
 */
package vendingMachine;

import java.text.NumberFormat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vendingMachine.classes.Cart;
import vendingMachine.classes.InventoryManagement;

import vendingMachine.classes.gui.CustomButtons;
import vendingMachine.classes.gui.grids.CartGrid;
import vendingMachine.classes.gui.grids.InventoryGrid;
import vendingMachine.classes.gui.grids.ManagerGrid;


public class Main extends Application{

    // Dispencer class creation
    private InventoryManagement iManager = new InventoryManagement();
    private Cart cart = new Cart(iManager);
    private Insets padding = new Insets(5,5,5,5);
    private boolean managerMode = false;
    private String borderedItems = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 6;\n"
            + "-fx-border-width: 2;\n"
            + "-fx-border-style: solid;\n";
    private final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();

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
        mainPane.setPrefSize(1020,800);

        //Create pane that will hold all center content
        HBox mainHBox = new HBox();
        mainHBox.setPadding(padding);

        //Create hbox to hold product selection buttons
        HBox productSelectionHBox = new HBox();
        productSelectionHBox.setPadding(padding);

        //Create hbox to hold cart update button and total cost label
        HBox cartHBox = new HBox();
        cartHBox.setPadding(padding);

        // vbox to hold Inventory Grid and Selection Buttons
        VBox inventoryVBox = new VBox();
        inventoryVBox.setPadding(padding);
        inventoryVBox.setAlignment(Pos.CENTER);
        inventoryVBox.setMaxWidth(510);
        inventoryVBox.setMinWidth(510);

        // vbox to hold grid
        VBox managerVBox = new VBox();
        managerVBox.setPadding(padding);
        managerVBox.setAlignment(Pos.CENTER);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setAlignment(Pos.CENTER);
        cartVBox.setPadding(padding);
        cartVBox.setMaxWidth(510);
        cartVBox.setMinWidth(510);

        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);

        //Text Labels
        Text inventoryLabel = new Text("Vending Machine");
        Text cartLabel = new Text("Cart");
        Text costLabel = new Text("Total:" + FORMATTER.format(cart.getTotalCost()));

        //Grid for InventoryPane's
        CartGrid cartGrid = new CartGrid(cart, 6);
        InventoryGrid inventoryGrid = new InventoryGrid(iManager.getProductList(), 6, cartGrid, 410, 0);
        ManagerGrid managerGrid = new ManagerGrid(iManager, 6);



        //Button for selecting Drink with picture
        Button btnDrink = new CustomButtons("res/images/drink.jpg", "Drinks");
        btnDrink.setOnAction(event -> {
         inventoryGrid.sortProductGrid("drink");
        });

        //Button for selecting Chips with picture
        Button btnChip = new CustomButtons("res/images/chips.jpg", "Chips");
        btnChip.setOnAction(event -> {
            inventoryGrid.sortProductGrid("chips");
        });

        //Button for selecting Snack with picture
        Button btnCandy = new CustomButtons("res/images/candy.jpg", "Candy");
        btnCandy.setOnAction(event -> {
            inventoryGrid.sortProductGrid("candy");
        });

        //Button for selecting Gum with picture
        Button btnGum = new CustomButtons("res/images/gum.jpg", "Gums");
        btnGum.setOnAction(event -> {
            inventoryGrid.sortProductGrid("gum");
        });

        //Button to display Manager Grid
        Button btnManager = new CustomButtons("res/images/manager.jpg", "Manager Mode");


        //Add Nodes to panes
        mainHBox.getChildren().addAll(inventoryVBox,  cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        inventoryVBox.getChildren().addAll(inventoryLabel, productSelectionHBox, inventoryGrid);
        managerVBox.getChildren().addAll(managerGrid);
        cartHBox.getChildren().addAll();
        cartVBox.getChildren().addAll(cartLabel, cartGrid, costLabel);
        bottomHBox.getChildren().add(btnManager);

        //Set default UI Group for mainPane
        mainPane.setCenter(mainHBox);
        mainPane.setBottom(bottomHBox);



        //Set Scene
        Scene scene = new Scene(mainPane, 1020, 800);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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
