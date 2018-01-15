/**
 * @project Final Project - Vending Machine
 * @about Create a custom vbox for holding product information.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/13/18
 */

package vendingMachine.classes.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vendingMachine.classes.Cart;
import vendingMachine.classes.products.Product;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;


public class ProductPane extends VBox {

    private String labelStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-label-padding: 5; ";
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance();

    //basic constructor
    public ProductPane(){}

    /**
     * Contructor to create a array list of Stack panes holding product information.
    * @param product
     */
    public ProductPane(Product product, Cart cart) {

            // Create an ImageView of the product image
            ImageView productImage = new ImageView(new Image("res/images/" + product.getImageName()));
            productImage.setFitWidth(100);
            productImage.setFitHeight(100);


            // Label for displaying the name of the product
            Label productName = new Label(product.getProductName());
            productName.setWrapText(true);
            productName.setStyle(labelStyle);

            // Label for displaying the price of the product
            Label productPrice = new Label(formatter.format(product.getPrice()) + "\nStock: " +String.valueOf(product.getQuantity()));
            productPrice.setWrapText(true);
            productPrice.setStyle(labelStyle);

            Button btnBuy = new Button("Purchase");
            btnBuy.setOnAction(event -> {
                if(product.getQuantity() > 0){
                    product.setQuantity(product.getQuantity() - 1); // subtract one from
                    cart.addToCart(product);
                }else{
                    System.err.println("Out of product.");
                }
            });



            //Add nodes to productPane
            this.getChildren().addAll(productImage, productName, productPrice, btnBuy);

            //TODO: Fromat StackPane to look good.

        this.setAlignment(Pos.CENTER);
    }




}
