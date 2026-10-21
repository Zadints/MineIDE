package org.example.mineide.domain.entities;

import org.example.mineide.domain.entities.files.Plugin;

import java.nio.file.Path;
import java.util.List;

public class ServerFiles {
    private Path pluginsDirectory;
    private List<Plugin> plugins;

    private Path logsDirectory;
    private Path srvPropertiesDirectory;

    public void setPluginsDirectory(Path pluginsDirectory) {
        this.pluginsDirectory = pluginsDirectory;
    }

    public void addPlugin(Plugin plugin) {
        this.plugins.add(plugin);
    }

    public void setLogsDirectory(Path logsDirectory) {
        this.logsDirectory = logsDirectory;
    }

    public void setSrvPropertiesDirectory(Path srvPropertiesDirectory) {
        this.srvPropertiesDirectory = srvPropertiesDirectory;
    }

    public Path getPluginsDirectory() {
        return pluginsDirectory;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public Path getLogsDirectory() {
        return logsDirectory;
    }

    public Path getSrvPropertiesDirectory() {
        return srvPropertiesDirectory;
    }
}
