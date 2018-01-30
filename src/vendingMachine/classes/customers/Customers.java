/*
 * Simple Customer creation class.
 */
package vendingMachine.classes.customers;

/**
 *
 * @author Chris
 */
public class Customers {
    
    private String name; 
    private String wantedItem;
    
    /**
     * Constructor for Customers
     * @param name
     * @param wantedItem 
     */
    public Customers(String name, String wantedItem){
        this.name = name; 
        this.wantedItem = wantedItem; //Products they will want to purchase. 
    }
    
    @Override
    public String toString(){
        return name;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return wantedItems
     */
    public String getWantedItem() {
        return wantedItem;
    }

    /**
     * Set wantedItems
     * @param wantedItems 
     */
    public void setWantedItems(String wantedItem) {
        this.wantedItem = wantedItem;
    }
    
    
    
}
