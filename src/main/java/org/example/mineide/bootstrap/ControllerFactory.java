package org.example.mineide.bootstrap;

import javafx.util.Callback;
import org.example.mineide.presentation.controllers.IdeContr;
import org.example.mineide.presentation.controllers.IndexController;
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
                    root.getTranslation(),
                    navigator
            );
        }

        if (type == IdeContr.class) {
            return new IdeContr(
                    root.getTranslation(),
                    navigator
            );
        }


        throw new IllegalArgumentException(
                "Controlador desconocido: " + type.getName()
        );
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
