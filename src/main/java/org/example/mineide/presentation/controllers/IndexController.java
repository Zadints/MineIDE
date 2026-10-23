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
import org.example.mineide.application.usecase.project.CreateProjectUseCase;
import org.example.mineide.domain.annotations.Navigate;
import org.example.mineide.domain.annotations.ResourceRequiredSystem;
import org.example.mineide.presentation.utils.MouseMove;
import org.example.mineide.presentation.navigation.Navigator;

import java.io.IOException;

public class IndexController {

    private final GetTranslationUseCase getTranslation;
    private final Navigator navigator;
    private final CreateProjectUseCase createProjectUseCase;

    @FXML private Button btnClose;
    @FXML private VBox header;
    @FXML private StackPane content;

    public IndexController(GetTranslationUseCase getTranslation, Navigator navigator, CreateProjectUseCase createProjectUseCase){
        this.getTranslation = getTranslation;
        this.navigator = navigator;
        this.createProjectUseCase = createProjectUseCase;
    }

    public void initialize() {
        MouseMove mouseMove = new MouseMove(header);
        mouseMove.start();
    }


    @FXML protected void onPlugins(){

    }
    @FXML protected void onCreateProject(){

        try {

            Stage stage = (Stage) btnClose.getScene().getWindow();

            navigator.awaitOpenWindow(
                    "mainPageOptions/createNewProject.fxml",
                    stage,
                    0.60,
                    0.85
            );

            if (!createProjectUseCase.isCreatedProject()) return;

            navigator.navigateToPage("mainPage.fxml", 0.98, 0.98);

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
