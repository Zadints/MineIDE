package org.example.mineide.core.domain.entities;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.domain.enums.JdkEnum;

import java.nio.file.Path;

public class Project {
    private final String id;
    private String serverName;
    private Path projectLocation;
    private MinecraftVersionDTO versionAndSoftware;
    private JdkEnum jdkSoftware;
    private Path jdkPath;
    private Path imageServer;
    private boolean updateLastVersion;


    public Project(String serverName, String id, Path projectLocation, MinecraftVersionDTO versionAndSoftware, JdkEnum jdkSoftware, Path jdkPath, boolean updateLastVersion) {
        this.serverName = serverName;
        this.id = id;
        this.projectLocation = projectLocation;
        this.versionAndSoftware = versionAndSoftware;
        this.jdkSoftware = jdkSoftware;
        this.jdkPath = jdkPath;
        this.updateLastVersion = updateLastVersion;
    }

    public String getId() {
        return id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Path getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(Path projectLocation) {
        this.projectLocation = projectLocation;
    }

    public MinecraftVersionDTO getVersionAndSoftware() {
        return versionAndSoftware;
    }

    public void setVersionAndSoftware(MinecraftVersionDTO versionAndSoftware) {
        this.versionAndSoftware = versionAndSoftware;
    }

    public JdkEnum getJdkSoftware() {
        return jdkSoftware;
    }

    public void setJdkSoftware(JdkEnum jdkSoftware) {
        this.jdkSoftware = jdkSoftware;
    }

    public Path getJdkPath() {
        return jdkPath;
    }

    public void setJdkPath(Path jdkPath) {
        this.jdkPath = jdkPath;
    }

    public Path getImageServer() {
        return imageServer;
    }

    public void setImageServer(Path imageServer) {
        this.imageServer = imageServer;
    }

    public boolean isUpdateLastVersion() {
        return updateLastVersion;
    }

    public void setUpdateLastVersion(boolean updateLastVersion) {
        this.updateLastVersion = updateLastVersion;
    }

    public String getVersion() {
        return versionAndSoftware.getId();
    }

    public void setVersion(String version) {
        this.versionAndSoftware.setId(version);
    }
}
