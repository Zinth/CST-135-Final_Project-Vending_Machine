/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @date 01/19/18
 *
 * @about class that displays productPanes on a grid based on Product type.
 */

package vendingMachine.classes.gui.grids;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import vendingMachine.classes.gui.panes.InventoryPane;
import vendingMachine.classes.products.*;

import vendingMachine.classes.ServiceManager;
import vendingMachine.interfaces.UpdatableGUINode;

public final class InventoryGrid extends GridPane implements UpdatableGUINode{

    //number of Vertical Columns in GridPane
    private final int columns;
    private final ServiceManager serviceManager;
    private String productType = "chips";


    /**
     * Constructor
     * @param serviceManager
     * @param columns 
     */
    public InventoryGrid(ServiceManager serviceManager, int columns){
        this.serviceManager = serviceManager;
        this.columns = columns;
        updateNode();


        //TODO: Add Any universal GridPane Formatting

        this.setHgap(5);
        this.setPadding(new Insets(0,5,0,5));
        this.setMaxWidth(100);
        //this.setMaxHeight(100);
        this.setStyle("-fx-border-color: #54428E; -fx-border-width: 5px;");
    }

    /**
     * get the number of a particular product type and return it.
     *
     * @param productType
     * @return int
     */
    public int numOfProductType(String productType){
        int drinkCount = 0;
        int chipCount = 0;
        int candyCount = 0;
        int gumCount = 0;

        for(int i = 0; i < serviceManager.getIManager().getProducts().size(); i++){
            if(serviceManager.getIManager().getProducts().get(i) instanceof Drink){
                drinkCount++;
            }else if(serviceManager.getIManager().getProducts().get(i) instanceof Chips){
                chipCount++;
            }else if(serviceManager.getIManager().getProducts().get(i) instanceof Candy){
                candyCount++;
            }else if(serviceManager.getIManager().getProducts().get(i) instanceof Gum){
                gumCount++;
            }
        }

        switch (productType) {
            case "drink":
                return drinkCount;
            case "chips":
                return chipCount;
            case "candy":
                return candyCount;
            case "gum":
                return gumCount;
            default:
                return serviceManager.getIManager().getProducts().size();
        }

    }

    /**
     * Sets what to display on product grid
     */
    @Override
    public void updateNode() {
        //Clear all previouse grid nodes.
        this.getChildren().clear();

        // counter to keep track of number of products added.
        int counter = 0;

        //counter to keep track of index of the serviceManager.getIManager().getProducts()
        int productCounter = 0;

        //Loop through the number of product and add a productPane to the grid for it.
        for (int index = 0; index < serviceManager.getIManager().getProducts().size(); index++) {

            for (int j = 0; j < getColumns(); j++) {
                //if Product is a type add it to the gridPane and increase counter.
                if(productCounter >= serviceManager.getIManager().getProducts().size()){
                    break;
                }
                Product product = serviceManager.getIManager().getProducts().get(productCounter);
                switch (productType) {
                    case "chips":
                        if (product instanceof Chips) {
                            this.add(new InventoryPane(serviceManager, product), j, index);
                            counter++;
                        }
                        break;
                    case "candy":
                        if (product instanceof Candy) {
                            this.add(new InventoryPane(serviceManager, product), j, index);
                            counter++;
                        }
                        break;
                    case "gum":
                        if (product instanceof Gum) {
                            this.add(new InventoryPane(serviceManager, product), j, index);
                            counter++;
                        }
                        break;
                    case "drink":
                        if (product instanceof Drink) {
                            this.add(new InventoryPane(serviceManager, product), j, index);
                            counter++;
                        }
                        break;

                }

                //Increase serviceManager.getIManager().getProducts() index
                productCounter++;

            }
            //If counter is greater than the number of products on the list break the loop
            if(counter > numOfProductType(productType) || productCounter >= serviceManager.getIManager().getProducts().size()){
                break;
            }

        }
    }

    public int getColumns() {
        return columns;
    }

    public void setProductType(String productType) {
        this.productType = productType;
        updateNode();
    }
}
