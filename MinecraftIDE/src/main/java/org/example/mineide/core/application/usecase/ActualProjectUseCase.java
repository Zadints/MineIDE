package org.example.mineide.core.application.usecase;

import org.example.mineide.core.domain.entities.Project;

public class ActualProjectUseCase {
    private static Project project = null;

    public static boolean isOpenProject(){

        /*
        if (project == null) return false;
        return true; */
        return true;
    }
}
