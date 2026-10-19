package org.example.mineide.infraestructure.models.api;

import org.example.mineide.application.dto.ResponseDTO;

import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiLinksClient {
    public static ResponseDTO getLink(ResponseDTO peticion){
        ObjectMapper mapper = new ObjectMapper();

        ResponseDTO Response = peticion;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .header("Sofware", Response.Software())
                .header("Version", Response.Version())
                .uri(URI.create("http://lrweb.dpdns.org/mcDownload"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            mapper.readValue(response.body(), ResponseDTO.class);

        } catch (Exception e){
            Response.setStatus(false);
            return Response;
        }
        return Response;

    }
}
