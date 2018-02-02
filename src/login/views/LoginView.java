/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.views;

import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.controller.LoginController;
import utilities.ServiceManager;

public class LoginView extends Application {

    private ServiceManager serviceManager;
    private LoginController loginController = new LoginController();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public void load() {
        if(!loginController.isLoggedIn()){
            Stage primaryStage = new Stage();
            Text header = new Text("Login To Access Manager Mode");
            Button loginBtn = new Button("Login");
            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            loginBtn.setOnAction((ActionEvent event) -> {
                if (!loginController.login(usernameField.getText(), passwordField.getText())) {
                    loginController.getLoginErrors().entrySet().stream().forEach((pair) -> {
                        String Location = (String) pair.getKey();
                        String Message = (String) pair.getValue();
                        serviceManager.getALERT().showAlert(Location+ " "+Message, 0, Color.RED);
                    });
                }else{
                    primaryStage.hide();
                    serviceManager.getBtnManager().finalizeManagerMode();
                }
            });
            VBox vbox = new VBox(header, usernameField, passwordField, loginBtn);
            StackPane root = new StackPane();
            root.getChildren().add(vbox);

            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Login to Manager Mode!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }else{
            serviceManager.getBtnManager().finalizeManagerMode();
        }
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
