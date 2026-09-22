package org.example.mineide.core.application.usecase.Loader;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.domain.repositories.VersionsMc;
import org.example.mineide.infraestructure.models.api.VersionsMcClient;

import java.util.List;

public class LoadVersionMcUseCase {
    private final VersionsMc versionsMc;

    public LoadVersionMcUseCase(VersionsMc versionsMc) {
        this.versionsMc = versionsMc;
    }

    public List<MinecraftVersionDTO> load(){
        return versionsMc.getVersions();
    }

}
