package org.example.mineide.controllers.center;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.net.http.*;
import java.net.URI;
import java.nio.file.*;
import java.io.File;

public class ServesController {
    Path path;
    @FXML
    void directoryselect(ActionEvent event){
        DirectoryChooser carpeta = new DirectoryChooser();
        carpeta.setTitle("select main directory");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File file = carpeta.showDialog(stage);
        path = file.toPath();
        System.out.println(path);
    }
    void serverCreate(String software, String version, String path) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://lrweb.dpdns.org/mcDowload"))
                .header("software", software)
                .header("version", version)
                .GET()
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        if (response.statusCode() == 200){
            if (path == null){
                Files.write(Paths.get( System.getProperty("user.home") + File.separator + "Desktop"), response.body());

            }else {
                Files.write(Paths.get(path), response.body());
            }
        }

    }

}
