package org.example.mineide.application.usecase;

import org.example.mineide.application.dto.NewProjectDto;
import org.example.mineide.domain.entities.Project;
import org.example.mineide.domain.entities.ServerJar;
import org.example.mineide.domain.enums.JdkEnum;
import org.example.mineide.domain.exceptions.CreateNewProjectException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.StandardCopyOption;
import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectManagerUseCase {
    ObjectMapper mapper = new ObjectMapper();
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
    public static boolean evalueTable(Connection con, String Table){
        try{
            PreparedStatement stm = con.prepareStatement("SELECT * FROM " + Table + " LIMIT 1;");
            ResultSet rs = stm.executeQuery();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }


    }

    public static String createProject(NewProjectDto newProjectDto) {

        String serverName = newProjectDto.getServerName();
        if (!evalueData(serverName)) return "No ingresaste el nombre del servidor";

        Path pathNewProject;

        try {
            pathNewProject = evaluePath(newProjectDto.getLocationNewProject());

            if (pathNewProject == null){
                return "El campo de la ruta no cincide con ninguna carpeta o no es una ruta";
            }
        } catch (InvalidPathException e) {
            return "La ruta del directorio a crear no existe o está mal escrita";
        }
//-------------------------------------------------------------------------------------------
        //por default vienen con valores default xd

        JdkEnum jdkVersion = newProjectDto.getJdkVersion();

        if (jdkVersion == null) throw new IllegalArgumentException("jdkVersion is null");

        ServerJar serverJar = newProjectDto.getServerJar();
        serverJar.setJdkVersion(jdkVersion);

//-------------------------------------------------------------------------------------------

        boolean initializeRepository = newProjectDto.isInitializeRepository();
        boolean createReadme = newProjectDto.isCreateReadme();
        boolean generateGitIgnore = newProjectDto.isGenerateGitIgnore();
        boolean downloadDependencies = newProjectDto.isDownloadDependencies();

        String uuid = UUID.randomUUID().toString();

        Project newProject = new Project(
                uuid,
                serverName,
                pathNewProject,
                serverJar,
                initializeRepository,
                createReadme,
                generateGitIgnore,
                downloadDependencies
                );

        actualProject = newProject;
        projectsCreatedList.add(newProject);
        deployNewProject(newProject);

        return "";
    }

    /**
     * This method has the sole responsibility of calling other
     * methods for the purpose of implementing a newly created project
     * via the IDE itself, using the main menu.
     * @param  newProject
     *              The required Project type parameter
     **/
    private static String deployNewProject(Project newProject){

        try {
            createWorkspace(newProject);
            copyServerJar(
                    newProject.getServerJarActualLocation(),
                    newProject.getProjectLocation()
            );

        } catch (IOException e){
            throw new CreateNewProjectException(e.getMessage());
        }

        return "";
    }

    /**
     * This method has the sole responsibility of creating directory .mide
     * @param  project
     *              The data deploy project required
     **/
    public static void createWorkspace(Project project) throws IOException {

        Path projectDeployPath = project.getProjectLocation();
        Path mideDirectory = projectDeployPath.resolve(".mide");
        Files.createDirectory(mideDirectory);
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
    public static void copyServerJar(Path sourcePath, Path destinationPath)
            throws IOException {

        if (sourcePath.equals(destinationPath)) return;

        Path parent = destinationPath.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.copy(
                sourcePath,
                destinationPath
        );

    }

    /*
    *
    *
        ResponseDTO request = new ResponseDTO();
        try (DatagramSocket connection = new DatagramSocket()){
            connection.connect(InetAddress.getByName("8.8.8.8"),10002);
            request.setIp(connection.getLocalAddress().getHostAddress());
        }catch (Exception e){
            return "The internal checks failed. \n" +
                    "connect internet and try again";
        }
        if (request.Ip() != null){
            request.setSoft(requeriments.getSoftware());
            request.setVers(requeriments.getVersion());
            try {
                createWorkspace(path);
                ResponseDTO response = ApiLinksClient.getLink(request);
                if (response.Status()){

                }else{
                    return "request result in a failure \n try again in some seconds";
                }
            }catch (IOException e){
                return "Deployment error whit API request or internal managment";
            }
        }else{
            return "Error in registre requeriments";
        }
    *
    *
    *
    *

    * */


    /*Lore guardame aqui en el cache que pusiste
        toda la entidad Project pero hacelo por procedimientos almacenados ocn metodos separados


    File CacheDierctory = new File(System.getenv("LOCALAPPDATA") + File.separator +"MineIDE" + File.separator + "Cache" + File.separator + "DB");
        if (!CacheDierctory.exists()){
        CacheDierctory.mkdirs();
    }
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + CacheDierctory.getPath() + File.separator + "CacheDB");) {
        if (evalueTable(con,"PROJECTS")){
            PreparedStatement stm = con.prepareStatement("INSERT INTO PROJECTS (ID, NAME, PATH, VERSION) VALUES (?, ?, ?, ?)");
            stm.setString(1,uuid);
            stm.setString(2,serverName);
            stm.setString(3,pathNewProject.toString());
            stm.setString(4,actualProject.getServerJarVersion());
            stm.executeUpdate();
            stm.close();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM PROJECTS");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("NAME"));
            }
        } else {
            Statement stm = con.createStatement();
            stm.execute("CREATE TABLE PROJECTS(" +
                    "ID INTEGER UNIQUE , " +
                    "NAME TEXT NOT NULL, " +
                    "PATH TEXT NOT NULL, " +
                    "VERSION TEXT NOT NULL" +
                    ");"
            );
            return "DataBase created, try now";
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Error with sql conecction";
    }

*/
}
