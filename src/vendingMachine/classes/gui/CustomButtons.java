/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/20/18
 *
 * @about Class to create custom buttons with images.
 */

package vendingMachine.classes.gui;

import javafx.geometry.NodeOrientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomButtons extends Button {

    public CustomButtons(String imagePath, String btnName){

        //Image that will be displayed on the button
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        //set graphic ImageView of button
        this.setGraphic(imageView);

        //Set the tool tip for the button
        this.setTooltip(new Tooltip("Press to view " + btnName));
    }
}
