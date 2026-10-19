package org.example.mineide.domain.entities;

import org.example.mineide.domain.annotations.Optional;
import org.example.mineide.domain.enums.JdkEnum;

import java.nio.file.Path;

public class ServerJar {
    private String jarName;
    private String version;
    private String software;
    private String build;
    private JdkEnum jdkVersion;
    private String description;
    private Path path;



    public ServerJar(String version, String software, String build) {
        this.version = version;
        this.software = software;
        this.build = build;
    }


    public void setJarName(String jarName) {
        if (this.jarName != null || !this.jarName.isEmpty()) return;
        this.jarName = jarName;
    }

    public void setJdkVersion(JdkEnum jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setPath(Path path) {
        this.path = path;
    }

    public void editJarName(String jarName) {
        if (this.jarName == null || this.jarName.isEmpty()) return;
        this.jarName = jarName;
    }

    public void editVersion(String version) {
        if (this.version == null || this.version.isEmpty()) return;
        this.version = version;
    }

    public void editSoftware(String software) {
        if (this.software == null || this.software.isEmpty()) return;
        this.software = software;
    }

    public void editBuild(String build) {
        if (this.build == null || this.build.isEmpty()) return;
        this.build = build;
    }

    public void editJdkVersion(JdkEnum jdkVersion) {
        if (this.jdkVersion == null) return;
        this.jdkVersion = jdkVersion;
    }

    public void editDescription(String description) {
        if (this.description == null || this.description.isEmpty()) return;
        this.description = description;
    }

    public void editPath(Path path) {
        if (this.path == null) return;
        this.path = path;
    }

    public String getJarName() {
        return jarName;
    }

    public String getVersion() {
        return version;
    }

    public String getSoftware() {
        return software;
    }

    public String getBuild() {
        return build;
    }

    public JdkEnum getJdkVersion() {
        return jdkVersion;
    }

    public String getDescription() {
        return description;
    }

    public Path getPath() {
        return path;
    }
}
