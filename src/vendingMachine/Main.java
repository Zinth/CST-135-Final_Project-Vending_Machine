package vendingMachine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vendingMachine.classes.Dispenser;
import vendingMachine.classes.gui.ProductGrid;
import vendingMachine.classes.gui.ProductPane;
import vendingMachine.classes.products.*;

public class Main extends Application{

    // Dispencer class creation
    private Dispenser dispenser = new Dispenser(20.00, 0);
    private Insets padding = new Insets(5,5,5,5);
    private String borderedItems = "-fx-border-color: blue;\n"
            + "-fx-border-insets: 6;\n"
            + "-fx-border-width: 2;\n"
            + "-fx-border-style: solid;\n";


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

        // vbox to hold grid
        VBox productVBox = new VBox();
        productVBox.setPadding(padding);

        //vbox to hold cart grid , total price cost, and confirmation button
        VBox cartVBox = new VBox();
        cartVBox.setPadding(padding);

        //Grid pane for the products
        ProductGrid productGrid = new ProductGrid(dispenser.getProductList(), 4);

        //Button for selecting Drink
        Button btnDrink = new Button("Drinks");
        btnDrink.setOnAction(event -> {
         productGrid.sortProductGrid("drink");
        });

        //Button for selecting Chips
        Button btnChip = new Button("Chips");
        btnChip.setOnAction(event -> {
            productGrid.sortProductGrid("chips");
        });

        //Button for selecting Snack
        Button btnCandy = new Button("Candy");
        btnCandy.setOnAction(event -> {
            productGrid.sortProductGrid("candy");
        });
        //Button for selecting Snack
        Button btnGum = new Button("Gum");
        btnGum.setOnAction(event -> {
            productGrid.sortProductGrid("gum");
        });

        //Add Nodes to panes
        mainPane.setCenter(mainHBox);
        mainHBox.getChildren().addAll(productVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnChip, btnCandy, btnGum);
        productVBox.getChildren().addAll(productSelectionHBox, productGrid);

        //Set Scene
        Scene scene = new Scene(mainPane, 800, 600);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void Main(String[] args){
        launch(args);
    }
}