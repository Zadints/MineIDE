package org.example.mineide.core.application.usecase;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.application.usecase.Loader.LoadVersionMcUseCase;
import org.example.mineide.infraestructure.models.api.VersionsMcClient;

import java.util.ArrayList;
import java.util.List;

public class StartAppUseCase {
    private static List<MinecraftVersionDTO> minecraftVersionList = new ArrayList<>();

    public static void loadDataReuired(){
        loadMinecraftVersion();
    }

    private static void loadMinecraftVersion (){
        LoadVersionMcUseCase minecraftVersions = new LoadVersionMcUseCase(new VersionsMcClient());
        minecraftVersionList = minecraftVersions.load();
    }
    //otros loads aqui lore van al momento de iniciar app
}
