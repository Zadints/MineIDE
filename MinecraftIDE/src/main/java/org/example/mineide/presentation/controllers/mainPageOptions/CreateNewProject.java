package org.example.mineide.presentation.controllers.mainPageOptions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.application.usecase.StartAppUseCase;
import org.example.mineide.presentation.controllers.utils.MouseMove;

import java.util.List;

public class CreateNewProject {

    @FXML private VBox header;
    @FXML private Button btnCancel;
    @FXML private TextField txtProjectName;
    @FXML private TextField txtProjectLocation;
    @FXML private ComboBox<String> cbxServerSoftware;
    @FXML private ComboBox<String> cbxMinecraftVersion;
    @FXML TextField txtServerName;

    public void initialize() {
        MouseMove mouseMove = new MouseMove(header);
        mouseMove.start();

        for (MinecraftVersionDTO item : StartAppUseCase.getMinecraftVersionList()){
            cbxMinecraftVersion.getItems().add(item.toVersion());
            cbxServerSoftware.getItems().add(item.toSoftware());
        }
    }

    @FXML protected void onCancel(){
        close();
    }

    private void close(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }



    @FXML private void onCreateProject(){
        String serverName = txtServerName.getText();

        //String version = cbxMinecraftVersion;

        close();
    }
    @FXML private void onBrowseJava(){

    }
    @FXML private void onBrowseLocation(){

    }
}
