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
        Product newGum = new Gum("Watermelon", 8, 1, true, 13.3, 1, "Trident", 0.50, 10, "C5", "sold_out");

        // Use the Dispenser change method
        vendingMachine.changeProduct(14, newGum);

        System.out.println("AFTER: " + vendingMachine.getProductList().get(14)); // print product after change

    }

}
