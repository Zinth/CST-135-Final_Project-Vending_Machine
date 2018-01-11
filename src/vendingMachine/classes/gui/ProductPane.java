package vendingMachine.classes.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import vendingMachine.classes.products.Product;

import java.util.ArrayList;


public class ProductPane extends StackPane {


    //basic constructor
    public ProductPane(){}

    /**
     * Contructor to create a array list of Stack panes holding product information.
    * @param product
     */
    public ProductPane(Product product) {

            // Create an ImageView of the product image
            ImageView productImage = new ImageView(new Image("res/images/" + product.getImageName()));
            productImage.setFitWidth(200);
            productImage.setFitHeight(200);
            // Label for displaying the price of the product
            Label productPrice = new Label("$" + product.getPrice());
            productPrice.setWrapText(true);
            productPrice.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-label-padding: 5; -fx-font: Calibri");

            //TODO: edit the to string method to better detail the product
            /* Label for displaying the detials of the product
            Label productDetails = new Label(product.toString());
            productDetails.setWrapText(true);
            */
            //Add nodes to productPane
            this.getChildren().addAll(productImage, productPrice);

            //TODO: Fromat StackPane to look good.
        this.setStyle("-fx-alignment: bottom-center");
    }

}
