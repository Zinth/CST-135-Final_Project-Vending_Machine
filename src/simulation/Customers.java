/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/30/18
 *
 * @about Class for making customers
 */
package simulation;

import vendingMachine.views.animations.IdleAnimation;

public class Customers {
    
    private String name; 
    private String wantedItem;
    private String category;
    private IdleAnimation animation;
    
    /**
     * Constructor for Customers
     * @param name
     * @param wantedItem 
     */
    public Customers(String name, String category, String wantedItem){
        this.name = name; 
        this.wantedItem = wantedItem; //Products they will want to purchase.
        this.category = category;
        this.animation = new IdleAnimation(name);
    }
    
    /**
     * @return 
     */
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

    /**
     * @return category
     */
    public String getCatagory() {
        return category;
    }

    /**
     * set category
     * @param category 
     */
    public void setCatagory(String catagory) {
        this.category = catagory;
    }

    /**
     * @return animation
     */
    public IdleAnimation getAnimation() {
        return animation;
    }
}
