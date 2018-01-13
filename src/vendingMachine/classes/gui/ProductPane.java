package vendingMachine.classes.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import vendingMachine.classes.products.Product;

import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class ProductPane extends VBox {

    private String labelStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-label-padding: 5; -fx-font: Calibri;";
    private ArrayList<Product> cartList;

    //basic constructor
    public ProductPane(){}

    /**
     * Contructor to create a array list of Stack panes holding product information.
    * @param product
     */
    public ProductPane(Product product) {

            // Create an ImageView of the product image
            ImageView productImage = new ImageView(new Image("res/images/" + product.getImageName()));
            productImage.setFitWidth(100);
            productImage.setFitHeight(100);



            // Label for displaying the name of the product
            Label productName = new Label(product.getProductName());
            productName.setWrapText(true);
            productName.setStyle(labelStyle);

            // Label for displaying the price of the product
            Label productPrice = new Label("$" +String.valueOf(product.getPrice()) + "Stock: " +String.valueOf(product.getQuantity()));
            productPrice.setWrapText(true);
            productPrice.setStyle(labelStyle);


            //TODO: edit the to string method to better detail the product
            Label productDetails = new Label(product.toString());
            productDetails.setWrapText(true);

            //Add nodes to productPane
            this.getChildren().addAll(productImage, productName, productPrice);

            //TODO: Fromat StackPane to look good.

        this.setAlignment(Pos.CENTER);
    }

    public ArrayList<Product> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Product> cartList) {
        this.cartList = cartList;
    }
}
