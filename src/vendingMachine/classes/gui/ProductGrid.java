/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @date 01/13/18
 */

package vendingMachine.classes.gui;

import javafx.scene.layout.GridPane;
import vendingMachine.classes.products.*;

import java.util.ArrayList;

public class ProductGrid extends GridPane{

    // ArrayList holder
    private ArrayList<Product> productList;

    //number of Vertical Columns in GridPane
    private int columns;

    /**
     * Main Constructor
     * @param productList
     * @param columns
     */
    public ProductGrid(ArrayList<Product> productList, int columns){
      this.productList = productList;
      this.columns = columns;

      //TODO: Add Any universal GridPane Formatting
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

        for(int i = 0; i < productList.size(); i++){
            if(productList.get(i) instanceof Drink){
                drinkCount++;
            }else if(productList.get(i) instanceof Chips){
                chipCount++;
            }else if(productList.get(i) instanceof Candy){
                candyCount++;
            }else if(productList.get(i) instanceof Gum){
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
                return productList.size();
        }

    }

    /**
     * Sets what to display on product grid
     * @param productType
     */
    public void sortProductGrid(String productType) {
        //Clear all previouse grid nodes.
        this.getChildren().clear();

        // counter to keep track of number of products added.
        int counter = 0;

        //counter to keep track of index of the productList
        int productCounter = 0;

        //Loop through the number of product and add a productPane to the grid for it.
        for (int i = 0; i < productList.size(); i++) {

            for (int j = 0; j < getColumns(); j++) {
                //if Product is a type add it to the gridPane and increase counter.
                switch (productType) {
                    case "drink":
                        if (productList.get(productCounter) instanceof Drink) {
                            this.add(new ProductPane(productList.get(productCounter)), j, i);
                            counter++;
                        }
                        break;
                    case "chips":
                        if (productList.get(productCounter) instanceof Chips) {
                            this.add(new ProductPane(productList.get(productCounter)), j, i);
                            counter++;
                        }
                        break;
                    case "candy":
                        if (productList.get(productCounter) instanceof Candy) {
                            this.add(new ProductPane(productList.get(productCounter)), j, i);
                            counter++;
                        }
                        break;
                    case "gum":
                        if (productList.get(productCounter) instanceof Gum) {
                            this.add(new ProductPane(productList.get(productCounter)), j, i);
                            counter++;
                        }
                        break;
                    default:
                        this.add(new ProductPane(productList.get(productCounter)), j, i);
                        counter++;
                        break;
                }

                //Increase podunctList index
                productCounter++;

            }
            //If counter is greater than the number of products on the list break the loop
            if(counter > numOfProductType(productType) || productCounter >= productList.size()){
                break;
            }

        }
    }

    /**
     * get ArrayList
     * @return productList
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * get columns
     * @return columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * set columns
     * @param columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
}
