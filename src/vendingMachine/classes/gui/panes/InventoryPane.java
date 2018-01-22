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

    private double X = 410;
    private double Y = 0;
    private ImageView animateImage;
    private AnimationPane animation;

    public InventoryPane(ServiceManager serviceManager, Product product) {
        super(serviceManager, product);

        if (product.getQuantity() <= 0) {
            productImage.setImage(new Image("res/images/soldOut.png"));
        }
        Button btnAdd = new Button("Add");
        //Get this products image as a seperate image view for animation
        animateImage = new ImageView(getImage());
        animateImage.setFitWidth(75);
        animateImage.setFitHeight(75);

        btnAdd.setOnAction(event -> {
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
        btnAdd.setStyle("-fx-background-color: \n"
                + "        linear-gradient(#ffd65b, #e68400),\n"
                + "        linear-gradient(#ffef84, #f2ba44),\n"
                + "        linear-gradient(#ffea6a, #efaa22),\n"
                + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0))");

        btnAdd.setOnMouseEntered(event -> {
            btnAdd.setStyle("-fx-background-color:\n"
                    + "        linear-gradient(#f0ff35, #a9ff00),\n"
                    + "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");
        });
        btnAdd.setOnMouseExited(event -> {
            btnAdd.setStyle("-fx-background-color: \n"
                    + "        linear-gradient(#ffd65b, #e68400),\n"
                    + "        linear-gradient(#ffef84, #f2ba44),\n"
                    + "        linear-gradient(#ffea6a, #efaa22),\n"
                    + "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
                    + "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
        });

        this.getChildren().add(btnAdd);
    }

    public void finishAnimation() {
        this.getChildren().remove(animation);
        animateImage.setOpacity(1.0);
        serviceManager.UpdateGui();
    }
}
