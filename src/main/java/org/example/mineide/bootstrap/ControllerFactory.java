package org.example.mineide.bootstrap;

import javafx.util.Callback;
import org.example.mineide.presentation.controllers.ErrorPageController;
import org.example.mineide.presentation.controllers.IdeController;
import org.example.mineide.presentation.controllers.IndexController;
import org.example.mineide.presentation.controllers.center.*;
import org.example.mineide.presentation.controllers.mainPageOptions.CreateProjectController;
import org.example.mineide.presentation.navigation.Navigator;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final AppCompositionRoot root;
    private Navigator navigator;

    public ControllerFactory(AppCompositionRoot root) {
        this.root = root;
    }

    @Override
    public Object call(Class<?> type) {

        if (type == IndexController.class) {
            return new IndexController(
                    root.getTranslationUseCase(),
                    navigator,
                    root.getCreateProjectUseCase()

            );
        }

        if (type == IdeController.class) {
            return new IdeController(
                    root.getTranslationUseCase(),
                    navigator,
                    root.getCreateProjectUseCase()
            );
        }

        if (type == CreateProjectController.class) {
            return new CreateProjectController(
                    root.getCreateProjectUseCase(),
                    navigator
            );
        }

        if (type == ConsoleController.class) {
            return new ConsoleController();
        }
        if (type == FilesController.class) {
            return new FilesController();
        }
        if (type == SettingController.class) {
            return new SettingController();
        }
        if (type == BackupsController.class) {
            return new BackupsController();
        }
        if (type == DatabasesController.class) {
            return new UsersController();
        }
        if (type == UsersController.class) {
            return new UsersController();
        }
        if (type == StartupController.class) {
            return new StartupController();
        }
        if (type == GuideController.class) {
            return new GuideController();
        }

        if (type == ErrorPageController.class) {
            return new ErrorPageController();
        }


        throw new IllegalArgumentException(
                "Controlador desconocido: " + type.getName()
        );
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
