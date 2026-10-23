package org.example.mineide.application.usecase.project;

import org.example.mineide.application.dto.create.project.ProjectDto;
import org.example.mineide.domain.entities.Project;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.enums.JdkEnum;
import org.example.mineide.domain.exceptions.CreateNewProjectException;
import org.example.mineide.domain.repository.project.ProjectDeployer;
import org.example.mineide.domain.repository.project.ProjectRepository;
import org.example.mineide.infraestructure.project.SqlProjectRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class CreateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final ProjectDeployer projectDeployer;
    private final ProjectRepository sqlProjectRepository;

    public CreateProjectUseCase(ProjectRepository projectRepository, ProjectDeployer projectDeployer, ProjectRepository sqlProjectRepository){

        this.projectRepository = projectRepository;
        this.projectDeployer = projectDeployer;
        this.sqlProjectRepository = sqlProjectRepository;
    }

    public void create(ProjectDto dto) {

        ProjectDto dtoValidated = validateProjectDto(dto) ;
        String uuid = UUID.randomUUID().toString();

        Project newProject = new Project(
                uuid,
                dtoValidated.getName(),
                dtoValidated.getDeployPath(),
                dtoValidated.getServerJar(),
                dtoValidated.isInitializeRepository(),
                dtoValidated.isCreateReadme(),
                dtoValidated.isGenerateGitIgnore(),
                dtoValidated.isDownloadDependencies()
        );

        projectDeployer.deploy(newProject);

        projectRepository.setActual(newProject);
        projectRepository.add(newProject);

        sqlProjectRepository.add(newProject);

    }

    public boolean isCreatedProject(){
        Project project = projectRepository.getActual();
        if (project == null) return false;
        return true;
    }

    private ProjectDto validateProjectDto(ProjectDto dto){
        return new ProjectDto(
                evalueData(dto.getName()),
                evalueData(dto.getDeployPath()),
                evalueData(dto.getServerJar()),
                dto.isInitializeRepository(),
                dto.isCreateReadme(),
                dto.isGenerateGitIgnore(),
                dto.isDownloadDependencies()
        );
    }

    private String evalueData(String value){
        if (value == null || value.isBlank()){
            throw new CreateNewProjectException(value);
        }
        return value;
    }
    private Path evalueData(Path path) {
        if (path == null){
            throw new CreateNewProjectException("path is null");
        }
        if (!Files.exists(path)) {
            throw new CreateNewProjectException(path + "no exist");
        }

        return path;
    }
    private ServerJar evalueData(ServerJar srv) {
        if (srv == null){
            throw new CreateNewProjectException("internal ServerJar is null");
        }

        return new ServerJar(
                evalueData(srv.getJarFileName()),
                evalueData(srv.getJarVersion()),
                evalueData(srv.getSoftware()),
                evalueData(srv.getBuild()),
                evalueData(srv.getJdkVersion()),
                evalueData(srv.getDescription()),
                evalueData(srv.getActualLocation())
        );
    }
    /**
     * Se asigna JDK_26 en caso viene null o sea en la seleccion del ServerJar de la
     * pc del susuario o api no se puso resolver el JDk requerido.
     **/

    private JdkEnum evalueData(JdkEnum jdk) {
        if (jdk != null) {
            return jdk;
        }
        return JdkEnum.JDK_26;
    }
}
