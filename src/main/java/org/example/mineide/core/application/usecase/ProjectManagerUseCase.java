package org.example.mineide.core.application.usecase;

import org.example.mineide.core.application.dto.MinecraftVersionDTO;
import org.example.mineide.core.application.dto.NewProjectDto;
import org.example.mineide.core.application.dto.ResponseDTO;
import org.example.mineide.core.application.dto.VersionDTO;
import org.example.mineide.core.domain.entities.Project;
import org.example.mineide.core.domain.enums.JdkEnum;
import org.example.mineide.infraestructure.models.api.ApiLinksClient;
import org.example.mineide.core.application.usecase.EvalueVersionUseCase;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.*;
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

import static org.example.mineide.core.application.usecase.EvalueVersionUseCase.evalueJDK;

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


//-------------------------------------------------------------------------------------------

        // Evaluar compatibilidad del jdk con la version actual
        JdkEnum jdkVersion;
        try {
            jdkVersion = newProjectDto.getJdkVersion();
        } catch (Exception e){
            jdkVersion = null;
        }
        if (jdkVersion != null && version != null){
            VersionDTO evalueResult = evalueJDK(jdkVersion, version);
            if (evalueResult.getStatus()){}
            else {
                return evalueResult.getMessage();
            }
        }
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
                stm.setString(4,actualProject.getVersion());
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
        MinecraftVersionDTO requeriments = newProjectDto.getVersionAndSoftware();
        deployProject(pathNewProject, requeriments);
        return "";
    }

    private static String deployProject(Path path, MinecraftVersionDTO requeriments){

        //------------------ descargamso todo y creamos carpeta del proyecto mijin:
        ResponseDTO request = new ResponseDTO();
        try (DatagramSocket connection = new DatagramSocket()){
            connection.connect(InetAddress.getByName("8.8.8.8"),10002);
            request.setIp(connection.getLocalAddress().getHostAddress());
        }catch (Exception e){
            return "The internal checks failed. \n" +
                    "connect internet and try again";
        }
        if (request.Ip() != null){
            request.setSoft(requeriments.getType());
            request.setVers(requeriments.getId());
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
