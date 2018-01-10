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

/**
 *
 * @author jamesray
 */
public class Gum extends Snack {

    /**
     * Flavor of the gum
     */
    private String flavor;

    /**
     * How many pieces of gum in each package.
     */
    private int pieces;

    /**
     * Serving size of gum
     */
    private double servingSize;

    /**
     * sugar free status of the gum
     */
    private boolean sugarFree;

    /**
     * Create gum with generic information.
     */
    public Gum() {
        super();
        flavor = "Peppermint";
        pieces = ThreadLocalRandom.current().nextInt(5, 16);
        servingSize = ThreadLocalRandom.current().nextDouble(1, 2);
        sugarFree = ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Create gum with specific information
     *
     * @param flavor
     * @param pieces
     * @param servingSize
     * @param sugarFree
     * @param weight
     * @param calories
     * @param productName
     * @param price
     * @param quantity
     * @param dispenceLocation
     * @param imageName
     */
    public Gum(String flavor, int pieces, double servingSize, boolean sugarFree, double weight, int calories, String productName, double price, int quantity, String dispenceLocation, String imageName) {
        super(weight, calories, productName, price, quantity, dispenceLocation, imageName);
        this.flavor = flavor;
        this.pieces = pieces;
        this.servingSize = servingSize;
        this.sugarFree = sugarFree;
    }

    /**
     * Returns the string version of the class.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "\nGum{" + "flavor=" + flavor + ", pieces=" + pieces + ", servingSize=" + servingSize + ", sugarFree=" + sugarFree + '}';
    }

    /**
     * Returns the flavor of the gum.
     *
     * @return String
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of the gum
     *
     * @param flavor
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * Get the number of pieces of gum
     *
     * @return int
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * Sets the number of pieces of gum
     *
     * @param pieces
     */
    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    /**
     * get the serving size of the gum
     *
     * @return double
     */
    public double getServingSize() {
        return servingSize;
    }

    /**
     * set the serving size of the gum.
     *
     * @param servingSize
     */
    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    /**
     * Returns the sugar free satus of the gum
     *
     * @return boolean
     */
    public boolean isSugarFree() {
        return sugarFree;
    }

    /**
     * sets the sugar free status of the gum.
     *
     * @param sugarFree
     */
    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

}
