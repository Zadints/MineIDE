package org.example.mineide.core.application.dto;

import org.example.mineide.core.domain.annotations.Optional;
import org.example.mineide.core.domain.enums.JdkEnum;

import java.nio.file.Path;

public class NewProjectDto {

    private Path projectLocation;
    private String serverName;

    private MinecraftVersionDTO versionAndSoftware;
    @Optional private Path serverSoftware;

    private JdkEnum jdkSoftware;
    @Optional private Path jdkPath;

    private boolean initializeRepository;
    private boolean createReadme;
    private boolean generateGitIgnore;
    private boolean dowloandDependenciesAutomatic;


}
