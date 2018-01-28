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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AlertWindow extends Application {
    private Text txtError = new Text();

    /**
     * Cunstroctor for alert messages
     * @param alertStage
     * @throws Exception
     */
    @Override
    public void start(Stage alertStage) throws Exception {
       
    }

    /**
     * Displays the Alert Message Window with errorMsg.
     * @param errorMsg
     */
    public void showAlert(String errorMsg, int txtSize, Color color){
        Stage alertStage = new Stage();
         VBox pane = new VBox(); // Create HBox to hold things
        pane.setAlignment(Pos.CENTER); // Center components in the pane
        pane.setStyle("-fx-background-color: #7D869C;"
                + "-fx-border-width: 5px;"
                + "-fx-border-color: #54428E");

        //Create and format text for displaying error message
        txtError = new Text("Error 000: No error message defined!");
        txtError.setFill(color);
        txtError.setWrappingWidth(250);
        txtError.setFont(Font.font("Calibri", FontWeight.BOLD, txtSize));
        txtError.setText(errorMsg);
        txtError.setTextAlignment(TextAlignment.CENTER);

        //Create Btn that closes the alert window
        Button btnOk = new CustomButtons("OK", event -> alertStage.hide());

        //Add nodes to pane
        pane.getChildren().addAll(txtError, btnOk);

        //Set up Scene and Stage
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("vendingMachine.css");
        alertStage.initModality(Modality.APPLICATION_MODAL); // make sure you can only click this window. 
        alertStage.setScene(scene);
        alertStage.setAlwaysOnTop(true); // Keeps alert window on top
        alertStage.show();
    }

    public Text getTxtError() {
        return txtError;
    }

    public void setTxtError(Text txtError) {
        this.txtError = txtError;
    }
    
    
}
