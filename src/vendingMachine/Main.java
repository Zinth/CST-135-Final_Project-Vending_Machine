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
import javafx.scene.Group;
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
    private BorderPane root;
    private Group customerUI = new Group();
    private Group managerUI= new Group();
    private CartGrid cartGrid;

    /**
     * Main Method
     * @param args
     */
    public static void Main(String[] args){
        launch(args);
    }

    /**
     * Overrided start method
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //-- Create Panes --
        //Create the root pane and format it.
        root = new BorderPane();
        root.setPadding(padding);
        root.setPrefSize(1020,800);

        //Create the Manager VBox to hold the managerGrid
        VBox managerVBox = new VBox();
        managerVBox.setPadding(padding);
        managerVBox.setAlignment(Pos.CENTER);

        //Create the bottomHBox to hold the manager Button
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);

        // --- Create Grids ---
        //Create the managerGrid that will display all products for sale
        ManagerGrid managerGrid = new ManagerGrid(iManager, 8);

        // --- Create Buttons ---
        Button resetButton = new CustomButtons("res/images/refresh.png", "Reset Items");
        resetButton.setOnAction(e -> {
            iManager.resetProducts();
            managerGrid.fillGrid();
            cartGrid.fillGrid();
        });
        //Button to display Manager Grid
        Button btnManager = new CustomButtons("res/images/manager.png", "Manager Mode");
        //btnManager action event for changing if manager grid is displayed or not
        btnManager.setOnAction(event -> {
            if(managerMode){
                root.setCenter(customerUI);
                managerMode = false;
                bottomHBox.getChildren().remove(resetButton);
            }else{
                // Else have scene contain managerVBox
                root.setCenter(managerUI);
                managerMode = true;
                bottomHBox.getChildren().add(resetButton);
            }
            managerGrid.fillGrid();
        });
        // --- Nodes to Groups ---
        //Add the createCustomerUI to customerUI group
        customerUI.getChildren().addAll(createCustomerUI());
        //Add managerVBox to managerUI group
        managerUI.getChildren().addAll(managerVBox);

        // --- Add Nodes to Panes ---
        //Add managerGrid to managerVBox
        managerVBox.getChildren().addAll(managerGrid);
        //Add btnManager to bottomHBox
        bottomHBox.getChildren().addAll(btnManager);
        //Set default UI Group for root Pane
        root.setCenter(customerUI);
        //Add the bottomHBox to the bottom of root.
        root.setBottom(bottomHBox);

        // --- Standard JavaFX ---
        //Set up default Scene
        Scene scene = new Scene(root, 1020, 800);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Prevent user resizing
        primaryStage.show();
    }

    /**
     * Method to create the customerHBox
     * @return customerHBox
     */
    public HBox createCustomerUI(){
        // --- Create and Formate Panes ---
        //Create pane that will hold all center content
        HBox customerHBox = new HBox();
        customerHBox.setPadding(padding);

        //Create hbox to hold product selection buttons
        HBox productSelectionHBox = new HBox();
        productSelectionHBox.setPadding(padding);

        // vbox to hold Inventory Grid and Selection Buttons
        VBox inventoryVBox = new VBox();
        inventoryVBox.setPadding(padding);
        inventoryVBox.setAlignment(Pos.CENTER);
        inventoryVBox.setMaxWidth(510);
        inventoryVBox.setMinWidth(510);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setAlignment(Pos.CENTER);
        cartVBox.setPadding(padding);
        cartVBox.setMaxWidth(510);
        cartVBox.setMinWidth(510);

        // --- Create Labels ---
        //Text Labels
        Text inventoryLabel = new Text("Vending Machine");
        Text cartLabel = new Text("Cart");
        Text costLabel = new Text("Total:" + FORMATTER.format(cart.getTotalCost()));

        // --- Create Grids ---
        cartGrid = new CartGrid(cart, 3, costLabel);
        InventoryGrid inventoryGrid = new InventoryGrid(iManager.getProductList(), 3, cartGrid, 410, 0);

        // --- Selection Buttons ---
        //Button for selecting Drink with picture
        Button btnDrink = new CustomButtons("res/images/drinks.png", "Drinks");
        btnDrink.setOnAction(event -> {
            //Sort the inventory grid to show only drinks
            inventoryGrid.sortProductGrid("drink");
        });

        //Button for selecting Chips with picture
        Button btnChip = new CustomButtons("res/images/chips.png", "Chips");
        btnChip.setOnAction(event -> {
            //Sort the inventory grid to show only chips
            inventoryGrid.sortProductGrid("chips");
        });

        //Button for selecting Snack with picture
        Button btnCandy = new CustomButtons("res/images/candy.png", "Candy");
        btnCandy.setOnAction(event -> {
            //Sort the inventory grid to show only candy
            inventoryGrid.sortProductGrid("candy");
        });

        //Button for selecting Gum with picture
        Button btnGum = new CustomButtons("res/images/gum.png", "Gums");
        btnGum.setOnAction(event -> {
            //Sort the inventory grid to show only gum
            inventoryGrid.sortProductGrid("gum");
        });

        // --- Fill Panes ---
        //Add Nodes to panes
        customerHBox.getChildren().addAll( inventoryVBox,  cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        inventoryVBox.getChildren().addAll(inventoryLabel, productSelectionHBox, inventoryGrid);
        cartVBox.getChildren().addAll(cartLabel, cartGrid, costLabel);

        // Return customerHBox Node
        return customerHBox;
    }



}
