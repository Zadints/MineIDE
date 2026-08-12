package org.example.mineide.controllers.center;


import java.net.http.*;
import java.net.URI;
import java.nio.file.*;
import java.io.File;

public class ServesController {
    void directoryCreate(String software, String version, String path) throws Exception {
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
                Files.write(Paths.get("pedroluis.txt"), response.body());
            }
        }

    }

}
