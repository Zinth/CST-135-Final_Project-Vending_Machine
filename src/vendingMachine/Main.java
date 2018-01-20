/**
 * @project Final Project - Vending Machine
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Jesus Leon
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 1/13/28
 */
package vendingMachine;

import java.text.NumberFormat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendingMachine.classes.Cart;
import vendingMachine.classes.InventoryManagement;
import vendingMachine.classes.gui.ProductGrid;
import vendingMachine.classes.gui.grids.InventoryGrid;

public class Main extends Application{

    // Dispencer class creation
    private InventoryManagement iManager = new InventoryManagement();
    private Cart cart = new Cart(iManager);
    private Insets padding = new Insets(5,5,5,5);
    private String borderedItems = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 6;\n"
            + "-fx-border-width: 2;\n"
            + "-fx-border-style: solid;\n";
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();


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
        productVBox.setAlignment(Pos.CENTER);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setPadding(padding);

        ImageView imageBasket = new ImageView(new Image("res/images/basket.jpg"));
        imageBasket.setFitHeight(100);
        imageBasket.setFitWidth(100);



        //Grid for InventoryPane's
        InventoryGrid productGrid = new InventoryGrid(iManager.getProductList(), 6, cart, imageBasket.getX(), imageBasket.getY());


        //Button for selecting Drink with picture
        Button btnDrink = new Button("Drinks", new ImageView(new Image("res/images/drink.jpg")));
        btnDrink.setOnAction(event -> {
         productGrid.sortProductGrid("drink");
        });

        //Button for selecting Chips with picture
        Button btnChip = new Button("Chips", new ImageView(new Image("res/images/chips.jpg")));
        btnChip.setOnAction(event -> {
            productGrid.sortProductGrid("chips");
        });

        //Button for selecting Snack with picture
        Button btnCandy = new Button("Candy", new ImageView(new Image("res/images/candy.jpg")));
        btnCandy.setOnAction(event -> {
            productGrid.sortProductGrid("candy");
        });
        //Button for selecting Gum with picture
        Button btnGum = new Button("Gum", new ImageView(new Image("res/images/gum.jpg")));
        btnGum.setOnAction(event -> {
            productGrid.sortProductGrid("gum");
        });




        //Add Nodes to panes
        mainPane.setCenter(mainHBox);
        mainHBox.getChildren().addAll(productVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        productVBox.getChildren().addAll(productSelectionHBox, productGrid, imageBasket);
        cartHBox.getChildren().addAll();
        cartVBox.getChildren().addAll();


        //Set Scene
        Scene scene = new Scene(mainPane, 1020, 800);

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
