package org.example.mineide.core.application.dto;

public class MinecraftVersionDTO {
    private String id;
    private String type;

    /*
    * {
      "id": "1.21.8",
      "type": "purpur or paper",
    }
    * */

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

    @Override
    public String toString() {
        return id;
    }
}
