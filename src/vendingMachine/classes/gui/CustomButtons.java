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
    
    //Custom Button With text and event
    public CustomButtons(String btnName, EventHandler<ActionEvent> event){
        //Set buttton Text Font and Color
        
        this.setTextFill(Color.WHITE);
        
       
        
        //set Text
        this.setText(btnName);
        
        //Set Tooltip
        this.setTooltip(new Tooltip("Press to " + btnName));
        
        //Set event
        this.setOnAction(event);
        
    }
    
    //Basic button with text
     //Custom Button With text and tool tip
    public CustomButtons(String btnName, Tooltip toolTip){
        //Set buttton Text Font and Color
        
        this.setTextFill(Color.WHITE);
        
        //set Text
        this.setText(btnName);
        this.setTooltip(toolTip);
        
        //Set on click to show tooltip
        this.setOnAction((event) -> {
            toolTip.show(toolTip);
        });
      
    }

    //Custom Button with image
    public CustomButtons(String imagePath, String btnName){

        //Image that will be displayed on the button
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        //set graphic ImageView of button
        this.setGraphic(imageView);
        
        //Set the tool tip for the button
        this.setTooltip(new Tooltip("Press to view " + btnName));
    }
    
 
    
    
}
