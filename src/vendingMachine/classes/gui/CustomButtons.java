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
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        //set graphic ImageView of button
        this.setGraphic(imageView);

        this.setStyle("-fx-background-color:\n" +
                "        linear-gradient(#f0ff35, #a9ff00),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: \n" +
                    "        linear-gradient(#ffd65b, #e68400),\n" +
                    "        linear-gradient(#ffef84, #f2ba44),\n" +
                    "        linear-gradient(#ffea6a, #efaa22),\n" +
                    "        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n" +
                    "        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));");
        });
        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color:\n" +
                    "        linear-gradient(#f0ff35, #a9ff00),\n" +
                    "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);");

        });

        //Set the tool tip for the button
        this.setTooltip(new Tooltip("Press to view " + btnName));
    }
}
