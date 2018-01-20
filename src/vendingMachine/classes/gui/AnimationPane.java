/**
 * @project Final Project: Vending Machine - Milestone 5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/17/18
 */

package vendingMachine.classes.gui;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class AnimationPane extends Pane{
    private int transSpeed;
    private int fadeSpeed;

    /**
     *  Constructor to start the animation
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public AnimationPane(double startX, double startY, double endX, double endY, ImageView imageView){
        this.transSpeed = 4000;
        this.fadeSpeed = 300;


        //Create the path using a drawn Line
        Line path = new Line(startX, startY, endX, endY);

        //Add children to the Pane
        this.getChildren().addAll(imageView);

        //Create PathTransition Animation
        PathTransition pathTrans = new PathTransition();
        pathTrans.setDuration(Duration.millis(transSpeed)); // Set how long the animation lasts
        pathTrans.setPath(path); // Set the path to transition along
        pathTrans.setNode(imageView); // Set the node to transition
        pathTrans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); // Set the Orentation of the node
        pathTrans.setCycleCount(1); // Set how many Cycles it takes.
        pathTrans.setAutoReverse(false); // Set if autoreverse is on
        pathTrans.play(); // Play pathTransition

        // Create FadeTransition Animation
        FadeTransition fadeTrans = new FadeTransition(Duration.millis(fadeSpeed), imageView); //Set animation duration and node in constructor
        fadeTrans.setFromValue(1.0); // Set start Opaqueness
        fadeTrans.setToValue(0.0); // set end opaqueness
        fadeTrans.setCycleCount(1); // set cycleCount
        fadeTrans.setAutoReverse(false); // set if autoreverse is on


        // Play fadeTrans on pathTrans finished
        pathTrans.setOnFinished(event -> fadeTrans.play());

    }


    public int getTransSpeed() {
        return transSpeed;
    }

    public void setTransSpeed(int transSpeed) {
        this.transSpeed = transSpeed;
    }

    public int getFadeSpeed() {
        return fadeSpeed;
    }

    public void setFadeSpeed(int fadeSpeed) {
        this.fadeSpeed = fadeSpeed;
    }
}


