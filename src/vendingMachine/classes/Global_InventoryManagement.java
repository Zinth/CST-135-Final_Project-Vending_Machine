package vendingMachine.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vendingMachine.classes.CsvUtil;
import vendingMachine.classes.products.Product;
import com.opencsv.CSVReader;
import vendingMachine.classes.products.Candy;


public class Global_InventoryManagement extends Product {

    ArrayList<Product>allProducts = new ArrayList<Product>();
    ArrayList<Product> nameSearchResults = new ArrayList<Product>();
    ServiceManager serviceManager;

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
            System.out.println(inventoryList);

            
            for (int i = 0; i < inventoryList.size(); i++){
                String vendingMachineName = inventoryList.get(i)[0];
                serviceManager.getVmManager().addVendingMachine(vendingMachineName);
                switch (inventoryList.get(i)[1]){
                    case "Candy":
                        Product candy = new Candy();
                        serviceManager.getVmManager().getVendingMachine(vendingMachineName).add(candy);
                        addCandy(inventoryList.get(i));
                        break;

                    case "Chips":
                        addChips(inventoryList.get(i));
                        break;

                    case "Drink":
                        addDrink(inventoryList.get(i));
                        break;

                    case "Gum":
                        addGum(inventoryList.get(i));
                        break;
                }
            }
    }
    private void addChips(String[] strings) {
    }
    private void addCandy(String[] strings) {
    }
    private void addDrink(String[] strings) {
    }
    private void addGum(String[] strings) {
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
        File file = new File("callStack");

        PrintWriter writer = new PrintWriter(file);

        if (file.exists()){
            writer.print("");
        }
        for (StackTraceElement line : Thread.currentThread().getStackTrace()){
            writer.println(line.toString());
        }
        writer.close();
    }
    
//    public void exportToCsv(String vendingMachineName){
//        ArrayList<Product> allProducts = findLowInventory(vendingMachineName);
//        CsvUtil csvUtil = new CsvUtil(vendingMachineName+"_purchaseOrder.csv");
//        csvUtil.writeLine(PO_HEADER);
//        lowInventoryProducts.stream().forEach((product) -> {
//            csvUtil.writeLine(product.toArrayList());
//        });
//        csvUtil.finalizeWrite();
//    }

}
