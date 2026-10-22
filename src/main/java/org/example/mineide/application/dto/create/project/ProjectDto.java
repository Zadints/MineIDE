package org.example.mineide.application.dto.create.project;

import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.enums.JdkEnum;

import java.nio.file.Path;

public class ProjectDto {

    private String name;
    private Path deployPath;

    private ServerJar serverJar;

    private boolean initializeRepository;
    private boolean createReadme;
    private boolean generateGitIgnore;
    private boolean downloadDependencies;

    public ProjectDto(String name, Path deployPath, ServerJar serverJar, boolean initializeRepository, boolean createReadme, boolean generateGitIgnore, boolean downloadDependencies) {
        this.name = name;
        this.deployPath = deployPath;
        this.serverJar = serverJar;
        this.initializeRepository = initializeRepository;
        this.createReadme = createReadme;
        this.generateGitIgnore = generateGitIgnore;
        this.downloadDependencies = downloadDependencies;
    }

    public String getName() {
        return name;
    }

    public Path getDeployPath() {
        return deployPath;
    }

    public ServerJar getServerJar() {
        return serverJar;
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
