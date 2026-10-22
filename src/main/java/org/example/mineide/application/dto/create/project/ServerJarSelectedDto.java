package org.example.mineide.application.dto.create.project;

import java.nio.file.Path;

public class ServerJarSelectedDto {

    private String jarVersion; //💡
    private String software; //💡
    private String build;
    private Path jarLocation;
    /**
     *  Este cosntructor solo se llama cuando se selecciona un .jar personalizado pro parte del usuario,
     *  no se llama cuando se descarga el .jar desde la nube.
     **/
    public ServerJarSelectedDto(String jarVersion, String software, String build, Path jarLocation) {
        this.jarVersion = jarVersion;
        this.software = software;
        this.build = build;
        this.jarLocation = jarLocation;
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

    public Path getJarLocation() {
        return jarLocation;
    }
}
