package org.example.mineide.domain.repository;

import org.example.mineide.application.dto.MinecraftVersionDTO;
import org.example.mineide.application.dto.create.project.ServerJarSelectedDto;
import org.example.mineide.domain.entities.ServerJar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;

public interface TypeServerJar {
    ServerJarSelectedDto getServerJar(JarFile jar, Path jarPath) throws IOException;
}
