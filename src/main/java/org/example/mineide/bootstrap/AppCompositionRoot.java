package org.example.mineide.bootstrap;

import org.example.mineide.application.usecase.GetTranslationUseCase;
import org.example.mineide.application.usecase.project.CreateProjectUseCase;
import org.example.mineide.domain.repository.LangRepository;
import org.example.mineide.domain.repository.project.ProjectDeployer;
import org.example.mineide.domain.repository.project.ProjectRepository;
import org.example.mineide.infraestructure.lang.YamlLangLoader;
import org.example.mineide.infraestructure.project.FileSystemProjectDeployer;
import org.example.mineide.infraestructure.project.InMemoryProjectRepository;
import org.example.mineide.infraestructure.project.SqlProjectRepository;

public class AppCompositionRoot {

    private final GetTranslationUseCase translationUseCase;

    private final CreateProjectUseCase createProjectUseCase;

    public AppCompositionRoot() {
        YamlLangLoader langLoader = new YamlLangLoader();
        LangRepository lang = langLoader.load("es");
        translationUseCase = new GetTranslationUseCase(lang);

        ProjectRepository sqlLoader = new SqlProjectRepository();
        ProjectRepository inMemoryProject = new InMemoryProjectRepository(sqlLoader.getAll());
        ProjectDeployer deployerProject = new FileSystemProjectDeployer();
        createProjectUseCase = new CreateProjectUseCase(inMemoryProject,deployerProject ,sqlLoader);

    }

    public CreateProjectUseCase getCreateProjectUseCase() {
        return createProjectUseCase;
    }
    public GetTranslationUseCase getTranslationUseCase() {
        return translationUseCase;
    }

}
