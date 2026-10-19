package org.example.mineide.domain.repository;

import org.example.mineide.application.dto.MinecraftVersionDTO;

import java.util.List;

public interface VersionsMc {

    List<MinecraftVersionDTO> getVersions();
}
