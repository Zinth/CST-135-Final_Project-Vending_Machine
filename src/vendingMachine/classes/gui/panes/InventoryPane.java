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
package vendingMachine.classes.gui.panes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.gui.AnimationPane;
import vendingMachine.classes.products.Product;

public class InventoryPane extends ProductPane {

    private double X = 50;
    private double Y = 300;
    private ImageView animateImage;
    private AnimationPane animation;

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
                //Create animationPane
                animation = new AnimationPane(50, 0, X, Y, animateImage);
                //Run Animation
                this.toFront();
                this.getChildren().add(animation);
                if (product.getQuantity() == 0) {
                    productImage.setImage(new Image("res/images/soldOut.png"));
                }
            }
        });
        this.setStyle("-fx-background-color: \n" +
"        #090a0c,\n" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
"        linear-gradient(#20262b, #191d22),\n" +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: \n" +
"        #000000,\n" +
"        linear-gradient(#7ebcea, #2f4b8f),\n" +
"        linear-gradient(#426ab7, #263e75),\n" +
"        linear-gradient(#395cab, #223768);");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: \n" +
"        #090a0c,\n" +
"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
"        linear-gradient(#20262b, #191d22),\n" +
"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");
        });

       
    }

    public void finishAnimation() {
        this.getChildren().remove(animation);
        animateImage.setOpacity(1.0);
        serviceManager.UpdateGui();
    }
}
