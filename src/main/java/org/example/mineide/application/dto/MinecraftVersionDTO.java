package org.example.mineide.application.dto;

public class MinecraftVersionDTO {
    private String version;
    private String software;

    public MinecraftVersionDTO(String id, String type) {
        this.version = id;
        this.software = type;
    }

    public String getVersion() {
        return version;
    }

    public String getSoftware() {
        return software;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String toSoftware() {
        return software;
    }

    public String toVersion() {
        return version;
    }
}
