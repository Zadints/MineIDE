package org.example.mineide.application.dto;

public class MinecraftVersionDTO {
    private String id;
    private String type;

    public MinecraftVersionDTO(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toSoftware() {
        return type;
    }

    public String toVersion() {
        return id;
    }
}
