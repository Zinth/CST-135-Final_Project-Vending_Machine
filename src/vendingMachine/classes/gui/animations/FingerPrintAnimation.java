/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/29/18
 *
 * @about Class to create custom buttons with images.
 */
package vendingMachine.classes.gui.animations;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import vendingMachine.interfaces.UpdatableGUINode;

/**
 *
 * @author Chris
 */
public class FingerPrintAnimation extends ImageView implements UpdatableGUINode{

    
    /**
     * Constructor
     */
    FingerPrintAnimation(){
        //imageView Image
        this.setImage(new Image("res/images/fingerPrint.png"));
        //Animation
        FadeTransition fadeTrans = new FadeTransition(Duration.millis(50), this); //Set animation duration and node in constructor
        fadeTrans.setFromValue(1.0); // Set start Opaqueness
        fadeTrans.setToValue(0.0); // set end opaqueness
        fadeTrans.setCycleCount(1); // set cycleCount
        fadeTrans.setAutoReverse(false); // set if autoreverse is on
    }
    
    
    
    @Override
    public void updateNode() {
        //rest the opasity
        this.setOpacity(1.0);
        
    }
    
    
    
}
