/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/30/18
 *
 * @about Class to show the info of the customer being served.
 */
package vendingMachine.classes.gui.panes;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.customers_simulation.Customers;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class CustomerInfoPane extends HBox implements UpdatableGUINode{
    private ServiceManager serviceManager;
    //Sets customer to the first customer in the customerQueue
    private Customers customer;
    private Label nameLabel = new Label();
    private Label productWantedLabel = new Label();
    private Label productPurchasedLabel = new Label();
    private Label priceLabel = new Label();
    private TextArea alertLog;
    
    public CustomerInfoPane(ServiceManager serviceManager){
        this.serviceManager = serviceManager;
        
        alertLog = serviceManager.getEventLog();
        
        //Set general Style
        this.setStyle("-fx-background-color: #54428E");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        
        updateNode();
        //Set Up Customer info VBox
        VBox infoVBox = new VBox();
        infoVBox.setAlignment(Pos.TOP_LEFT);
        
        Label infoHeader = new Label("Customer Info:");
        infoHeader.getStyleClass().add("labelHeader");
        
        infoVBox.getChildren().addAll(infoHeader,
                new Separator(),
                infoLine("Customer Name", nameLabel),
                infoLine("Looking for", productWantedLabel),
                infoLine("Purchased Item", productPurchasedLabel),
                infoLine("Purchase Price", priceLabel));

        //Set up the alerLog
        VBox logVBox = new VBox();
        logVBox.setAlignment(Pos.TOP_LEFT);
        
        Label logHeader = new Label("Customer Action Log");
        logHeader.getStyleClass().add("labelHeader");
        
        logVBox.getChildren().addAll(logHeader, new Separator(), alertLog);
        
        //Property Settings of alertBox
        alertLog.setEditable(false);
        alertLog.setMaxSize(USE_PREF_SIZE, 125);
        
        Separator separator = new Separator(Orientation.VERTICAL);
        
        //Add Nodes to this HBox
        this.getChildren().addAll(infoVBox, separator, logVBox);
        
        updateVisible();
        
    }
    
    public HBox infoLine(String string, Label label){
        HBox info = new HBox();
        Label lineHeader = new Label(string + ": ");
        lineHeader.getStyleClass().add("labelBlack");
        
        info.getChildren().addAll(lineHeader, label);
        return info;
    }
 
    /**
     * update the text of the customer info Labels
     */
    public void updateCustomerInfo(){
        nameLabel.setText(customer.getName());
        productWantedLabel.setText(customer.getWantedItem());
        serviceManager.getTotalPrice().updateNode();
    }
    /**
     * Only show this if customerQueMode is true
     */
     public void updateVisible(){
         if (serviceManager.isCustomerQueMode() == true && serviceManager.isManagerMode() == false) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
         System.out.println(serviceManager.isCustomerQueMode());
    }
    
    public void updateNode(){
        customer = serviceManager.getCustomerQueue().getCustomerQue().getFirst();
        updateCustomerInfo();
    }

    public Label getProductPurchasedLabel() {
        return productPurchasedLabel;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }
    
    
    
}
