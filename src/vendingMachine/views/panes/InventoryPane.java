/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class creates Panes of products for the inventory.
 */
package vendingMachine.views.panes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.ServiceManager;
import vendingMachine.views.animations.ToCart;
import vendingMachine.products.Product;

public class InventoryPane extends ProductPane {

    private double X = 50;
    private double Y = 200;
    private ImageView animateImage;
    private ToCart animation;

    public InventoryPane(ServiceManager serviceManager, Product product) {
        super(serviceManager, product);

        if (product.getQuantity() <= 0) {
            productImage.setImage(new Image("res/images/soldOut.png"));
        }

        //Get this products image as a seperate image view for animation
        animateImage = new ImageView(getImage());
        animateImage.setFitWidth(75);
        animateImage.setFitHeight(75);

        this.setOnMouseClicked(event -> {
            if (product.getQuantity() > 0) {
                //Add item to cart
                serviceManager.getCart().addToCart(product);
                serviceManager.getIManager().decreaseQuantity(product);
                updateStockLabel(product);
                //Create animationPane
                animation = new ToCart(50, 75, X, Y, animateImage);
                //Run Animation
                this.toFront();
                this.getChildren().add(animation);
                if (product.getQuantity() == 0) {
                    productImage.setImage(new Image("res/images/soldOut.png"));
                }
            }
        });
        
        //Set the default style of the panel
        this.setStyle(BTN_NORMAL);

        //Set the if hovering styles of the panel.
        this.setOnMouseEntered(event -> {this.setStyle(BTN_HOVER);});
        this.setOnMouseExited(event -> {this.setStyle(BTN_NORMAL);});

       
    }

    public void finishAnimation() {
        this.getChildren().remove(animation);
        animateImage.setOpacity(1.0);
        serviceManager.UpdateCartGui();
    }
    
   
}
