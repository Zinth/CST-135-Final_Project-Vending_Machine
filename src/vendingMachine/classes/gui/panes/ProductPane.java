/**
 * @project Final Project - Vending Machine
 * @about Create a custom vbox for holding product information.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/13/18
 */

package vendingMachine.classes.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import vendingMachine.classes.products.Product;
import vendingMachine.classes.ServiceManager;



public class ProductPane extends VBox {

    private String labelStyle = "-fx-background-color: cyan; -fx-text-fill: black; -fx-label-padding: 5; ";
    protected ImageView productImage;
    private Label productInfo;
    protected ServiceManager serviceManager;

    //basic constructor
    public ProductPane(){}

    /**
     * Contructor to create a array list of Stack panes holding product information.
     * @param serviceManager
     * @param product
     */
    public ProductPane(ServiceManager serviceManager, Product product) {
        this.serviceManager = serviceManager;
            // Create an ImageView of the product image
            productImage = new ImageView(new Image("res/images/" + product.getImageName()));
            productImage.setFitWidth(100);
            productImage.setFitHeight(100);



            // Label for displaying the price of the product
            productInfo = new Label(product.getProductName() + ":\n" + serviceManager.formatPrice(product.getPrice()) + "\nStock: " +String.valueOf(product.getQuantity()));
            productInfo.setWrapText(true);
            productInfo.setStyle(labelStyle);


            //Add nodes to productPane
            this.getChildren().addAll(productImage, productInfo);

            //TODO: Fromat StackPane to look good.
        this.setStyle("-fx-background-color: cyan; -fx-border-color: black");

        this.setAlignment(Pos.CENTER);
    }

    /**
     * get the X position of the Pane
     * @return
     */
    public double getPositionX(){
        return this.getLayoutX();
    }

    /**
     * get the Y position of the Pane
     * @return
     */
    public double getPositionY(){
        return this.getLayoutY();
    }

    /**
     * get the ImageView
     * @return
     */
    public Image getImage() {
        return productImage.getImage();
    }
}
