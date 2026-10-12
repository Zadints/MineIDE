package org.example.mineide.core.application.linkDrivers.managers;

import org.example.mineide.core.domain.interfaces.softwareStrategy;

import java.net.URI;
import java.nio.file.Path;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class fastAccesLinkManager implements softwareStrategy{
    public boolean manage(String link, Path destination){
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .header("User-Agent", "MineIDE/1.0")
                .GET()
                .build();
        try {
            HttpResponse<Path> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofFile(destination)
            );
        }catch (Exception e){
            return false;
        }
        return true;

    }
}
