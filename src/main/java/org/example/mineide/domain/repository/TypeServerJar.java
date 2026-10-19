package org.example.mineide.domain.repository;

import org.example.mineide.application.dto.MinecraftVersionDTO;
import org.example.mineide.domain.entities.ServerJar;

import java.io.IOException;
import java.util.List;
import java.util.jar.JarFile;

public interface TypeServerJar {
    ServerJar getServerJar(JarFile jar) throws IOException;
}
