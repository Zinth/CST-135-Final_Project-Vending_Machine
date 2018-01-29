/**
 * @project Final Project - Vending Machine
 * @about Create and orginize GridPane of products.
 * @course CST-135
 * @author Christopher Hyde
 * @date 01/30/18
 *
 * @about Create a pane that plays an animation of an idle customer
 */
package vendingMachine.classes.gui.animations;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 *
 * @author Chris
 */
public class IdleAnimation extends Pane {
    //Create all Images as Final
private final ImageView idleOne = new ImageView(new Image("res/animations/idleOne.png"));
private final ImageView idleTwo = new ImageView(new Image("res/animations/idleTwo.png"));
private final ImageView idleThree = new ImageView(new Image("res/animations/idleThree.png"));
private final ImageView idleFour = new ImageView(new Image("res/animations/idleFour.png"));

//Group to hold image view
private Group idleAnimation;

public IdleAnimation(){
    //Create and set first image in the group idleAnimation
    idleAnimation = new Group(idleOne);
    
    //Create the Timeline that will switch images at keyframe intervals
    Timeline animation = new Timeline();
    animation.setCycleCount(Animation.INDEFINITE);
    
    //Set key frames so that a blink happens every 20 seconds
    animation.getKeyFrames().addAll(new KeyFrame(Duration.millis(2000), e -> {
            idleAnimation.getChildren().setAll(idleOne);
        }), new KeyFrame(Duration.millis(2100), e -> {
                    idleAnimation.getChildren().setAll(idleTwo);
        }), new KeyFrame(Duration.millis(2400), e -> {
                    idleAnimation.getChildren().setAll(idleOne);
        }), new KeyFrame(Duration.millis(4000), e -> {
                    idleAnimation.getChildren().setAll(idleThree);
        }), new KeyFrame(Duration.millis(4100), e -> {
                    idleAnimation.getChildren().setAll(idleFour);
        }), new KeyFrame(Duration.millis(4200), e -> {
                    idleAnimation.getChildren().setAll(idleThree);
        }), new KeyFrame(Duration.millis(4300), e -> {
                    idleAnimation.getChildren().setAll(idleOne);
        })
    );
    animation.play();

    //Add the animation to this pane
    this.getChildren().add(idleAnimation);
}

}