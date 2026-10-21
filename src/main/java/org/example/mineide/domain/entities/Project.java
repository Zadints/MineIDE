package org.example.mineide.domain.entities;

import org.example.mineide.domain.entities.files.Plugin;
import org.example.mineide.domain.enums.JdkEnum;

import java.nio.file.Path;
import java.util.List;

public class Project {
    private final String id;
    private String serverName;
    private Path projectLocation;

    /**Todo respecto al .jar de mc*/
    private ServerJar serverJar;
   /**
    * Atribuo opcional que se carga con  los metodos de setServerFiles manager
    **/
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




    public String getId() {
        return id;
    }

    public String getServerName() {
        return serverName;
    }

    public Path getProjectLocation() {
        return projectLocation;
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

    /**
     * these methods are for serverJar attribute
     *
     **/

    public String getServerJarVersion() {
        return serverJar.getJarVersion();
    }
    public String getServerJarSoftware() {
        return serverJar.getSoftware();
    }
    public String getServerJarName() {
        return serverJar.getJarFileName();
    }
    public JdkEnum getJdkVersion() {
        return serverJar.getJdkVersion();
    }
    public String getServerJarBuild() {
        return serverJar.getBuild();
    }
    public String getServerJarDescription() {
        return serverJar.getDescription();
    }
    public Path getServerJarActualLocation() { return serverJar.getActualLocation(); }
    public ServerJar getServerJar() { return serverJar; }

    public void updateServerJar(ServerJar updateServerJar) {
        serverJar = updateServerJar;
    }

    /**
     * these methods are for ServerFiles attribute
     *
     **/

    public Path getServerFilesPluginsDirectory() {
        return serverFiles.getPluginsDirectory();
    }
    public List<Plugin> getServerFilesPlugins() {
        return serverFiles.getPlugins();
    }
    public Path getServerFilesLogsDirectory() {
        return serverFiles.getLogsDirectory();
    }
    public Path getServerFilesSrvPropertiesDirectory() {
        return serverFiles.getSrvPropertiesDirectory();
    }

    public void addServerFilesPlugin(Plugin plugin) {
        this.serverFiles.addPlugin(plugin);
    }
    public void setServerFilesPluginsDirectory(Path pluginsDirectory) {
        this.serverFiles.setPluginsDirectory(pluginsDirectory);
    }
    public void setServerFilesLogsDirectory(Path logsDirectory) {
        this.serverFiles.setLogsDirectory(logsDirectory);
    }
    public void setServerFilesSrvPropertiesDirectory(Path srvPropertiesDirectory) {
        this.serverFiles.setSrvPropertiesDirectory(srvPropertiesDirectory);
    }
}
