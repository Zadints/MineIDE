package org.example.mineide.infraestructure.project;

import org.example.mineide.domain.entities.Project;
import org.example.mineide.domain.exceptions.CreateNewProjectException;
import org.example.mineide.domain.repository.project.ProjectDeployer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileSystemProjectDeployer implements ProjectDeployer {

    @Override
    public void deploy(Project project) {

        Path destinationJar = project.getProjectLocation()
                .resolve(project.getServerJarName());

        try{
            createWorkspace(project);
            copyServerJar(
                    project.getServerJarActualLocation(),
                    destinationJar
            );
        }catch (IOException e){
            throw new CreateNewProjectException("deploy project in directory");
        }
    }

    private void createWorkspace(Project project) throws IOException {

        Path projectDeployPath = project.getProjectLocation();
        Path mideDirectory = projectDeployPath.resolve(".mide");
        Files.createDirectories(mideDirectory);
        Path workspaceFile = mideDirectory.resolve("workspace.yml");

        String content = """
        id: %s
        server-name: %s
        project-path:  %s
        server-jar:
            jar-name: "%s"
            jar-version: "%s"
            jar-software: "%s"
            jar-build: "%s"
            jdk-version: "%s"
            jar-description: "%s"
            jar-path: "%s"

        initialize-repository: "%s"
        create-readme: "%s"
        generate-git-ignore: "%s"
        download-dependencies: "%s"

        
        """.formatted(
                project.getId(),
                project.getServerName(),
                project.getProjectLocation(),

                project.getServerJarName(),
                project.getJdkVersion(),
                project.getServerJarSoftware(),
                project.getServerJarBuild(),
                project.getServerJar().getJarVersion(),
                project.getServerJarDescription(),
                project.getServerJarActualLocation(),

                project.isInitializeRepository(),
                project.isCreateReadme(),
                project.isGenerateGitIgnore(),
                project.isDownloadDependencies()

        );

        if (Files.notExists(workspaceFile)) {
            Files.writeString(workspaceFile, content);
        }

    }

    private void copyServerJar(Path sourcePath, Path destinationPath) throws IOException {

        sourcePath = sourcePath.toAbsolutePath().normalize();
        destinationPath = destinationPath.toAbsolutePath().normalize();

        if (sourcePath.equals(destinationPath)) {
            return;
        }

        Files.copy(
                sourcePath,
                destinationPath,
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}
