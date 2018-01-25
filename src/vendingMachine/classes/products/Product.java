/**
 * @project Milestone2
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Jesus Leon
 * @author Robert Wayde
 * @teacher Dr. Lively
 * @date 12/11/17
 */
package vendingMachine.classes.products;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import vendingMachine.classes.ServiceManager;

abstract public class Product {

    /**
     * Product name
     */
    private String productName;

    /**
     * price of the product.
     */
    private double price;

    /**
     * available quantity of the product.
     */
    private int quantity;

    /**
     * Image icon file name of product
     */
    private String imageName;

    /**
     * Id of the product
     */
    protected int productId;

    /**
     * Create a product with generic information.
     */
    public Product() {
        productName = "Product Name Here";
        double randomPrice = ThreadLocalRandom.current().nextDouble(0.5, 6);
        DecimalFormat dec = new DecimalFormat("#.00");
        price = Double.parseDouble(dec.format(randomPrice));
        int randomQuantity = ThreadLocalRandom.current().nextInt(1, 11);
        quantity = randomQuantity;
        imageName = "sold_out";
    }

    /**
     * Create a product with specific information.
     *
     * @param productName
     * @param price
     * @param quantity
     * @param imageName
     */
    public Product(String productName, double price, int quantity, String imageName) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.imageName = imageName;
    }

    /**
     * Copy constructor
     *
     * @param product
     */
    public Product(Product product) {
        this(product.getProductName(), product.getPrice(), product.getQuantity(), product.getImageName());
    }

    /**
     * Returns a string of what the product is.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Product{" + "productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", dispenceLocation=" + '}';
    }
    
    /**
     * @return ArrayList<String>
     */
    public ArrayList<String> toArrayList(){
        ArrayList<String> properties = new ArrayList<>();
        properties.add(productName);
        properties.add(String.valueOf(price));
        properties.add(String.valueOf(quantity));
        properties.add(imageName);
        return properties;
    }

    /**
     * Returns the product Name
     *
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param name
     */
    public void setProductName(String name) {
        this.productName = name;
    }

    /**
     * Returns the price of a product
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of a product
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the available quantity of the product
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity of the product
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of a product by one.
     */
    public void increaseQuantity() {
        this.quantity++;
    }

    /**
     * Increates the quantity of a product by int.
     *
     * @param increase
     */
    public void increaseQuantity(int increase) {
        this.quantity += increase;
    }

    /**
     * Decreases the quantity of a product by one.
     */
    public void decreaseQuantity() {
        this.quantity--;
    }

    /**
     * Decreases the quantity of a product by int
     *
     * @param decrease
     */
    public void decreaseQuantity(int decrease) {
        this.quantity -= decrease;
    }

    /**
     * Returns the name of the image to be displayed.
     *
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * sets the location of the item
     *
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Returns the product id.
     * @return int
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product id.
     * @param productId 
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int compareName(Product prod) {
        if (this.getProductName().charAt(0) > prod.getProductName().charAt(0)) {
            return 1;
        } else if (this.getProductName().charAt(0) == prod.getProductName().charAt(0) ) {
            if (this.getProductName().charAt(1) > prod.getProductName().charAt(1)) {
                return 1;
            } else if (this.getProductName().charAt(1) == prod.getProductName().charAt(1) ) {
                if (this.getProductName().charAt(2) > prod.getProductName().charAt(2)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public int compareQuantity(Product product) {
        if (this.getQuantity() > product.getQuantity()) {
            return 1;
        } else {
            return 0;
        }
    }
}
