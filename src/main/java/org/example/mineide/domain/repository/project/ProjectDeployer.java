package org.example.mineide.domain.repository.project;

import org.example.mineide.domain.entities.Project;

public interface ProjectDeployer {
    void deploy(Project project);
}
