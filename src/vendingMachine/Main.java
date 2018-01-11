package vendingMachine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vendingMachine.classes.Dispenser;
import vendingMachine.classes.gui.ProductPane;
import vendingMachine.classes.products.Product;

public class Main extends Application{

    // Dispencer class creation
    Dispenser dispenser = new Dispenser(20.00, 0);


    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = new GridPane();

        int counter = 0;

        for(int i =0; i < 4; i++){
            for(int j = 0; j< 6; j++){
                gridPane.add(new ProductPane(dispenser.getProductList().get(counter)), j, i);
                counter++;
            }
        }



        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void Main(String[] args){
        launch(args);
    }
}
