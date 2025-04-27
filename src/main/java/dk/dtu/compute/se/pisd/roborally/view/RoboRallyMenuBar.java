/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class RoboRallyMenuBar extends MenuBar {

    private AppController appController;

    private Menu controlMenu;

    private MenuItem saveGame;

    private MenuItem newGame;

    private MenuItem loadGame;

    private MenuItem SignIn;
    private MenuItem SignOut;

    private MenuItem selectGame;

    private MenuItem stopGame;

    private MenuItem exitApp;

    public RoboRallyMenuBar(AppController appController) {
        this.appController = appController;

        controlMenu = new Menu("File");
        this.getMenus().add(controlMenu);

        newGame = new MenuItem("New Game");
        newGame.setOnAction( e -> this.appController.newGame());
        controlMenu.getItems().add(newGame);


        stopGame = new MenuItem("Stop Game");
        stopGame.setOnAction( e -> this.appController.stopGame());
        controlMenu.getItems().add(stopGame);

        saveGame = new MenuItem("Save Game");
        saveGame.setOnAction( e -> this.appController.saveGame());
        controlMenu.getItems().add(saveGame);

        loadGame = new MenuItem("Load Game");
        loadGame.setOnAction( e -> this.appController.loadGame());
        controlMenu.getItems().add(loadGame);

        SignIn = new MenuItem("SignIn");
        SignIn.setOnAction(e -> showSignIpDialog());
        controlMenu.getItems().add(SignIn);


        selectGame = new MenuItem("Select Online Game");
        selectGame.setOnAction( e -> this.appController.selectGame());
        controlMenu.getItems().add(selectGame);

        exitApp = new MenuItem("Exit");
        exitApp.setOnAction( e -> this.appController.exit());
        controlMenu.getItems().add(exitApp);

        controlMenu.setOnShowing(e -> update());
        controlMenu.setOnShown(e -> this.updateBounds());
        update();
    }

    private void showSignIpDialog(){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Sign Up");

        Label label = new Label("Sign Up");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        Button createButton = new Button("Create");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10, createButton, cancelButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(10, label, usernameField, buttons);
        layout.setStyle("-fx-padding: 15; -fx-alignment: center;");

        createButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            if (!username.isEmpty()) {
                appController.signUpUser(username); // Implement this method in your controller
                dialog.close();
            } else {
                usernameField.setStyle("-fx-border-color: red;");
            }
        });
        cancelButton.setOnAction(e -> dialog.close());

        dialog.setScene(new Scene(layout));
        dialog.showAndWait();
    }


    public void update() {
        if (appController.isGameRunning()) {
            newGame.setVisible(false);
            stopGame.setVisible(true);
            saveGame.setVisible(true);
            loadGame.setVisible(false);
        } else {
            newGame.setVisible(true);
            stopGame.setVisible(false);
            saveGame.setVisible(false);
            loadGame.setVisible(true);
        }
    }

}
