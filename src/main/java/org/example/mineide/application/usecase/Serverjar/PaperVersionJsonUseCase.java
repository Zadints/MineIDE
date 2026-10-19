package org.example.mineide.application.usecase.Serverjar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.exceptions.ServerJarDataInternalException;
import org.example.mineide.domain.repository.TypeServerJar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PaperVersionJsonUseCase implements TypeServerJar {

    @Override
    public ServerJar getServerJar(JarFile jar) throws IOException {

        JarEntry entry = jar.getJarEntry("version.json");

        if (entry == null) {
            return null;
        }

        try (InputStream input = jar.getInputStream(entry)) {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(input);

            String name = json.path("name").asText(null);
            String version = json.path("id").asText(null);

            if (!name.equalsIgnoreCase(version)){
                throw new ServerJarDataInternalException("The version and the name are not the same.");
            }

            return new ServerJar(version, name, null);
        }
    }
}
