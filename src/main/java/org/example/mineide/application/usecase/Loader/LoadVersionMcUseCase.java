package org.example.mineide.application.usecase.Loader;

import org.example.mineide.application.dto.MinecraftVersionDTO;
import org.example.mineide.domain.repository.VersionsMc;

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
