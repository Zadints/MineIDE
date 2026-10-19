package org.example.mineide.application.dto;

import org.example.mineide.domain.annotations.Optional;
import org.example.mineide.domain.entities.ServerFiles;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.enums.JdkEnum;

public class NewProjectDto {

    private String serverName;
    private String locationNewProject;
    private ServerJar serverJar;

    private JdkEnum jdkVersion;

    private boolean initializeRepository;
    private boolean createReadme;
    private boolean generateGitIgnore;
    private boolean downloadDependencies;

    public NewProjectDto(String serverName, String locationNewProject, ServerJar serverJar, JdkEnum jdkVersion, boolean initializeRepository, boolean createReadme, boolean generateGitIgnore, boolean downloadDependencies) {
        this.serverName = serverName;
        this.locationNewProject = locationNewProject;
        this.serverJar = serverJar;
        this.jdkVersion = jdkVersion;
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

    public ServerJar getServerJar() {
        return serverJar;
    }

    public JdkEnum getJdkVersion() {
        return jdkVersion;
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
}
