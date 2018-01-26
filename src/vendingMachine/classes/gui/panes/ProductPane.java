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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import vendingMachine.classes.products.Product;
import vendingMachine.classes.ServiceManager;



public class ProductPane extends VBox {

    protected final String LABEL_STYLE = "-fx-text-fill: white; -fx-label-padding: 2; ";
    protected final String BTN_NORMAL = "-fx-background-color: \n"
            + "#090a0c,\n"
            + "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
            + "linear-gradient(#20262b, #191d22),\n"
            + "radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));";
    protected final String BTN_HOVER = "-fx-background-color: \n"
            + "#000000,\n"
            + "linear-gradient(#7ebcea, #2f4b8f),\n"
            + "linear-gradient(#426ab7, #263e75),\n"
            + "linear-gradient(#395cab, #223768);";
    
    protected ImageView productImage;
    protected Label productInfo;
    protected Label stockLabel;
    protected ServiceManager serviceManager;

    //basic constructor
    public ProductPane(){}

    /**
     * Constructor to create a array list of Stack panes holding product information.
     * @param serviceManager
     * @param product
     */
    public ProductPane(ServiceManager serviceManager, Product product) {
        this.serviceManager = serviceManager;
        // Create an ImageView of the product image
        productImage = new ImageView(new Image("res/images/" + product.getImageName()));
        productImage.setFitWidth(100);
        productImage.setFitHeight(100);

        //Label for displaying the current Stock
        stockLabel = new Label("Stock: " + String.valueOf(product.getQuantity()));
        stockLabel.setStyle(LABEL_STYLE);
        stockLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 16));

        // Label for displaying the price of the product
        productInfo = new Label(product.getProductName() + "\n" + serviceManager.formatPrice(product.getPrice()));
        productInfo.setFont(Font.font("Calibri", FontWeight.BOLD, 12));
        productInfo.setWrapText(true);
        productInfo.setStyle(LABEL_STYLE);
        productInfo.setTextAlignment(TextAlignment.CENTER);

        // Add nodes to productPane
        this.getChildren().addAll(productInfo, productImage, stockLabel);

        //TODO: Fromat StackPane to look good.
        this.setStyle("-fx-background-color: #7D869C;");
        this.setAlignment(Pos.CENTER);
    }
    protected void updateStockLabel(Product product){
        stockLabel.setText("Stock: " + String.valueOf(product.getQuantity()));
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
