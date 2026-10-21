package org.example.mineide.domain.entities;

import org.example.mineide.domain.enums.JdkEnum;
import org.example.mineide.domain.exceptions.AlreadySetValueEntityException;

import java.nio.file.Path;

public class ServerJar {
    private String jarFileName;
    private String jarVersion; //💡
    private String software; //💡
    private String build; //💡
    private JdkEnum jdkVersion;
    private String description;
    private Path actualLocation;

    private boolean firshCostructorCall = false;
    /**
     *  Este cosntructor solo se llama cuando se selecciona un .jar personalizado pro parte del usuario,
     *  no se llama cuando se descarga el .jar desde la nube.
     **/
    public ServerJar(String version, String software, String build) {
        this.jarVersion = version;
        this.software = software;
        this.build = build;
        this.firshCostructorCall = true;
    }

    /**
     *  Este cosntructor solo se llama cuando se ya se descargo el .jar de la nube y desea crear la entidad apr guardar en
     *  la entodad project.
     **/
    public ServerJar(String jarName, String version, String software, String build, JdkEnum jdkVersion, String description, Path actualLocation) {
        this.jarFileName = jarName;
        this.jarVersion = version;
        this.software = software;
        this.build = build;
        this.jdkVersion = jdkVersion;
        this.description = description;
        this.actualLocation = actualLocation;
    }



    /**
     *  Métodos a llamar cuando se crea la entidad cuando el usuario selecciona un
     *  .jar personalizado
     **/

    public void setFileJarName(String jarName) {
        if (!firshCostructorCall) throw new AlreadySetValueEntityException("setFileJarName(" + jarName + ") already defined => " + jarFileName);
        this.jarFileName = jarName;
    }

    public void setJdkVersion(JdkEnum jdkVersion) {
        if (!firshCostructorCall) throw new AlreadySetValueEntityException("setJdkVersion(" + jdkVersion + ") already defined => " + this.jdkVersion);
        this.jdkVersion = jdkVersion;
    }

    public void setActualLocation(Path actualLocation) {
        if (!firshCostructorCall) throw new AlreadySetValueEntityException("setActualLocation(" +  actualLocation + ") already defined => " + this. actualLocation);
        this.actualLocation = actualLocation;
    }


    /**
     *  Métodos a llamar cuando se necesita ediatr la entidad
     **/


    public void editJarName(String jarName) {
        this.jarFileName = jarName;
    }

    public void editVersion(String version) {
        this.jarVersion = version;
    }

    public void editSoftware(String software) {
        this.software = software;
    }

    public void editBuild(String build) {
        this.build = build;
    }

    public void editJdkVersion(JdkEnum jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public void editActualLocation(Path path) {
        this.actualLocation = path;
    }

    /**
     *  Métodos getter apra la entidad
     **/

    public String getJarFileName() {
        return jarFileName;
    }

    public String getJarVersion() {
        return jarVersion;
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

    public Path getActualLocation() {
        return actualLocation;
    }
}
