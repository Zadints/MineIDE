package org.example.mineide.presentation.controllers.mainPageOptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.application.usecase.StartAppUseCase;
import org.example.mineide.presentation.controllers.utils.MouseMove;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class CreateNewProject {
    @FXML private AnchorPane anchorservers;
    @FXML private ToggleGroup updateOption;
    @FXML private ImageView imageServer;
    @FXML private RadioButton enable;
    @FXML private RadioButton disable;
    @FXML private TextField pathfield;
    @FXML private TextField namefield;
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
    Path path;
    @FXML private void onBrowseLocation(ActionEvent event){
        DirectoryChooser carpeta = new DirectoryChooser();
        carpeta.setTitle("select main directory");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File file = carpeta.showDialog(stage);
        try {
            path = file.toPath();
            System.out.println(path);
            pathfield.setText(path.toString());
        } catch (Exception e) {
            System.out.println("Ninguna carpeta selecionada se usara el path predeterminado: " + System.getProperty("user.home") + File.separator + "desktop");
            pathfield.setText(System.getProperty("user.home") + File.separator + "Desktop");
        }

    }



}
