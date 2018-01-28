/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @author Robert Wayde
 * @date 01/19/18
 *
 * @about Class displays all products on a grid for manager use.
 */

package vendingMachine.classes.gui.grids;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import vendingMachine.classes.gui.panes.ManagerPane;
import vendingMachine.classes.ServiceManager;
import vendingMachine.interfaces.UpdatableGUINode;

public class ManagerGrid extends GridPane implements UpdatableGUINode{

    private final ServiceManager serviceManager;
    private final int columns;

    /**
     * Constructor
     * @param serviceManager
     * @param columns 
     */
    public ManagerGrid(ServiceManager serviceManager, int columns) {
        this.serviceManager = serviceManager;
        this.columns = columns;
        
        
        
        //Style
        this.getStyleClass().add("grid");
        this.setMaxWidth(100);

    }

    /**
     * Method for filling the grid.
     */
    @Override
    public void updateNode(){
        int productCounter = 0;
        for(int i = 0; i < serviceManager.getIManager().getProducts().size(); i++){
            for(int j = 0; j < getColumns(); j++){
                if(productCounter >= serviceManager.getIManager().getProducts().size()){
                    break;
                }
                add(new ManagerPane(serviceManager, serviceManager.getIManager().getProducts().get(productCounter)), j, i);
                productCounter++;
            }
        }
    }

    public int getColumns() {
        return columns;
    }
}
