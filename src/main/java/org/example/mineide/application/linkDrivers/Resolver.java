package org.example.mineide.application.linkDrivers;

import org.example.mineide.application.linkDrivers.managers.fastAccesLinkManager;
import org.example.mineide.domain.repository.softwareStrategy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Resolver{

    List<String> fastAcces = new ArrayList<>();



    public void resolve(String name, String Link, Path destination){

        fastAcces.addAll(List.of("PAPER" ,"PURPUR", "Vanilla", "Velocit", "Folia"));

        if (fastAcces.contains(name)){
            softwareStrategy manager = new fastAccesLinkManager();
            manager.manage(Link, destination);
        }
        switch (name){
            case "":
        }
    }

}
