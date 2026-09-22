package org.example.mineide.presentation.controllers.mainPageOptions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.mineide.presentation.controllers.utils.MouseMove;

public class CreateNewProject {

    @FXML private VBox header;
    @FXML private Button btnCancel;
    @FXML private TextField txtProjectName;
    @FXML private TextField txtProjectLocation;

    public void initialize() {
        MouseMove mouseMove = new MouseMove(header);
        mouseMove.start();

        
    }

    @FXML protected void onCancel(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    @FXML private void onCreateProject(){

    }
    @FXML private void onBrowseJava(){

    }
    @FXML private void onBrowseLocation(){

    }
}
