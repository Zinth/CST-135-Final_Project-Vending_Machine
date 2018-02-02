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
package vendingMachine.products;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Snack extends Product implements Comparable<Snack> {

    /**
     * weight of the snack
     */
    private double weight;
    /**
     * calories in the snack
     */
    private int calories;

    /**
     * Create a snack with generic information.
     */
    public Snack() {
        super();
        double randomWeight = ThreadLocalRandom.current().nextDouble(1, 11);
        DecimalFormat dec = new DecimalFormat("#.00");
        weight = Double.parseDouble(dec.format(randomWeight));
        calories = ThreadLocalRandom.current().nextInt(5, 256);
    }

    /**
     * Create a snack with specific information.
     *
     * @param weight
     * @param calories
     * @param productName
     * @param price
     * @param quantity
     * @param imageName
     */
    public Snack(double weight, int calories, String productName, double price, int quantity, String imageName) {
        super(productName, price, quantity, imageName);
        this.weight = weight;
        this.calories = calories;
    }

    /**
     * Copy constructor
     *
     * @param snack
     */
    public Snack(Snack snack) {
        this(snack.getWeight(), snack.getCalories(), snack.getProductName(), snack.getPrice(), snack.getQuantity(), snack.getImageName());
    }

    /**
     * Returns a string of what the snack is.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "\nInfo:" +"\nWeight: " + weight + "\nCalories: " + calories;
    }

    /**
     * Compare this snack to another snack greater return 1 less than return -1
     * equal return 0
     *
     * @param snack
     * @return int
     */
    @Override
    public int compareTo(Snack snack) {

        //Check to see if the product names are the same.
        if (this.getProductName().equalsIgnoreCase(snack.getProductName())) {
            //If they are the same, check to see if the price is equal
            if (this.getPrice() == snack.getPrice()) {
                return 0;
            } else if (this.getPrice() > snack.getPrice()) { //Test which product has the higher price and return them in ascending order.
                return -1;
            } else {
                return 1;
            }
        } else if (this.getProductName().compareToIgnoreCase(snack.getProductName()) > 0) { //If they have the same price, check which name comes sooner and return result in alphabetical order.
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Gets the weight of the snack
     *
     * @return double
     */
    public double getWeight() {
        return weight;
    }

    /**
     * set the weight of a snack.
     *
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * get the calories in the snack.
     *
     * @return int
     */
    public int getCalories() {
        return calories;
    }

    /**
     * sets the calories in the snack.
     *
     * @param calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

}
