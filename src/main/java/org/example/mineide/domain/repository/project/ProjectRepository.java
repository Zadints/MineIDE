package org.example.mineide.domain.repository.project;

import org.example.mineide.domain.entities.Project;

import java.util.List;

public interface ProjectRepository {
    Project get(int index);
    List<Project> getAll();
    Project getActual();
    void add(Project p);
    void setActual(Project p);
    void remove(Project project);
    void update(Project project);
    Project getById(String id);
}
