package org.example.mineide.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.example.mineide.presentation.controllers.utils.MouseMove;

import java.io.IOException;

public class IndexController {


    @FXML private Label welcomeText;
    @FXML private Button btnFile;
    @FXML private Button btnCode;
    @FXML private Button btnGit;
    private MouseMove mouseMove;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML private HBox titleBar;
    public void initialize() {
        this.mouseMove = new MouseMove(titleBar);
        mouseMove.start();
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

    /*--------------------------------------
         Este metodo contiene atributos para su funcionamiento tanto de JavaFX como
         privados booleanos
     ------------------------------------*/

    @FXML
    private Button btnMaximize;
    @FXML
    private StackPane root;
    private boolean maximized = true;
    @FXML protected void onMaximizeClick() {
        Stage stage = (Stage) btnMaximize.getScene().getWindow();
        System.out.println("🔥 input de entrada" + maximized);
        if (maximized) {
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            root.getStyleClass().add("maximized");
            maximized = false;

        } else {
            stage.setWidth(1300);
            stage.setHeight(700);
            stage.centerOnScreen();
            root.getStyleClass().remove("maximized");
            maximized = true;
        }
        System.out.println("🔥 input de salida" + maximized);
    }
    @FXML
    protected void onTitleBarPressed(MouseEvent event) {
        System.out.println("CLICK");
        if (maximized){
            onMaximizeClick();
        }
    }
    @FXML
    protected void onTitleBarDragged(MouseEvent event) {
        System.out.println("DRAG");
        if (maximized){
            onMaximizeClick();
        }
    }

    @FXML
    private BorderPane brPanel;
    @FXML
    protected void onServersClick() {
        LoadContent("servers.fxml");
    }

    @FXML
    protected  void onFilesClick(){
        LoadContent("files.fxml");
    }
    @FXML
    protected void onConsoleClick () {
        LoadContent("console.fxml");
    }

    @FXML
    protected void onBackupsClick() {
        LoadContent("backups.fxml");
    }

    private void LoadContent(String fileName){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/mineide/center/" + fileName)
            );

            Parent view = loader.load();

            brPanel.setCenter(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private StackPane modalOverlay;

    @FXML
    private void onSettingAppClick() {
        openCreateServerModal();
    }

    private void openCreateServerModal() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/mineide/embed.fxml")
            );

            Parent modal = loader.load();

            modalOverlay.getChildren().clear();
            modalOverlay.getChildren().add(modal);

            modalOverlay.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    protected void onFileClick() {
       //desplegar menu
    }
    @FXML
    protected void onCodeClick() {
        //desplegar menu
    }
    @FXML
    protected void onGitClick() {
        //desplegar menu
    }
}