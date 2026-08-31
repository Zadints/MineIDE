package org.example.mineide.core.entities;

import org.example.mineide.core.enums.ServerSoftware;
import org.example.mineide.core.enums.ServerVersion;

import java.nio.file.Path;

public class Server {
    private String id;
    private String serverName;
    private Path directory;
    private double ram;
    private double disco;
    private double cpu;
    private short puerto;
    private Path imageServer;
    private boolean updateLastVersion;
    private ServerSoftware software;
    private ServerVersion version;

    public Server(String id, String serverName, Path directory, double ram, double disco,
                  double cpu, short puerto, Path imageServer, boolean updateLastVersion,
                  ServerSoftware software, ServerVersion version) {
        this.id = id;
        this.serverName = serverName;
        this.directory = directory;
        this.ram = ram;
        this.disco = disco;
        this.cpu = cpu;
        this.puerto = puerto;
        this.imageServer = imageServer;
        this.updateLastVersion = updateLastVersion;
        this.software = software;
        this.version = version;
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Path getDirectory() {
        return directory;
    }

    public void setDirectory(Path directory) {
        this.directory = directory;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(long ram) {
        this.ram = ram;
    }

    public double getDisco() {
        return disco;
    }

    public void setDisco(long disco) {
        this.disco = disco;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public short getPuerto() {
        return puerto;
    }

    public void setPuerto(short puerto) {
        this.puerto = puerto;
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

    public ServerSoftware getSofware() {
        return software;
    }

    public void setSofware(ServerSoftware sofware) {
        this.software = sofware;
    }

    public ServerVersion getVersion() {
        return version;
    }

    public void setVersion(ServerVersion version) {
        this.version = version;
    }
}
