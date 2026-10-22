package org.example.mineide.application.usecase.Serverjar;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mineide.application.dto.create.project.ServerJarSelectedDto;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.exceptions.ServerJarDataInternalException;
import org.example.mineide.domain.repository.TypeServerJar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaperPatchPropertiesUseCase implements TypeServerJar {
    @Override
     public ServerJarSelectedDto getServerJar(JarFile jar, Path jarPath)
            throws IOException {

        JarEntry entry = jar.getJarEntry("patch.properties");

        if (entry == null) {
            return null;
        }

        Properties properties = new Properties();

        try (InputStream input = jar.getInputStream(entry)) {
            properties.load(input);
        }

        String version = properties.getProperty("version");
        String name = properties.getProperty("patch");
        String build = properties.getProperty("sourceUrl");

         if (name == null || version == null)
             throw new ServerJarDataInternalException("version or name is null");
         name = "Paper";

         if (build != null ){
             Pattern pattern = Pattern.compile("/objects/([a-fA-F0-9]{40})/");
             Matcher matcher = pattern.matcher(build);
             if (matcher.find()) {
                 build = matcher.group(1);
             }
         }

        return new ServerJarSelectedDto(version, name, build, jarPath);
    }

}
