package org.example.mineide.controllers.center;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.example.mineide.utils.IdGenerate;
import org.example.mineide.core.enums.ServerSoftware;
import org.example.mineide.core.entities.Server;

import java.net.http.*;
import java.net.URI;
import java.nio.file.*;
import java.io.File;

public class ServesController {
    @FXML private AnchorPane anchorservers;
    @FXML private ToggleGroup updateOption;
    @FXML private ImageView imageServer;
    @FXML private RadioButton enable;
    @FXML private RadioButton disable;
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

    Path path;
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

    Path imagepath = null;
    @FXML
    void selectImage(ActionEvent event){
        FileChooser image = new FileChooser();
        image.setTitle("select image for your server");

        image.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("imagenes", "*.png", "*.jpg", "*.gif"));

        File imageactual = image.showOpenDialog( imageServer.getScene().getWindow());

        imagepath = imageactual.toPath();

        Image newimage = new Image(imageactual.toURI().toString());
        imageServer.setImage(newimage);
    }

    /*
        Software Download System with api in internal server
    */

    @FXML private ComboBox softwarebox;
    @FXML private ComboBox versionbox;
    @FXML private ComboBox buildbox;
    @FXML private Slider ramslider;
    @FXML private Slider ssdslider;
    @FXML private Slider cpuslider;
    @FXML private TextField portfield;
    @FXML
    // gigabytes * 13421772 formula chunks
    void serverCreate(){

    }

    void sofwarerequest() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        Toggle selector = updateOption.getSelectedToggle();

        boolean updatable = false;

        if (selector == enable){
            updatable = true;
        } else if (selector == disable){
            updatable = false;
        }

        Server actualserver = new Server(IdGenerate.getNewId(),namefield.getText() ,path , ramslider.getValue(), ssdslider.getValue(), cpuslider.getValue(), Short.parseShort(portfield.getText()), imagepath ,updatable, ServerSoftware.valueOf(softwarebox.getValue().toString()), Short.valueOf(versionbox.getValue().toString()));

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
    void versionupdate(){
        softwarebox.itemsProperty().addListener(((obs, oldValue, newValue) -> {

        }));
    }
    void registre(Server server) {
        HttpClient client = HttpClient.newHttpClient();

        server.getSofware();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(""))
                .build();

    }
    /*void List<String> lastversion(String software){

    }
*/
}
