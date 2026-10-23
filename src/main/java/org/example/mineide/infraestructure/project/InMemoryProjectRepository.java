package org.example.mineide.infraestructure.project;

import org.example.mineide.domain.entities.Project;
import org.example.mineide.domain.exceptions.CreateNewProjectException;
import org.example.mineide.domain.repository.project.ProjectDeployer;
import org.example.mineide.domain.repository.project.ProjectRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class InMemoryProjectRepository implements ProjectRepository {

    private final List<Project> projects;
    private Project actualProject;

    public InMemoryProjectRepository(List<Project> projects){
        this.projects = new ArrayList<>(
                projects != null ? projects : List.of()
        );
    }

    @Override
    public Project get(int index) {
        if (index < 0 || index >= projects.size()) {
            return null;
        }

        return projects.get(index);
    }

    @Override
    public List<Project> getAll() {
        return projects;
    }

    @Override
    public Project getActual() {
        return actualProject;
    }

    @Override
    public void add(Project p) {
        projects.add(p);
    }

    @Override
    public void setActual(Project p) {
        actualProject = p;
    }

    @Override
    public void remove(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public Project getById(String id) {
        return null;
    }
}
