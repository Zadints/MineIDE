package org.example.mineide.core.domain.repositories;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;

import java.io.IOException;
import java.util.List;

public interface VersionsMc {

    List<MinecraftVersionDTO> getVersions();
}
