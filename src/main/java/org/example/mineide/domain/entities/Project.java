package org.example.mineide.domain.entities;

import java.nio.file.Path;

public class Project {
    private final String id;
    private String serverName;
    private Path projectLocation;

    private ServerJar serverJar;
    private ServerFiles serverFiles;

    private boolean initializeRepository ;
    private boolean createReadme ;
    private boolean generateGitIgnore ;
    private boolean downloadDependencies ;

    public Project(String id, String serverName, Path projectLocation, ServerJar serverJar, boolean initializeRepository, boolean createReadme, boolean generateGitIgnore, boolean downloadDependencies) {
        this.id = id;
        this.serverName = serverName;
        this.projectLocation = projectLocation;
        this.serverJar = serverJar;
        this.initializeRepository = initializeRepository;
        this.createReadme = createReadme;
        this.generateGitIgnore = generateGitIgnore;
        this.downloadDependencies = downloadDependencies;
    }


    public void setServerFiles(ServerFiles serverFiles) {
        this.serverFiles = serverFiles;
    }

    public String getId() {
        return id;
    }

    public String getServerName() {
        return serverName;
    }

    public Path getProjectLocation() {
        return projectLocation;
    }

    public ServerJar getServerJar() {
        return serverJar;
    }

    public ServerFiles getServerFiles() {
        return serverFiles;
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

    public String getVersion() {
        return serverJar.getVersion();
    }

}
