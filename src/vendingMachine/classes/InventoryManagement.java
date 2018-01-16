/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
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

public class InventoryManagement extends Product {

    protected ObservableList<String> cartObservableList;
    protected ArrayList<String> cartListItems = new ArrayList<String>();
    private void sendAlert(AlertType error, String item_unavailable, String item_unavailable1, String errorMessage) {
    }

    InventoryManagement(){

    }

    /**
     * sellItem
     *
     * Reduce items based on what has been sold.
     *
     * @param product
     * @param qty
     */
    public boolean addToCart(Product product, int qty){
        String errorMessage = "";

        //First check to make sure the items are available.
        if (product.getQuantity() <= 0){
            if (product.getQuantity() == 0)
                errorMessage = "Sorry, " + product.getProductName()+ "available. Please select a different item.";
            else
                errorMessage = "Sorry, only " + product.getQuantity() + "left of " + product.getProductName() + ". Please select a differnt item.";

            sendAlert(AlertType.ERROR, "Item Unavailable", "Item Unavailable", errorMessage);

            return false;
        }else{
            product.setQuantity(product.getQuantity() - qty);

           //Add items to the cart
            cartListItems.add(product.getProductName() + " - $" + product.getPrice());
            updateCartObservableList();

            //This step will create the animation for items dropping into the cart.
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add to cart");
            stage.setResizable(false);

            Group root = new Group();

            ImageView cart = new ImageView(new Image("res/images/cart.jpg"));
            cart.relocate(15, 250);
            cart.setFitHeight(100);
            cart.setFitWidth(100);

            //Import the images for the items.
            ImageView productImage = new ImageView();

        switch (product.getProductName()){
            case "Arrowhead Water": productImage = new ImageView((new Image("res/images/arrowhead_water.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Big Red Gum": productImage = new ImageView((new Image("res/images/big_red.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Coca Cola": productImage = new ImageView((new Image("res/images/coca-cola.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Diet Coca Cola": productImage = new ImageView((new Image("res/images/diet_coca-cola.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Diet Pepsi": productImage = new ImageView((new Image("res/images/diet_pepsi.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Doritos Cool Ranch": productImage = new ImageView((new Image("res/images/doritos_cool_ranch.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Doritos Nacho Cheese": productImage = new ImageView((new Image("res/images/doritos_nacho_cheese.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Doublemint Gum": productImage = new ImageView((new Image("res/images/doublemint.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Fritos Chilli Cheese Chips": productImage = new ImageView((new Image("res/images/fritos_chili_cheese.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Fritos Original": productImage = new ImageView((new Image("res/images/fritos_original.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Gatorade Fruit Punch": productImage = new ImageView((new Image("res/images/gatorade_fruit_punch.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Hershy Candy Bar": productImage = new ImageView((new Image("res/images/hershy.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Juicy Fruit Gum": productImage = new ImageView((new Image("res/images/juicy_fruit.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Juicy Fruit Sour Watermelon": productImage = new ImageView((new Image("res/images/juicy_fruit_sour_watermelon.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Lay's BBQ Chips": productImage = new ImageView((new Image("res/images/lays_bbq.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Lay's Classic Chips": productImage = new ImageView((new Image("res/images/lays_classic.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "M & M Candy": productImage = new ImageView((new Image("res/images/m_and_m.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Pepsi": productImage = new ImageView((new Image("res/images/pepsi.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Resees Candy": productImage = new ImageView((new Image("res/images/resees.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Skittles Candy": productImage = new ImageView((new Image("res/images/skittles.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Snickers": productImage = new ImageView((new Image("res/images/snickers.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Starburst Candy": productImage = new ImageView((new Image("res/images/starburst.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Trident Cinnamon Gum": productImage = new ImageView((new Image("res/images/trident_cinnamon.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;

            case "Trident Tropical Twist": productImage = new ImageView((new Image("res/images/trident_tropical_twist.jpg")));
                productImage.setFitHeight(70);
                productImage.setFitWidth(70);
                break;
        }

        productImage.relocate(0,0);
            root.getChildren().addAll(productImage, cart);

            //Build animation path.
            Path path = new Path();
            path.getElements().addAll(new MoveTo(50,50), new VLineTo(300));
            path.setFill(null);
            path.setStroke(Color.TRANSPARENT);
            path.setStrokeWidth(2);
            root.getChildren().add(path);

            Scene scene = new Scene(root, 100, 300);
            stage.setScene(scene);
            stage.show();

            PathTransition pt = new PathTransition(Duration.millis(1000), path, productImage);
        pt.play();
            pt.setAutoReverse(false);
            pt.play();

            return true;

        }
    }



    /**
     *
     * removing items from cart
     *
     * @param mainDispenser
     * @param productId
     * @param itemInList
     *
     */
    public void removeFromCart(Dispenser mainDispenser, int productId, int itemInList){

        cartObservableList.remove(itemInList);

        //remove item from array
        for (int i = 0; i < cartListItems.size(); i++){
            String[] itemToRemove = cartListItems.get(i).split(" - ");

            if (Integer.parseInt(itemToRemove[1] == productId)){
                cartListItems.remove(i);

            }
        }
        for (int k = 0; k < mainDispenser.getProducts().size(); k++){
            Product theProduct = mainDispenser.getProducts().get(k);
            
            if (theProduct.getProductId() == productId){
                theProduct.setQuantity(theProduct.getQuantity() + 1);
                
            }
        }
    }
    /**
     * getCartTotal
     * 
     * @return
     */
    public double getCartTotal(){
        double tempTotal = 0.00;
        
        for (int j = 0; j < cartListItems.size(); j++){
            String[] itemToRemove = cartListItems.get(j).split(" - ");
            
            tempTotal += Float.parseFloat(itemToRemove[2].replace("$", ""));
                        
        }
        
        return Math.round(tempTotal * 100D) / 100D;
    }
    /**
     * updateCartObservableList()
     * 
     */
    private void updateCartObservableList(){
        String[] tempListItems = new String[cartListItems.size()];
        
        for (int i = 0; i < cartListItems.size(); i++){
            tempListItems[i] = cartListItems.get(i);
        }
        
        cartObservableList = FXCollections.observableArrayList(tempListItems);
    }
    
    public ObservableList<String>getCartObservableList(){
        return cartObservableList;
    }

}
