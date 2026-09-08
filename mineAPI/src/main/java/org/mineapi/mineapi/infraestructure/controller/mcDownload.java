package org.mineapi.mineapi.infraestructure.controller;

import org.mineapi.mineapi.core.application.usecase.ObtenerVersionServidorUseCase;
import org.mineapi.mineapi.infraestructure.dto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcDownload")
public class mcDownload {

    private final ObtenerVersionServidorUseCase useCase;

    public mcDownload (ObtenerVersionServidorUseCase uCase) {
        this.useCase = uCase;
    }

    @PostMapping
    public String obtenerVersionServidor(@RequestBody VersionDto versDto) {


        return "¿Que buscas aventurero?";
    }
}
