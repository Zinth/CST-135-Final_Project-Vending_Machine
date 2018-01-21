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
import vendingMachine.classes.Cart;
import vendingMachine.classes.gui.AnimationPane;
import vendingMachine.classes.gui.grids.CartGrid;
import vendingMachine.classes.products.Product;

public class InventoryPane extends ProductPane {
    public InventoryPane(Product product, CartGrid cartGrid, double x , double y){
        super(product);

        Button btnAdd = new Button("Add");
        //Get this products image as a seperate image view for animation
        ImageView animateImage = new ImageView(getImage());
        animateImage.setFitWidth(75);
        animateImage.setFitHeight(75);

        btnAdd.setOnAction(event -> {
            //Add item to cart
            cartGrid.getCart().addToCart(product);
            //refresh cartGrid
            cartGrid.fillGrid();
            
            this.updateLabel(product);
            //Create animationPane
//            AnimationPane animation = new AnimationPane(0, 0, x,y, animateImage );
            //Run Animation
//            this.getChildren().add(animation);

        });
        btnAdd.setStyle("-fx-background-color: \n" +
                "        linear-gradient(#ffd65b, #e68400),\n" +
                "        linear-gradient(#ffef84, #f2ba44),\n" +
                "        linear-gradient(#ffea6a, #efaa22),\n" +
                "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0))");

        btnAdd.setOnMouseEntered(event -> {
            btnAdd.setStyle("-fx-background-color:\n" +
                    "        linear-gradient(#f0ff35, #a9ff00),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");
        });
        btnAdd.setOnMouseExited(event -> {
            btnAdd.setStyle("-fx-background-color: \n" +
                    "        linear-gradient(#ffd65b, #e68400),\n" +
                    "        linear-gradient(#ffef84, #f2ba44),\n" +
                    "        linear-gradient(#ffea6a, #efaa22),\n" +
                    "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                    "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
        });

        this.getChildren().add(btnAdd);
    }
}
