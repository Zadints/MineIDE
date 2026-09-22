package org.example.mineide.infraestructure.models.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.domain.exceptions.ApiException;
import org.example.mineide.core.domain.repositories.VersionsMc;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class VersionsMcClient implements VersionsMc {


    private final HttpClient client;
    private final ObjectMapper mapper;

    public VersionsMcClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }
    @Override
    public List<MinecraftVersionDTO> getVersions(){

        try {

            String url = "http://lrweb.dpdns.org/mcDowload";
        /*
        [
          {
            "id": "1.21.8",
            "type": "purpur"
          },
          {
            "id": "1.21.8",
            "type": "purpur"
          },
          {
            "id": "1.21.8",
            "type": "purpur"
          }
        ]
        */
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new IOException(
                        "Error HTTP: " + response.statusCode()
                );
            }

            return mapper.readValue(
                    response.body(),
                    new TypeReference<List<MinecraftVersionDTO>>() {}
            );
        } catch (IOException | InterruptedException e){
            throw new ApiException("No se pudo cargar las versiones de servidores", e);
        }
    }
}
