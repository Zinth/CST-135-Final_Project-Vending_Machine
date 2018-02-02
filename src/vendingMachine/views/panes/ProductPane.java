/**
 * @project Final Project - Vending Machine
 * @about Create a custom vbox for holding product information.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/13/18
 */

package vendingMachine.views.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import vendingMachine.products.Product;
import utilities.ServiceManager;
import vendingMachine.products.Candy;
import vendingMachine.products.Chips;
import vendingMachine.products.Drink;



public class ProductPane extends StackPane {

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
    private Image image;
    protected Label productInfo;
    protected Tooltip toolTip;
    protected Label stockLabel = new Label();
    protected ServiceManager serviceManager;
    protected VBox infoVBox;
    protected Product product;

    //basic constructor
    public ProductPane(){}

    /**
     * Constructor to create a array list of Stack panes holding product information.
     * @param serviceManager
     * @param product
     */
    public ProductPane(ServiceManager serviceManager, Product product) {
        this.serviceManager = serviceManager;
        this.product = product;
        
        //Create a VBox to hold most conent
        infoVBox = new VBox();
        infoVBox.setAlignment(Pos.CENTER);
        infoVBox.setPadding(new Insets(5));
        
        // Create an ImageView of the product image
        try{ //Make sure image at path exists
        productImage = new ImageView(new Image("res/images/" + product.getImageName()));
        
        }catch(IllegalArgumentException e){
            //If not set the product Default image. 
            if(product instanceof Drink){
                productImage = new ImageView(new Image("res/images/defaultDrink.png"));
            }else if (product instanceof Chips){
                productImage = new ImageView(new Image("res/images/defaultChips.png"));
            }else if(product instanceof Candy){
                productImage = new ImageView(new Image("res/images/defaultCandy.png"));
            }else{
                productImage = new ImageView(new Image("res/images/defaultGum.png"));
            }
        }
        //Edit ImagaeView's size Limit.
        productImage.setFitWidth(100);
        productImage.setFitHeight(100);
        
        // Label for displaying the price of the product
        productInfo = new Label(serviceManager.formatPrice(product.getPrice()));
        productInfo.setWrapText(true);
        productInfo.setTextAlignment(TextAlignment.CENTER);
        
        //Set up tootip
        toolTip = new Tooltip(product.toString());
        toolTip.setFont(Font.font("Arial", 16));
        toolTip.getStyleClass().add("tooltip");
        toolTip.setGraphic(new ImageView(getImage()));
        Tooltip.install(this, toolTip);

        // Add nodes to productPane
        infoVBox.getChildren().addAll(productImage, productInfo);
        this.getChildren().add(infoVBox);

        //TODO: Fromat StackPane to look good.
        this.setStyle("-fx-background-color: #7D869C;");
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(100, 200);
    }
    protected void updateStockLabel(Product product){
        stockLabel.setText("Stock: " + String.valueOf(product.getQuantity()));
        toolTip.setText(product.toString());
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
    
     @Override
    public String toString(){
        return product.getProductName();
    }
    
}
