/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class manages the inventory of the Dispenser and is infact and extention of the Dispenser Class
 */

package vendingMachine.classes;

import java.util.ArrayList;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import vendingMachine.classes.products.Product;

public class InventoryManagement extends Dispenser {

    public InventoryManagement(){
        super(20, 0);
    }

    /**
     * Increace Product Quantity by one
     */
    public void increaseQuantity(Product product){
        for (int index = 0; index < getProductList().size(); index++){
            if(getProductList().get(index) == product){
                getProductList().get(index).increaseQuantity();
            }
        }
    }

    /**
     * Decrease Product Quantity by one
     */
    public void decreaseQuantity(Product product){
        for (int index = 0; index < getProductList().size(); index++){
            if(getProductList().get(index) == product){
                getProductList().get(index).decreaseQuantity();
            }
        }
    }



}
