package org.example.mineide.infraestructure.project;

import org.example.mineide.domain.entities.Project;
import org.example.mineide.domain.repository.project.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SqlProjectRepository implements ProjectRepository  {

    private final List<Project> projects = new ArrayList<>();

    public SqlProjectRepository(){
        //aqui meter logica de arga de SQl para givear mediante get
    }

    @Override
    public Project get(int index) {
        return null;
    }

    public List<Project> getAll() {
        return null;
    }

    @Override
    public Project getActual() {
        return null;
    }

    public void add(Project p) {

    }

    @Override
    public void setActual(Project p) {

    }

    public void remove(Project p) {

    }
    public void update(Project p) {

    }

    @Override
    public Project getById(String id) {
        return null;
    }


}


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
