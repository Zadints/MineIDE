package org.example.mineide.controllers.center;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.example.mineide.core.entities.Server;
import org.example.mineide.utils.IdGenerate;

import java.net.http.*;
import java.net.URI;
import java.nio.file.*;
import java.io.File;

public class ServesController {
    @FXML
    private AnchorPane anchorservers;
    @FXML
    private ToggleGroup updateOption;
    @FXML
    private ImageView imageServer;
    @FXML
    void initialize(){

        updateOption.selectedToggleProperty().addListener(
                (obs, anterior, actual) -> {
                    RadioButton selected = (RadioButton) actual;
                }
        );
        System.out.println(getClass().getResource("/multimedia/images/folder.png"));
        Image imagedefault = new Image(getClass().getResourceAsStream("/multimedia/images/folder.png"));
        imageServer.setImage(imagedefault);
        anchorservers.getStylesheets().add(
                getClass().getResource("/styles/servers.css").toExternalForm()
        );
    }

    Path path = Path.of(System.getProperty("user.home") + File.separator + "Desktop");
    @FXML private TextField pathfield;
    @FXML private TextField namefield;
    @FXML
    void directoryselect(ActionEvent event){
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


    @FXML
    void selectImage(ActionEvent event){
        FileChooser image = new FileChooser();
        image.setTitle("select image for your server");

        image.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("imagenes", "*.png", "*.jpg", "*.gif"));

        File imageactual = image.showOpenDialog( imageServer.getScene().getWindow());

        Image newimage = new Image(imageactual.toURI().toString());
        imageServer.setImage(newimage);
    }

    /*
        Software Download System with api in internal server
    */

    @FXML private ComboBox softwarebox;
    @FXML private ComboBox versionbox;
    @FXML private ComboBox buildbox;
    @FXML
    void serverCreate(ActionEvent event) throws Exception {

//        Server buildserver = new Server(IdGenerate.getNewId(), namefield.getText(), path, );

        HttpClient client = HttpClient.newHttpClient();

        String software = softwarebox.getValue().toString();
        String build = buildbox.getValue().toString();
        String version = versionbox.getValue().toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://lrweb.dpdns.org/mcDowload"))
                .header("software", software)
                .header("version", version)
                .header("build", build)
                .GET()
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        if (response.statusCode() == 200){
            if (path == null){
                Path name = Paths.get(System.getProperty("user.home") + File.separator + "Desktop");
                Path main = Files.createDirectory(name.resolve(pathfield.getText()));
                Files.write(main.resolve("software.jar"), response.body());

            }else {
                Path name = Files.createDirectory(path.resolve(namefield.getText()));
                Files.write(name.resolve("software.jar") , response.body());
            }
        }

    }

//    void registre() {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().
//                uri(URI.create("")).
//                header(" ", "juan").
//                POST(Htt).
//                build();
//    }

}
