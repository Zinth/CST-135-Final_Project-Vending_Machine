/**
 * @project Milestone5
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/17/18
 */

package vendingMachine.classes.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class AlertWindow extends Application {
    private Text txtError;
    private Stage alertStage = new Stage();

    /**
     * Cunstroctor for alert messages
     * @param alertStage
     * @throws Exception
     */
    @Override
    public void start(Stage alertStage) throws Exception {
        HBox pane = new HBox(); // Create HBox to hold things
        pane.setAlignment(Pos.CENTER); // Center components in the pane

        //Create and format text for displaying error message
        txtError = new Text("Error 000: No error message defined!");
        txtError.setFill(Color.RED);
        txtError.setWrappingWidth(250);
        txtError.setFont(new Font("Calibri", 20.0));

        //Create Btn that closes the alert window
        Button btnOk = new Button("OK");
        btnOk.setOnAction(event -> alertStage.hide());

        //Add nodes to pane
        pane.getChildren().addAll(txtError, btnOk);

        //Set up Scene and Stage
        Scene scene = new Scene(pane, 300, 300);
        alertStage.setScene(scene);
        alertStage.setAlwaysOnTop(true); // Keeps alert window on top
    }

    /**
     * Displays the Alert Message Window with errorMsg.
     * @param errorMsg
     */
    public void showAlert(String errorMsg){
        txtError.setText(errorMsg);
        alertStage.show();
    }
}
