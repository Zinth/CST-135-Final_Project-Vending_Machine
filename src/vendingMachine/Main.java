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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.gui.AlertWindow;
import vendingMachine.classes.gui.ButtonManager;
import vendingMachine.classes.gui.CustomButtons;
import vendingMachine.classes.gui.MachineComboBox;

public class Main extends Application {

    // Dispencer class creation
    private final Insets PADDING = new Insets(5, 5, 5, 5);
    //private boolean managerMode = false;
    private BorderPane root;
    private final Group CUSTOMER_UI_GROUP = new Group();
    private final Group MANAGER_UI_GROUP = new Group();
    private ServiceManager serviceManager;
    private ButtonManager btnManager;
    private AlertWindow alert = new AlertWindow();

    /**
     * Main Method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overriden start method
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Create Service Manager
        serviceManager = new ServiceManager();
        btnManager = serviceManager.getBtnManager();
        
        //-- Create Panes --
        //Create the root pane and format it.
        root = new BorderPane();
        root.setPrefSize(1020, 800);
        root.getStyleClass().add("main");

        //Create MenuBar;
        MenuBar menu = serviceManager.getMenuBar();
        
        

        //Create the Manager VBox to hold the managerGrid
        VBox managerVBox = new VBox();
        managerVBox.setPadding(PADDING);
        managerVBox.setAlignment(Pos.CENTER);

        //Create the bottomHBox to hold the manager Button
        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);

        //Create the Customer VBox
        VBox customerVBox = createCustomerUI();
        
        //Create Manager Screen Label
        Label managerLabel = new Label("Manager Mode");
        managerLabel.getStyleClass().add("labelHeader");
        

        //Create manager ScrollPane
        ScrollPane managerScroll = new ScrollPane();
        managerScroll.setMaxHeight(650);
        managerScroll.setFitToWidth(true);

        //Create HBox to hold vending machine switching buttons
        ComboBox switchMachines = new MachineComboBox(serviceManager); // change the arguement to reflect number of machines

        // --- Create Buttons ---
        CustomButtons resetButton = new CustomButtons(serviceManager, true, "res/images/refresh.png", "Reset Items");
        resetButton.setShape(new Circle(3));
        resetButton.setOnAction(e -> {
            serviceManager.getIManager().resetProducts();
            serviceManager.UpdateGui();
        });
        
        //Button to display Manager Grid
        Button btnManager = new CustomButtons(serviceManager, false, "res/images/manager.png", "Manager Mode");
        btnManager.setShape(new Circle(3));
        //btnManager action event for changing if manager grid is displayed or not
        btnManager.setOnAction(event -> {
            if (serviceManager.isManagerMode()) {
                root.setCenter(CUSTOMER_UI_GROUP);
                serviceManager.setManagerMode(false);
                resetButton.updateNode();
                serviceManager.UpdateGui();
            } else {
                // Else have scene contain managerVBox
                root.setCenter(MANAGER_UI_GROUP);
                serviceManager.setManagerMode(true);
                resetButton.updateNode();
                serviceManager.UpdateGui();
            }
        });
        // --- Nodes to Groups ---
        //Add the createCustomerUI to customerUI group
        CUSTOMER_UI_GROUP.getChildren().addAll(customerVBox);
        //Add managerVBox to managerUI group
        MANAGER_UI_GROUP.getChildren().addAll(managerVBox);

        // --- Add Nodes to Panes ---
        //Add managerGrid to managerVBox
        managerScroll.setContent(serviceManager.getManagerGrid());
        managerVBox.getChildren().addAll(managerLabel,switchMachines, managerScroll);

        //Add btnManager to bottomHBox
        rightVBox.getChildren().addAll(btnManager, resetButton);
        //Set default UI Group for root Pane
        root.setCenter(CUSTOMER_UI_GROUP);
        root.setAlignment(CUSTOMER_UI_GROUP, Pos.TOP_CENTER);
        //Add the bottomHBox to the bottom of root.
        root.setRight(rightVBox);

        //Add Menu bar to root top
        root.setTop(menu);
        
        //Add Customer Animation Que Line to root
        root.setLeft(serviceManager.getCustomerLine());
        
        //Add Customer Info Pane to root
        root.setBottom(serviceManager.getCustomerInfo());
        
        // --- Standard JavaFX ---
        //Set up default Scene
        Scene scene = new Scene(root, 1020, 800);
        scene.getStylesheets().add("vendingMachine.css");

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
    public VBox createCustomerUI() {
        // --- Create and Formate Panes ---
        //Create pane that will hold all center content
        VBox customerVBox = new VBox();
        customerVBox.setPadding(PADDING);
        customerVBox.setAlignment(Pos.TOP_CENTER);
        customerVBox.setMinSize(0, 0);
        customerVBox.setMaxSize(root.getMaxHeight(), root.getMaxWidth());

        //Create hbox to hold product selection buttons
        HBox productSelectionHBox = new HBox();
        productSelectionHBox.setPadding(PADDING);
        productSelectionHBox.setAlignment(Pos.CENTER);

        // vbox to hold Inventory Grid and Selection Buttons
        VBox inventoryVBox = new VBox();
        inventoryVBox.setPadding(PADDING);
        inventoryVBox.setAlignment(Pos.CENTER);
        inventoryVBox.getStyleClass().add("inventoryVBox");

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setAlignment(Pos.CENTER);
        cartVBox.setPadding(PADDING);
        //cartVBox.setStyle(PANE_STYLE);

        //Create manager ScrollPane
        ScrollPane cartScroll = new ScrollPane();
        cartScroll.setMaxHeight(300);
        cartScroll.setFitToWidth(true);

        // --- Create Labels ---
        //Inventory Text Label
        Label inventoryLabel = new Label("Vending Machine");
        inventoryLabel.getStyleClass().add("labelHeader");
        //Invenotry Cart Label
        Label cartLabel = new Label("Cart");
        cartLabel.getStyleClass().add("labelHeader");

        // --- Selection Buttons ---
        
        
        
        // --- Fill Panes ---
        //Add Nodes to panes
        customerVBox.getChildren().addAll(inventoryVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnManager.getBtnDrink(), btnManager.getBtnChip(), btnManager.getBtnCandy(), btnManager.getBtnGum());
        inventoryVBox.getChildren().addAll(inventoryLabel, productSelectionHBox,  serviceManager.getInventoryGrid());
        cartVBox.getChildren().addAll(cartLabel, cartScroll, serviceManager.getTotalPrice(), btnManager.getBtnPurchase());
        cartScroll.setContent(serviceManager.getCartGrid());

        // Return customerHBox Node
        return customerVBox;
    }

}
