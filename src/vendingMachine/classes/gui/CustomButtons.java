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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomButtons extends Button {
    
    //Custom Button With text
    public CustomButtons(String btnName, EventHandler<ActionEvent> event){
        //Set buttton Text Font and Color
        this.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        this.setTextFill(Color.WHITE);
        
        //set Style
        setBtnStyle();
        
        //set Text
        this.setText(btnName);
        
        //Set Tooltip
        this.setTooltip(new Tooltip("Press to Manage Vending Machine: " + btnName));
        
        //Set event
        this.setOnAction(event);
        
    }

    //Custom Button with image
    public CustomButtons(String imagePath, String btnName){

        //Image that will be displayed on the button
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        //set graphic ImageView of button
        this.setGraphic(imageView);

        //Set button Style
        setBtnStyle();

        //Set the tool tip for the button
        this.setTooltip(new Tooltip("Press to view " + btnName));
    }
    
    public void setBtnStyle(){
        this.setStyle("-fx-background-color: \n"
                + "#090a0c,\n"
                + "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
                + "linear-gradient(#20262b, #191d22),\n"
                + "radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");

        this.setOnMouseEntered(event -> {
            this.setStyle("-fx-background-color: \n"
                + "#000000,\n"
                + "linear-gradient(#7ebcea, #2f4b8f),\n"
                + "linear-gradient(#426ab7, #263e75),\n"
                + "linear-gradient(#395cab, #223768);");
        });

        this.setOnMouseExited(event -> {
            this.setStyle("-fx-background-color: \n"
                + "#090a0c,\n"
                + "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n"
                + "linear-gradient(#20262b, #191d22),\n"
                + "radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));");

        });
    }
    
    
}
