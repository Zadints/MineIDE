package org.example.mineide.domain.entities;

import org.example.mineide.domain.entities.files.Plugin;

import java.nio.file.Path;
import java.util.List;

public class ServerFiles {
    private Path pluginsDirectory;
    private List<Plugin> plugins;

    private Path logsDirectory;
    private Path srvPropertiesDirectory;

}
