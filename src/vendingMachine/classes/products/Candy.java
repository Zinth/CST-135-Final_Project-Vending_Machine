/**
 * @project Milestone2
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Jesus Leon
 * @author Robert Wayde
 * @teacher Dr. Lively
 * @date 12/14/17
 */
package vendingMachine.classes.products;

import java.util.concurrent.ThreadLocalRandom;

public class Candy extends Snack {

    /**
     * Serving size of the candy
     */
    private double servingSize;

    /**
     * flavor of the candy
     */
    private String flavor;

    /**
     * Is the candy sugar free
     */
    private boolean sugarFree;

    /**
     * is the candy gluten free
     */
    private boolean glutenFree;

    /**
     * Create candy with generic information.
     */
    public Candy() {
        super();
        servingSize = ThreadLocalRandom.current().nextDouble(1, 5);
        flavor = "Chocolate";
        sugarFree = ThreadLocalRandom.current().nextBoolean();
        glutenFree = ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Create Candy with specific information.
     *
     * @param servingSize
     * @param flavor
     * @param sugarFree
     * @param glutenFree
     * @param weight
     * @param calories
     * @param productName
     * @param price
     * @param quantity
     * @param dispenceLocation
     * @param imageName
     */
    public Candy(double servingSize, String flavor, boolean sugarFree, boolean glutenFree, double weight, int calories, String productName, double price, int quantity, String dispenceLocation, String imageName) {
        super(weight, calories, productName, price, quantity, dispenceLocation, imageName);
        this.servingSize = servingSize;
        this.flavor = flavor;
        this.sugarFree = sugarFree;
        this.glutenFree = glutenFree;
    }

    /**
     * Copy constructor
     *
     * @param candy
     */
    public Candy(Candy candy) {
        this(candy.getServingSize(), candy.getFlavor(), candy.isSugarFree(), candy.isGlutenFree(), candy.getWeight(), candy.getCalories(), candy.getProductName(), candy.getPrice(), candy.getQuantity(), candy.getDispenceLocation(), candy.getImageName());
    }

    /**
     * Return the string for candy class.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "\nCandy{" + "servingSize=" + servingSize + ", flavor=" + flavor + ", sugarFree=" + sugarFree + ", glutenFree=" + glutenFree + '}';
    }

    /**
     * Gets the flavor of the candy
     *
     * @return String
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * sets the flavor of the candy
     *
     * @param flavor
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * gets the service size of the candy.
     *
     * @return double
     */
    public double getServingSize() {
        return servingSize;
    }

    /**
     * sets the serving size of the candy
     *
     * @param servingSize
     */
    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    /**
     * Checks if the candy is sugar free.
     *
     * @return boolean
     */
    public boolean isSugarFree() {
        return sugarFree;
    }

    /**
     * sets the sugar free status.
     *
     * @param sugarFree
     */
    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

    /**
     * Checks if the candy is gluten free.
     *
     * @return boolean
     */
    public boolean isGlutenFree() {
        return glutenFree;
    }

    /**
     * sets if the gluten free status.
     *
     * @param glutenFree
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

}
