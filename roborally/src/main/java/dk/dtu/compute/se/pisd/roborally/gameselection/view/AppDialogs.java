package dk.dtu.compute.se.pisd.roborally.gameselection.view;

import dk.dtu.compute.se.pisd.roborally.gameselection.controller.OnlineController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import dk.dtu.compute.se.pisd.roborally.gameselection.model.User;

public class AppDialogs {

    private final OnlineController onlineController;

    public AppDialogs(OnlineController onlineController) {
        this.onlineController = onlineController;
    }

    public void showSignInDialog() {
        Stage stage = new Stage();
        stage.setTitle("Sign In");

        Text instruction = new Text("Register as user for Online RoboRally:");
        TextField userNameField = new TextField();
        userNameField.setPromptText("Enter username (min 4 characters)");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.close());

        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(e -> {
            String name = userNameField.getText();
            if (name.length() >= 4) {
                stage.close();
                onlineController.signIn(name);
            } else {
                System.out.println("Name too short!");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(instruction, userNameField, signInButton, cancelButton);

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.showAndWait(); // Modal window (blocks user from leaving)
    }

}
