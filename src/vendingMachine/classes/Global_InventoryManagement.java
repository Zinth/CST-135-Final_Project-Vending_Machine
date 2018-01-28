package vendingMachine.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.scene.paint.Color;
import vendingMachine.classes.products.Product;
import vendingMachine.classes.products.Candy;
import vendingMachine.classes.products.Chips;
import vendingMachine.classes.products.Drink;
import vendingMachine.classes.products.Gum;


public class Global_InventoryManagement extends Product {

    ArrayList<Product> allProducts = new ArrayList<Product>();
    ArrayList<Product> nameSearchResults = new ArrayList<Product>();
    ServiceManager serviceManager;
    private final static ArrayList<String> EXPORT_HEADER = new ArrayList<String>() {{
        add("Machine");
        add("Type");
        add("Name");
        add("Price");
        add("Quantity");
        add("imageName");
        add("Option 1");
        add("Option 2");
        add("Option 3");
        add("Option 4");
        add("Option 5");
        add("Option 6");
    }};

    Global_InventoryManagement(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
    }

    /**
     *
     * csvInventoryImport()
     *
     * Imports inventory from CSV file.
     */

    public void csvInventoryImport(String path){

        //Read from CSV file.
            CsvUtil inventory = new CsvUtil(path);

            List<String[]> inventoryList = inventory.readCSV();

            
            for (int i = 0; i < inventoryList.size(); i++){
            String vendingMachineName = inventoryList.get(i)[0];
            serviceManager.getVmManager().addVendingMachine(vendingMachineName);
            switch (inventoryList.get(i)[1]){
                case "Candy":
                    Product candy = addCandy(inventoryList.get(i));
                    serviceManager.getVmManager().getVendingMachine(vendingMachineName).add(candy);
                    break;

                case "Chips":
                    Product chips = addChips(inventoryList.get(i));
                    serviceManager.getVmManager().getVendingMachine(vendingMachineName).add(chips);
                    break;

                case "Drink":
                    Product drink = addDrink(inventoryList.get(i));
                    serviceManager.getVmManager().getVendingMachine(vendingMachineName).add(drink);
                    break;

                case "Gum":
                    Product gum = addGum(inventoryList.get(i));
                    serviceManager.getVmManager().getVendingMachine(vendingMachineName).add(gum);
                    break;
            }
        }



            recursiveSortName(allProducts, 2, allProducts.size());
            recursiveSortQuantity(allProducts, 2, allProducts.size());

            String productSearchName = "Lays";

            recursiveSearchByName(productSearchName, allProducts, 2, allProducts.size());
            System.out.println("-----Name Search Results-----");

            if (nameSearchResults.size() > 0){
                System.out.println("Searched for " + productSearchName + "...");

                for (Product product : nameSearchResults) {
                    System.out.println("Dispenser ID: " + product.getProductName() + ", Qty: " + product.getQuantity());
                }
            } else {
                System.out.println("Product name " + productSearchName + " was not found");
            }

            System.out.println("-------------- Sorted Products --------------");
            for (Product prod : allProducts) {
            System.out.println(prod.getProductName() + " : " + prod.getQuantity());
        }

    }
    private Product addChips(String[] strings) {
        double servingSize = Double.valueOf(strings[7]);
        String flavor = strings[6];
        boolean baked = Boolean.valueOf(strings[8]);
        boolean glutenFree = Boolean.valueOf(strings[9]);
        double weight = Double.valueOf(strings[10]);
        int calories = Integer.valueOf(strings[11]);
        String productName = strings[2];
        double price = Double.valueOf(strings[3]);
        int quantity = Integer.valueOf(strings[4]);
        String imageName = strings[5];
        Product chips = new Chips(flavor, servingSize, baked, glutenFree, weight, calories, productName, price, quantity, imageName);
        allProducts.add(chips);
        return chips;
    }
    private Product addCandy(String[] strings) {
        double servingSize = Double.valueOf(strings[6]);
        String flavor = strings[7];
        boolean sugarFree = Boolean.valueOf(strings[8]);
        boolean glutenFree = Boolean.valueOf(strings[9]);
        double weight = Double.valueOf(strings[10]);
        int calories = Integer.valueOf(strings[11]);
        String productName = strings[2];
        double price = Double.valueOf(strings[3]);
        int quantity = Integer.valueOf(strings[4]);
        String imageName = strings[5];
        Product candy = new Candy(servingSize, flavor, sugarFree, glutenFree, weight, calories, productName, price, quantity, imageName);   
        allProducts.add(candy);
        return candy;
    }
    private Product addDrink(String[] strings) {
        double ounces = Double.valueOf(strings[6]);
        String type = strings[7];
        int calories = Integer.valueOf(strings[8]);
        boolean sugarFree = Boolean.valueOf(strings[9]);
        int caffeine = Integer.valueOf(strings[10]);
        String productName = strings[2];
        double price = Double.valueOf(strings[3]);
        int quantity = Integer.valueOf(strings[4]);
        String imageName = strings[5];
        Product drink = new Drink(ounces, type, calories, sugarFree, caffeine, productName, price, quantity, imageName);
        allProducts.add(drink);
        return drink;
    }
    private Product addGum(String[] strings) {
        String flavor = strings[6];
        int pieces = Integer.valueOf(strings[7]);
        double servingSize = Double.valueOf(strings[8]);
        boolean sugarFree = Boolean.valueOf(strings[9]);
        double weight = Double.valueOf(strings[10]);
        int calories = Integer.valueOf(strings[11]);
        String productName = strings[2];
        double price = Double.valueOf(strings[3]);
        int quantity = Integer.valueOf(strings[4]);
        String imageName = strings[5];
        Product gum = new Gum(flavor, pieces, servingSize, sugarFree, weight, calories, productName, price, quantity, imageName);
        allProducts.add(gum);
        return gum;
    }

    /**
     * recursiveSortName
     *
     * @param allProducts
     * @param Index
     * @param n
     */
    public void recursiveSortName(ArrayList<Product> allProducts, int Index, int n){
        if (Index >= n)
            return;

        //Compare names
        for (int i = 0; i < allProducts.size(); i++){
            //Verify that we are not comparing to itself.
            if (i != Index){
                Product tempProduct = allProducts.get(i);

                int result = allProducts.get(Index).compareName( tempProduct );

                if (result == 1){
                    allProducts.set(i, allProducts.get(Index));
                    allProducts.set(Index,tempProduct);
                }
            }
        }

        recursiveSortName(allProducts, Index + 1, n);
    }

    /**
     *
     * recursiveSortQuantity
     *
     * Sort by quantity recursively.
     *
     * @param allProducts
     * @param Index
     * @param n
     *
     */
    public void recursiveSortQuantity(ArrayList<Product> allProducts, int Index, int n){
        if (Index >= n)
            return;

        //This step compares the quantity.
        for (int i = 0; i < allProducts.size(); i++){
            //We do not want to compare to itself.
            if (i != Index){
                Product tempProduct = allProducts.get(i);

                if (allProducts.get(Index).getProductId() == tempProduct.getProductId()){

                    int result = allProducts.get(Index).compareQuantity( tempProduct );

                    if (result == 1){
                        //Verify that the index is higher or do not swap.
                        if (Index < 1){
                            allProducts.set(i, allProducts.get(Index));
                            allProducts.set(Index, tempProduct);
                        }
                    }
                }
            }
        }

        recursiveSortQuantity(allProducts, Index + 1, n);
    }

    /**
     *
     * recursiveSearchByName()
     *
     * This searches for a specific product by name in the array.
     *
     * @param productName
     * @param allProducts
     * @param Index
     * @param n
     */
    public void recursiveSearchByName(String productName, ArrayList<Product> allProducts, int Index, int n){
        if (Index >= n)
            return;

        if (allProducts.get(Index).getProductName().equals( productName )){
            nameSearchResults.add(allProducts.get(Index));
        }

        //Write to the call stack file
        try {
            writeToCallStackFile();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        recursiveSearchByName(productName, allProducts, Index + 1, n);
    }
    /**
     *
     * writeToCallStackFile()
     *
     * This writes the search to a file.
     *
     * @throws FileNotFoundException
     */
    public void writeToCallStackFile() throws FileNotFoundException{
        File file = new File("callStack.txt");

        PrintWriter writer = new PrintWriter(file);

        if (file.exists()){
            writer.print("");
        }
        for (StackTraceElement line : Thread.currentThread().getStackTrace()){
            writer.println(line.toString());
        }
        writer.close();
    }
    
    public void exportAll(){
        Set<String> vendingMachinesNames = this.serviceManager.getVmManager().getVendingMachineNames();
        vendingMachinesNames.stream().forEach((vendingMachineName) -> {
            exportToCsv(vendingMachineName);
        });
        serviceManager.getALERT().showAlert("Inventory exported for all Vending Machines!", 20, Color.CORAL);
    }
    
    public void exportToCsv(String vendingMachineName){
        ArrayList<Product> allProducts = serviceManager.getVmManager().getVendingMachine(vendingMachineName);
        CsvUtil csvUtil = new CsvUtil(vendingMachineName+"_inventory.csv");
        csvUtil.writeLine(EXPORT_HEADER);
        allProducts.stream().forEach((product) -> {
            ArrayList<String> productInfo = new ArrayList<>();
            productInfo.add(0, vendingMachineName);
            productInfo.addAll(product.toArrayList());
            csvUtil.writeLine(productInfo);
        });
        csvUtil.finalizeWrite();
    }

}
