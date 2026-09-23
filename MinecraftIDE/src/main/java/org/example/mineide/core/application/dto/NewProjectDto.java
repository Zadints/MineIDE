package org.example.mineide.core.application.dto;

import org.example.mineide.core.domain.annotations.Optional;
import org.example.mineide.core.domain.enums.JdkEnum;

import java.nio.file.Path;

public class NewProjectDto {

    private String serverName;
    private String locationNewProject;

    private MinecraftVersionDTO versionAndSoftware;
    @Optional private String serverSoftwarePath;

    private JdkEnum jdkVersion;
    @Optional private String jdkPath;

    private boolean initializeRepository;
    private boolean createReadme;
    private boolean generateGitIgnore;
    private boolean downloadDependencies;

    public NewProjectDto(String serverName, String locationNewProject, MinecraftVersionDTO versionAndSoftware, JdkEnum jdkVersion, String serverSoftwarePath, String jdkPath, boolean initializeRepository, boolean createReadme, boolean generateGitIgnore, boolean downloadDependencies) {
        this.serverName = serverName;
        this.locationNewProject = locationNewProject;
        this.versionAndSoftware = versionAndSoftware;
        this.jdkVersion = jdkVersion;
        this.serverSoftwarePath = serverSoftwarePath;
        this.jdkPath = jdkPath;
        this.initializeRepository = initializeRepository;
        this.createReadme = createReadme;
        this.generateGitIgnore = generateGitIgnore;
        this.downloadDependencies = downloadDependencies;
    }

    public String getServerName() {
        return serverName;
    }

    public String getLocationNewProject() {
        return locationNewProject;
    }

    public String getServerSoftwarePath() {
        return serverSoftwarePath;
    }

    public JdkEnum getJdkVersion() {
        return jdkVersion;
    }

    public String getJdkPath() {
        return jdkPath;
    }

    public boolean isInitializeRepository() {
        return initializeRepository;
    }

    public boolean isCreateReadme() {
        return createReadme;
    }

    public boolean isGenerateGitIgnore() {
        return generateGitIgnore;
    }

    public boolean isDownloadDependencies() {
        return downloadDependencies;
    }

    public MinecraftVersionDTO getVersionAndSoftware() {
        return versionAndSoftware;
    }
}
