package org.example.mineide.core.domain.entities;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.domain.enums.JdkEnum;

import java.nio.file.Path;

public class Project {
    private final String id;
    private String serverName;
    private Path projectLocation;
    private MinecraftVersionDTO versionAndSoftware;
    private JdkEnum jdkVersion;
    private Path jdkPath;
    private boolean initializeRepository ;
    private boolean createReadme ;
    private boolean generateGitIgnore ;
    private boolean downloadDependencies ;

    public Project(String id, String serverName, Path projectLocation, MinecraftVersionDTO versionAndSoftware, JdkEnum jdkVersion, Path jdkPath, boolean initializeRepository, boolean createReadme, boolean generateGitIgnore, boolean downloadDependencies) {
        this.id = id;
        this.serverName = serverName;
        this.projectLocation = projectLocation;
        this.versionAndSoftware = versionAndSoftware;
        this.jdkVersion = jdkVersion;
        this.jdkPath = jdkPath;
        this.initializeRepository = initializeRepository;
        this.createReadme = createReadme;
        this.generateGitIgnore = generateGitIgnore;
        this.downloadDependencies = downloadDependencies;
    }

    public String getVersion() {
        return versionAndSoftware.getId();
    }

    public void setVersion(String version) {
        this.versionAndSoftware.setId(version);
    }
}
