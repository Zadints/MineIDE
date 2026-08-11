package org.example.mineide.controllers.center;

import com.almasb.fxgl.net.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;


public class ServesController {
    void directoryCreate(String software, String version) throws IOException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://lrweb.dpdns.org/mcDowload"))
                .header("software", software)
                .header("version", version)
                .GET()
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
    }
}
