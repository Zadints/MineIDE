package org.example.mineide.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.mineide.application.usecase.GetTranslationUseCase;
import org.example.mineide.application.usecase.ProjectManagerUseCase;
import org.example.mineide.domain.annotations.Navigate;
import org.example.mineide.domain.annotations.ResourceRequiredSystem;
import org.example.mineide.presentation.utils.MouseMove;
import org.example.mineide.presentation.navigation.Navigator;

import java.io.IOException;

public class IndexController {

    @ResourceRequiredSystem private final GetTranslationUseCase getTranslation;
    @Navigate private final Navigator navigator;

    @FXML private Button btnClose;
    @FXML private VBox header;
    @FXML private StackPane content;

    public IndexController(GetTranslationUseCase getTranslation, Navigator navigator){
        this.getTranslation = getTranslation;
        this.navigator = navigator;
    }

    public void initialize() {
        MouseMove mouseMove = new MouseMove(header);
        mouseMove.start();
    }


    @FXML protected void onPlugins(){

    }
    @FXML protected void onCreateProject(){
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/org/example/mineide/mainPageOptions/createNewProject.fxml"
                    )
            );

            Parent root = loader.load();
            Stage parentStage = (Stage) btnClose.getScene().getWindow();
            Stage stage = new Stage();

            stage.initOwner(parentStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);


            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            scene.getStylesheets().addAll(
                    getClass().getResource("/styles/mainPage.css").toExternalForm(),
                    getClass().getResource("/styles/index.css").toExternalForm()
            );
            stage.initStyle(StageStyle.TRANSPARENT);
            Rectangle2D screen = Screen.getPrimary().getVisualBounds();

            double width = screen.getWidth() * 0.60;
            double height = screen.getHeight() * 0.85;

            stage.setWidth(width);
            stage.setHeight(height);

            stage.setX(
                    screen.getMinX() +
                            (screen.getWidth() - width) / 2
            );

            stage.setY(
                    screen.getMinY() +
                            (screen.getHeight() - height) / 2
            );
            stage.setMinWidth(900);
            stage.setMinHeight(600);
            stage.setMaximized(false);
            stage.centerOnScreen();
            stage.setTitle("Create New Project");
            stage.setScene(scene);
            stage.showAndWait();

            if (!ProjectManagerUseCase.isOpenProject()) return;

            stage.hide();

            navigator.navigate("mainPage.fxml", 0.98, 0.98);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML protected void onSettings(){

    }
    @FXML protected void onClearRecent(){

    }
    @FXML protected void onConnectSftp(){

    }
    @FXML protected void onOpenFolder(){

    }
    @FXML protected void onClose(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }
}
