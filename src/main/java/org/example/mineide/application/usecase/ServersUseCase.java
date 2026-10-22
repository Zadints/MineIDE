package org.example.mineide.application.usecase;


import org.example.mineide.application.dto.create.project.ProjectDto;
import org.example.mineide.domain.entities.Project;

import java.util.ArrayList;

public class ServersUseCase {
    private static ArrayList<Project> ListServes = new ArrayList<Project>();

    public static void AddServer(ProjectDto newServer){
        //verificar que no se repita y evaluar datos que sean reales
        //ListServes.add(newServer);
    }


}
