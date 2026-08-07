package org.example.mineide.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class mineIdeController {


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    private double x;
    private double y;
    @FXML
    private HBox titleBar;
    public void initialize() {

        titleBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();

            if (!stage.isMaximized()) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
    }


    @FXML
    private Button btnClose;
    @FXML
    protected void onCloseClick() {
        ((Stage) btnClose.getScene().getWindow()).close();
    }

    @FXML
    private Button btnMinimize;
    @FXML
    protected void onMinimizeClick() {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Button btnMaximize;
    @FXML
    protected void onMaximizeClick() {
        Stage stage = (Stage) btnMaximize.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

}