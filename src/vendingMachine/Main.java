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
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.gui.CustomButtons;

public class Main extends Application {

    // Dispencer class creation
    
    private final Insets PADDING = new Insets(5, 5, 5, 5);
    private boolean managerMode = false;
    private BorderPane root;
    private final Group CUSTOMER_UI_GROUP = new Group();
    private final Group MANAGER_UI_GROUP = new Group();
    private ServiceManager serviceManager;

    /**
     * Main Method
     *
     * @param args
     */
    public static void Main(String[] args) {
        launch(args);
    }

    /**
     * Overrided start method
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Create Service Manager
        serviceManager = new ServiceManager();
        
        //-- Create Panes --
        //Create the root pane and format it.
        root = new BorderPane();
        root.setPadding(PADDING);
        root.setPrefSize(1020, 800);

        //Create the Manager VBox to hold the managerGrid
        VBox managerVBox = new VBox();
        managerVBox.setPadding(PADDING);
        managerVBox.setAlignment(Pos.CENTER);

        //Create the bottomHBox to hold the manager Button
        HBox bottomHBox = new HBox();
        bottomHBox.setAlignment(Pos.CENTER);

        HBox customerHbox = createCustomerUI();
        
        // --- Create Buttons ---
        Button resetButton = new CustomButtons("res/images/refresh.png", "Reset Items");
        resetButton.setOnAction(e -> {
            serviceManager.getIManager().resetProducts();
            serviceManager.UpdateGui();
        });
        //Button to display Manager Grid
        Button btnManager = new CustomButtons("res/images/manager.png", "Manager Mode");
        //btnManager action event for changing if manager grid is displayed or not
        btnManager.setOnAction(event -> {
            if (managerMode) {
                root.setCenter(CUSTOMER_UI_GROUP);
                managerMode = false;
                bottomHBox.getChildren().remove(resetButton);
                serviceManager.UpdateGui();
            } else {
                // Else have scene contain managerVBox
                root.setCenter(MANAGER_UI_GROUP);
                managerMode = true;
                bottomHBox.getChildren().add(resetButton);
                serviceManager.UpdateGui();
            }
        });
        // --- Nodes to Groups ---
        //Add the createCustomerUI to customerUI group
        CUSTOMER_UI_GROUP.getChildren().addAll(customerHbox);
        //Add managerVBox to managerUI group
        MANAGER_UI_GROUP.getChildren().addAll(managerVBox);

        // --- Add Nodes to Panes ---
        //Add managerGrid to managerVBox
        managerVBox.getChildren().addAll(serviceManager.getManagerGrid());
        //Add btnManager to bottomHBox
        bottomHBox.getChildren().addAll(btnManager);
        //Set default UI Group for root Pane
        root.setCenter(CUSTOMER_UI_GROUP);
        //Add the bottomHBox to the bottom of root.
        root.setBottom(bottomHBox);

        // --- Standard JavaFX ---
        //Set up default Scene
        Scene scene = new Scene(root, 1020, 800);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true); // Prevent user resizing
        primaryStage.show();
    }

    /**
     * Method to create the customerHBox
     *
     * @return customerHBox
     */
    public HBox createCustomerUI() {
        // --- Create and Formate Panes ---
        //Create pane that will hold all center content
        HBox customerHBox = new HBox();
        customerHBox.setPadding(PADDING);

        //Create hbox to hold product selection buttons
        HBox productSelectionHBox = new HBox();
        productSelectionHBox.setPadding(PADDING);

        // vbox to hold Inventory Grid and Selection Buttons
        VBox inventoryVBox = new VBox();
        inventoryVBox.setPadding(PADDING);
        inventoryVBox.setAlignment(Pos.CENTER);
        inventoryVBox.setMaxWidth(510);
        inventoryVBox.setMinWidth(510);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setAlignment(Pos.CENTER);
        cartVBox.setPadding(PADDING);
        cartVBox.setMaxWidth(510);
        cartVBox.setMinWidth(510);

        // --- Create Labels ---
        //Text Labels
        Text inventoryLabel = new Text("Vending Machine");
        Text cartLabel = new Text("Cart");

        // --- Selection Buttons ---
        //Button for selecting Drink with picture
        Button btnDrink = new CustomButtons("res/images/drinks.png", "Drinks");
        btnDrink.setOnAction(event -> {
            //Sort the inventory grid to show only drinks
            serviceManager.getInventoryGrid().setProductType("drink");
        });

        //Button for selecting Chips with picture
        Button btnChip = new CustomButtons("res/images/chips.png", "Chips");
        btnChip.setOnAction(event -> {
            //Sort the inventory grid to show only chips
            serviceManager.getInventoryGrid().setProductType("chips");
        });

        //Button for selecting Snack with picture
        Button btnCandy = new CustomButtons("res/images/candy.png", "Candy");
        btnCandy.setOnAction(event -> {
            //Sort the inventory grid to show only candy
            serviceManager.getInventoryGrid().setProductType("candy");
        });

        //Button for selecting Gum with picture
        Button btnGum = new CustomButtons("res/images/gum.png", "Gums");
        btnGum.setOnAction(event -> {
            //Sort the inventory grid to show only gum
            serviceManager.getInventoryGrid().setProductType("gum");
        });

        // --- Fill Panes ---
        //Add Nodes to panes
        customerHBox.getChildren().addAll(inventoryVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        inventoryVBox.getChildren().addAll(inventoryLabel, productSelectionHBox, serviceManager.getInventoryGrid());
        cartVBox.getChildren().addAll(cartLabel, serviceManager.getCartGrid(), serviceManager.getTotalPrice());

        // Return customerHBox Node
        return customerHBox;
    }

}
