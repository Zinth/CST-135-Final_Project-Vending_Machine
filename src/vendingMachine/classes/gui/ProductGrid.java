package vendingMachine.classes.gui;

import javafx.scene.layout.GridPane;
import vendingMachine.classes.products.*;

import java.util.ArrayList;

public class ProductGrid extends GridPane{

    private ArrayList<Product> productList;
    private int columns;

    public ProductGrid(ArrayList<Product> productList, int columns){
      this.productList = productList;
      this.columns = columns;

      //TODO: Add Any universal GridPane Formatting
    }

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

    public void sortProductGrid(String productType) {
        this.getChildren().clear();
        // number
        int counter = 0;
        int productCounter = 0;
        System.out.println(numOfProductType(productType));
        //Loop through the number of product and add a productPane to the grid for it.
        for (int i = 0; i < productList.size(); i++) {

            for (int j = 0; j < getColumns(); j++) {
                //Add products based on productType (default is all products)
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
                productCounter++;

            }
            if(counter > numOfProductType(productType) || productCounter >= productList.size()){
                break;
            }

        }
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
