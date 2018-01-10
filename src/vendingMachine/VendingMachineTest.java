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
package vendingMachine;

import vendingMachine.classes.*;
import vendingMachine.classes.products.Chips;
import vendingMachine.classes.products.Drink;
import vendingMachine.classes.products.Gum;
import vendingMachine.classes.products.Product;

public class VendingMachineTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // New Dispenser instance called vendingMachine
        Dispenser vendingMachine = new Dispenser(20.00, 0);

        //  -- Test vendingMachines toString() method. --
        System.out.println(vendingMachine + "\n");

        // -- Test Changing a product in the Dispenser --
        System.out.println("BEFORE: " + vendingMachine.getProductList().get(14) + "\n"); // print product before change

        // Create product to that will take the place of index 14
        Product newGum = new Gum("Watermelon", 8, 1, true, 13.3, 1, "Trident", 0.50, 10, "C5");

        // Use the Dispenser change method
        vendingMachine.changeProduct(14, newGum);

        System.out.println("AFTER: " + vendingMachine.getProductList().get(14)); // print product after change

        //TODO: Replase temp test code with actual test code for drinks and snacks as per Milestone 3 requirements
        // -- Start temp test code
        Drink coke = new Drink(12, "Soft Drink", 140, false, 1, "Coca-Cola", 1.25, 10, "D1");
        Drink dietCoke = new Drink(12, "Soft Drink", 0, true, 1, "Diet Coca-Cola", 1.25, 10, "D2");
        Drink sameDietCoke = new Drink(dietCoke);
        Drink dCoke = new Drink(12, "Soft Drink", 0, true, 1, "Diet Coca-Cola", 1.50, 10, "D2");

        Chips chedderChips = new Chips("Cheddar", 1, true, true, 10, 200, "Lays Cheddar Chips", 10, 1, "C1");
        Chips saltedChips = new Chips("Salted", 1, true, true, 10, 100, "Lays Salted Chips", 12, 1, "C1");
        Chips sameSaltedChips = new Chips(saltedChips);
        Chips saltedChipsLowerPrice = new Chips("Salted", 1, true, true, 10, 100, "Lays Salted Chips", 11, 1, "C1");
        
        System.out.println("\n Snack name comparison test: " + chedderChips.compareTo(saltedChips)); // should return 1
        System.out.println("\n Snack same comparison test: " + saltedChips.compareTo(sameSaltedChips)); // should return 0
        System.out.println("\n Snack higher price comparison test: " + saltedChips.compareTo(saltedChipsLowerPrice)); // should return -1

        System.out.println("\n Drink name comparison test: " + coke.compareTo(dietCoke)); // should return 1
        System.out.println("\n Drink same comparison test: " + dietCoke.compareTo(sameDietCoke)); // should return 0
        System.out.println("\n Drink higher price comparison test: " + dCoke.compareTo(dietCoke)); // should return -1

        //-- End temp test code
    }

}
