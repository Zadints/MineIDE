package org.example.mineide.application.services;

import org.example.mineide.application.usecase.Serverjar.PaperPatchPropertiesUseCase;
import org.example.mineide.application.usecase.Serverjar.PaperVersionJsonUseCase;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.exceptions.FileDoesNotExist;
import org.example.mineide.domain.repository.TypeServerJar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarFile;

public class ServerJarServices {
    /**
     * the Class for server.jar
     */
    private static TypeServerJar typeServerJar;

    public static ServerJar getType(Path pathServerJar) throws IOException{

        if (pathServerJar == null || !Files.exists(pathServerJar))
            throw  new FileDoesNotExist("Server.jar");

        try (JarFile jar = new JarFile(pathServerJar.toFile())) {

            if (jar.getEntry("version.json") != null) {
                typeServerJar = new PaperVersionJsonUseCase();

            }

            if (jar.getEntry("patch.json") != null) {
                typeServerJar = new PaperVersionJsonUseCase();

            }

            if (jar.getEntry("patch.properties") != null) {
                typeServerJar = new PaperPatchPropertiesUseCase();
            }


            return typeServerJar.getServerJar(jar);
        }
    }
}
