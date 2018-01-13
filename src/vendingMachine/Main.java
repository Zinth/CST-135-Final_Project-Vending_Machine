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
import vendingMachine.classes.gui.ProductPane;
import vendingMachine.classes.products.Drink;
import vendingMachine.classes.products.Product;
import vendingMachine.classes.products.Snack;

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
        GridPane productGrid = new GridPane();


        //Button for selecting Drink
        Button btnDrink = new Button("Drinks");
        btnDrink.setOnAction(event -> {
           buildProductGrid(productGrid, true);
        });

        //Button for selecting Snack
        Button btnSnack = new Button("Snacks");
        btnSnack.setOnAction(event -> {
            buildProductGrid(productGrid, false);
        });

        //Add Nodes to panes
        mainPane.setCenter(mainHBox);
        mainHBox.getChildren().addAll(productVBox, cartVBox);
        productSelectionHBox.getChildren().addAll(btnDrink, btnSnack);
        productVBox.getChildren().addAll(productSelectionHBox, productGrid);

        //Set Scene
        Scene scene = new Scene(mainPane, 800, 600);

        //Set primaryStage
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void buildProductGrid( GridPane gridPane, boolean isDrink){
        // Clear gridPane's previous content
        gridPane.getChildren().clear();

        // counter for indexing products
        int counter = 0;

        // imbeded for loop for filling gridPane
        for(int i =0; i < 4; i++){
            for(int j = 0; j< 6; j++){
                //See if the product is a Drink or not and populate gridPane accordingly
                if(isDrink){
                    if(dispenser.getProductList().get(counter) instanceof Drink) {
                        gridPane.add(new ProductPane(dispenser.getProductList().get(counter)), j, i);
                    }
                }else {
                    if(dispenser.getProductList().get(counter) instanceof Snack) {
                        gridPane.add(new ProductPane(dispenser.getProductList().get(counter)), j, i);
                    }
                }
                //Incrament counter
                counter++;
            }
        }
    }


    public static void Main(String[] args){
        launch(args);
    }
}