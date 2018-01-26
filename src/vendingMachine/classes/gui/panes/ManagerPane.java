/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/16/18
 *
 * @about This class creates Panes of products for the manager.
 */

package vendingMachine.classes.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vendingMachine.classes.ServiceManager;
import vendingMachine.classes.products.Product;

public class ManagerPane extends ProductPane {

    public ManagerPane(ServiceManager serviceManager, Product product){
        super(serviceManager, product);

        Button btnIncrease = new Button("+");
        btnIncrease.setOnAction(event -> {
            serviceManager.getIManager().increaseQuantity(product);
            updateStockLabel(product);
            serviceManager.UpdateCartGui();
            serviceManager.UpdateInventoryGui();
        });

        Button btnDecrease = new Button("-");
        btnDecrease.setOnAction(event -> {
            if(product.getQuantity() > 0){
                serviceManager.getIManager().decreaseQuantity(product);
                updateStockLabel(product);
                serviceManager.UpdateCartGui();
                serviceManager.UpdateInventoryGui();
            }
        });
        
        //Button set text Font
        btnDecrease.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        btnDecrease.setTextFill(Color.WHITE);
        btnIncrease.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        btnIncrease.setTextFill(Color.WHITE);
        
        //Set buttons default Style
        btnDecrease.setStyle(BTN_NORMAL);
        btnIncrease.setStyle(BTN_NORMAL);
        
        //Set buttons style on hover
        btnDecrease.setOnMouseEntered(e -> {btnDecrease.setStyle(BTN_HOVER);});
        btnIncrease.setOnMouseEntered(e -> {btnIncrease.setStyle(BTN_HOVER);});
        
        //Set buttons style back to normal on mouse exit
        btnDecrease.setOnMouseExited(e -> {btnDecrease.setStyle(BTN_NORMAL);});
        btnIncrease.setOnMouseExited(e -> {btnIncrease.setStyle(BTN_NORMAL);});

        HBox btnHBox = new HBox();
        btnHBox.setPadding(new Insets(10));
        btnHBox.getChildren().addAll(btnIncrease, btnDecrease);

        this.getChildren().addAll(btnHBox);
    }
}
