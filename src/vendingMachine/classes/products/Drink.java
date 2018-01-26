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
import java.util.concurrent.ThreadLocalRandom;

public class Drink extends Product implements Comparable<Drink> {

    /**
     * Ounces in a drink.
     */
    private double ounces;

    /**
     * Type of drink.
     */
    private String type;

    /**
     * number of calories in the drink
     */
    private int calories;

    /**
     * sugar free status of the drink.
     */
    private boolean sugarFree;

    /**
     * amount of caffeine in the drink.
     */
    private int caffeine;

    /**
     * Create a drink with generic information.
     */
    public Drink() {
        super();
        double randomOunces = ThreadLocalRandom.current().nextDouble(1, 11);
        DecimalFormat dec = new DecimalFormat("#.00");
        ounces = Double.parseDouble(dec.format(randomOunces));
        type = "Water";
        sugarFree = ThreadLocalRandom.current().nextBoolean();
        calories = ThreadLocalRandom.current().nextInt(0, 101);
    }

    /**
     * Create a drink with specific information.
     *
     * @param ounces
     * @param type
     * @param calories
     * @param sugarFree
     * @param caffeine
     * @param productName
     * @param price
     * @param quantity
     * @param imageName
     */
    public Drink(double ounces, String type, int calories, boolean sugarFree, int caffeine, String productName, double price, int quantity, String imageName) {
        super(productName, price, quantity, imageName);
        this.ounces = ounces;
        this.type = type;
        this.calories = calories;
        this.sugarFree = sugarFree;
        this.caffeine = caffeine;
    }

    /**
     * Copy constructor
     *
     * @param drink
     */
    public Drink(Drink drink) {
        this(drink.getOunces(), drink.getType(), drink.getCalories(), drink.isSugarFree(), drink.getCaffeine(), drink.getProductName(), drink.getPrice(), drink.getQuantity(), drink.getImageName());
    }

    /**
     * Return string of drink class.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "\nInfo" + "\nOunces: " + ounces + "\nType: " + type + "\nCalories: " + calories  + "\nSugar Free: " + sugarFree;
    }

    /**
     * Compare this drink to another drink greater return 1 lessthan return -1
     * equal return 0.
     *
     * @param drink
     * @return int
     */
    @Override
    public int compareTo(Drink drink) {
        //Check to see if product names are the same
        if (this.getProductName().equalsIgnoreCase(drink.getProductName())) {
            //If so check if price are equal and return result
            if (this.getPrice() == drink.getPrice()) {
                return 0;
            } else if (this.getPrice() > drink.getPrice()) { // Test wich product has the higher price and return them in assending order.
                return -1;
            } else {
                return 1;
            }

        } else if (this.getProductName().compareToIgnoreCase(drink.getProductName()) > 0) {// If not check to see wich name comes sooner in the alphabet and return result in alphabetical order.
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * get the ounces of the drink
     *
     * @return double
     */
    public double getOunces() {
        return ounces;
    }

    /**
     * set the ounces of a drink
     *
     * @param ounces
     */
    public void setOunces(double ounces) {
        this.ounces = ounces;
    }

    /**
     * get the type of drink
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of drink
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the calories for the drink.
     *
     * @return int
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Set the calories for the drink.
     *
     * @param calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Return if the drink is sugar free.
     *
     * @return boolean
     */
    public boolean isSugarFree() {
        return sugarFree;
    }

    /**
     * set the sugar free status of the drink.
     *
     * @param sugarFree
     */
    public void setSugarFree(boolean sugarFree) {
        this.sugarFree = sugarFree;
    }

    /**
     * Get how much caffeine is in the drink.
     *
     * @return int
     */
    public int getCaffeine() {
        return caffeine;
    }

    /**
     * sets how much caffeine is in the drink.
     *
     * @param caffeine
     */
    public void setCaffeine(int caffeine) {
        this.caffeine = caffeine;
    }

}
