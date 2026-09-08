package org.mineapi.mineapi.infraestructure.dto;

public record VersionDto(
        String software,
        String build,
        String version
) { }
