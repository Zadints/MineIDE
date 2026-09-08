package org.mineapi.mineapi.core.application.usecase;

import org.mineapi.mineapi.core.application.out.ServerRepository;
import org.mineapi.mineapi.infraestructure.dto.VersionDto;

public class ObtenerVersionServidorUseCase {

    private final ServerRepository serverRepository;

    public ObtenerVersionServidorUseCase(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public String execute(VersionDto dto) {
        return serverRepository.obtenerLink(
                dto.software(),
                dto.build(),
                dto.version()
        );
    }
}
