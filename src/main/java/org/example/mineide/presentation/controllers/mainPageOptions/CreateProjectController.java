package org.example.mineide.presentation.controllers.mainPageOptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import org.example.mineide.application.dto.MinecraftVersionDTO;
import org.example.mineide.application.dto.create.project.ProjectDto;
import org.example.mineide.application.dto.create.project.ServerJarSelectedDto;
import org.example.mineide.application.services.ServerJarServices;
import org.example.mineide.application.usecase.StartAppUseCase;
import org.example.mineide.application.usecase.project.CreateProjectUseCase;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.enums.JdkEnum;
import org.example.mineide.domain.exceptions.CreateNewProjectException;
import org.example.mineide.presentation.controllers.ErrorPageController;
import org.example.mineide.presentation.utils.MouseMove;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class CreateProjectController {
    @FXML private VBox header;
    @FXML private Button btnCancel;
    @FXML private Button btnCreate;
    @FXML private ComboBox<String> cbxServerSoftware;
    @FXML private ComboBox<String> cbxMinecraftVersion;
    @FXML private TextField txtServerName;
    @FXML private Button btnBrowseDirectory;
    @FXML private TextField txtServerDirectory;
    @FXML private ComboBox<JdkEnum> cbxJdkVersion;
    @FXML private Button btnBrowseSoftwareJava;
    @FXML private TextField txtJavaSofware;
    @FXML private CheckBox chkGit;
    @FXML private CheckBox chkReadme;
    @FXML private CheckBox chkGitIgnore;
    @FXML private CheckBox chkDownloadDependencies;

    private ServerJarSelectedDto serverJarDto;
    private Path pathDeployProject;

    private final CreateProjectUseCase createProjectUseCase;

    public CreateProjectController(CreateProjectUseCase createProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
    }

    @FXML
    protected void initialize() {
        MouseMove mouseMove = new MouseMove(header);
        mouseMove.start();

        for (MinecraftVersionDTO item : StartAppUseCase.getMinecraftVersionList()){
            cbxMinecraftVersion.getItems().add(item.toVersion());
            cbxServerSoftware.getItems().add(item.toSoftware());
        }
        cbxJdkVersion.getItems().addAll(JdkEnum.values());
    }

    @FXML protected void onCancel(){
        close();
    }
    private void close(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void onCreateProject(){

        ProjectDto newProject = createEntityProject();
        try{
            createProjectUseCase.create(newProject);
            close();
        }catch (CreateNewProjectException e){
            launchErrorWindows(
                    e.getMessage(),
                    "Error al crear proyecto",
                    btnCreate.getScene());
        }
    }

    @NotNull
    private ProjectDto createEntityProject() {
        String serverName = txtServerName.getText();
        JdkEnum jdkVersion = cbxJdkVersion.getValue();

        if (jdkVersion == null){
            jdkVersion = JdkEnum.JDK_26;
        }

        boolean initializeGit = chkGit.isSelected();
        boolean createReadme = chkReadme.isSelected();
        boolean generateGitIgnore = chkGitIgnore.isSelected();
        boolean downloadDependencies = chkDownloadDependencies.isSelected();

        return new ProjectDto(
            serverName,
            pathDeployProject,
            new ServerJar(
                    serverJarDto.getJarLocation().getFileName().toString(),
                    serverJarDto.getJarVersion(),
                    serverJarDto.getSoftware(),
                    serverJarDto.getBuild(),
                    jdkVersion,
                    "Mi primer proyecto",
                    serverJarDto.getJarLocation()
            ),
            initializeGit,
            createReadme,
            generateGitIgnore,
            downloadDependencies
        );
    }
    @FXML private void onBrowseLocation(ActionEvent event){

        Path pathDirectory = selectPathDirectory(btnBrowseDirectory.getScene());
        if (pathDirectory == null ) return;
        txtServerDirectory.setText(pathDirectory.toString());
        pathDeployProject = pathDirectory;
    }

    @FXML private void onBrowseJava(){
        Path path = selectArchive("server.jar", btnBrowseSoftwareJava.getScene());
        if (path == null) return;

        try {
            serverJarDto = ServerJarServices.getType(path);
        } catch (IOException e){
            launchErrorWindows(e.getMessage(), "Error", btnBrowseSoftwareJava.getScene());
        }


        txtJavaSofware.setText(path.toString());
        txtJavaSofware.setEditable(false);
        cbxMinecraftVersion.setDisable(true);
        cbxServerSoftware.setDisable(true);

        if ( serverJarDto != null){
            cbxMinecraftVersion.setPromptText( serverJarDto.getJarVersion());
            cbxServerSoftware.setPromptText( serverJarDto.getSoftware());
            return;
        }
        cbxMinecraftVersion.setPromptText("Already you Selected");
        cbxServerSoftware.setPromptText("Already you Selected");
    }

    private Path selectPathDirectory(Scene scene){
        DirectoryChooser carpeta = new DirectoryChooser();
        carpeta.setTitle("select main directory");

        Stage stage =  (Stage) scene.getWindow();

        File file = carpeta.showDialog(stage);

        if (file == null) return null;

        if (!file.isDirectory()){
            txtServerDirectory.setPromptText("Invalid directory selected");
            return null;
        }
        return file.toPath();
    }
    private Path selectArchive(String type, Scene scene){
        FileChooser archive = new FileChooser();
        archive.setTitle("Select file " + type);
        archive.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivo .jar", "*.jar")
        );

        Stage stage =  (Stage) scene.getWindow();

        File file = archive.showOpenDialog(stage);
        System.out.println(file + "");
        if (file == null) return null;
        return file.toPath();
    }

    private void getVersions(){


    }

    private void launchErrorWindows(String errorMessage, String errorTitle, Scene sceneActual){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            "/org/example/mineide/errorPage.fxml"
                    )
            );

            Parent root = loader.load();
            ErrorPageController controller = loader.getController();
            controller.setException(errorMessage,errorTitle);

            Stage parentStage = (Stage) sceneActual.getWindow();
            Stage stage = new Stage();

            stage.initOwner(parentStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);


            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            scene.getStylesheets().add(
                    getClass().getResource("/styles/mainPage.css").toExternalForm()
            );
            stage.initStyle(StageStyle.TRANSPARENT);
            Rectangle2D screen = Screen.getPrimary().getVisualBounds();

            double width = screen.getWidth() * 0.30;
            double height = screen.getHeight() * 0.45;

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
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.centerOnScreen();
            stage.setTitle("Create New Project");
            stage.setScene(scene);
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
