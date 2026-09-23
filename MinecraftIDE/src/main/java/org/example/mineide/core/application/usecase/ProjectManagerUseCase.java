package org.example.mineide.core.application.usecase;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.application.dto.NewProjectDto;
import org.example.mineide.core.domain.entities.Project;
import org.example.mineide.core.domain.enums.JdkEnum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectManagerUseCase {
    private static Project actualProject = null;
    private static List<Project> projectsCreatedList = new ArrayList<>();

    public static boolean isOpenProject(){

        /*
        if (project == null) return false;
        return true; */
        return true;
    }
    private static boolean evalueData(String value){
        if (value == null || value.isBlank()){
            return false;
        }
        return true;
    }
    private static Path evaluePath(String value) throws InvalidPathException {
        if (value == null || value.isBlank()){
            return null;
        }
        Path path = Path.of(value);

        if (!Files.exists(path)) {
            return null;
        }

        return path;
    }

    public static String createProject(NewProjectDto newProjectDto) {

        String serverName = newProjectDto.getServerName();
        if (!evalueData(serverName)) return "No ingresaste el nombre del servidor";
        Path pathNewProject;
        try {
            pathNewProject = evaluePath(newProjectDto.getLocationNewProject());
        } catch (InvalidPathException e) {
            return "La ruta del directorio a crear no existe o está mal escrita";
        }
//-------------------------------------------------------------------------------------------
        Path pathSoftware = null;

        String version = null;
        String software = null;

        String softwarePath = newProjectDto.getServerSoftwarePath();

        if (evalueData(softwarePath)) {

            try {
                pathSoftware = Path.of(softwarePath);

                if (!Files.isRegularFile(pathSoftware)) {
                    return "La ruta del server.jar no existe";
                }

            } catch (InvalidPathException e) {
                return "La ruta del server.jar está mal escrita";
            }

        } else {

            MinecraftVersionDTO versionAndSoftware =
                    newProjectDto.getVersionAndSoftware();

            version = versionAndSoftware.getId();
            software = versionAndSoftware.getType();

            if (!evalueData(version) || !evalueData(software)) {
                return "Debes seleccionar una versión y software";
            }
        }

//-------------------------------------------------------------------------------------------
        String jdkPath = newProjectDto.getJdkPath();
        Path jdkPathConverter = null;
        try {
            jdkPathConverter = evaluePath(jdkPath);
        } catch (InvalidPathException e){
            return "la ruta para usar el jdk es inválida déjala en automática";
        }

        JdkEnum jdkVersion = newProjectDto.getJdkVersion();
//-------------------------------------------------------------------------------------------

        boolean initializeRepository = newProjectDto.isInitializeRepository();
        boolean createReadme = newProjectDto.isCreateReadme();
        boolean generateGitIgnore = newProjectDto.isGenerateGitIgnore();
        boolean downloadDependencies = newProjectDto.isDownloadDependencies();

        String uuid = UUID.randomUUID().toString();

        actualProject = new Project(
                uuid,
                serverName,
                pathNewProject,
                newProjectDto.getVersionAndSoftware(),
                jdkVersion,
                jdkPathConverter,
                initializeRepository,
                createReadme,
                generateGitIgnore,
                downloadDependencies
                );
        projectsCreatedList.add(actualProject);
        deployProject();
        //agregar guardar en db ruta con proyecto
        return "";
    }

    private static String deployProject(){
        /*
        //------------------ descargamso todo y creamos carpeta del proyecto mijin:
        try {
            createWorkspace(pathNewProject);
            //evaluar y descargar aqui el sofware con el jdk lo mismo bro
        }catch (IOException e){

        }

*/
        return "";
    }


    private static void createWorkspace(Path selectedPath) throws IOException {

        Path mideDirectory = selectedPath.resolve(".mide");

        Files.createDirectories(mideDirectory);

        Path workspaceFile = mideDirectory.resolve("workspace.yml");

        if (!Files.exists(workspaceFile)) {
            Files.createFile(workspaceFile);
        }
    }
}
