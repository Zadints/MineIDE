package org.example.mineide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.mineide.bootstrap.AppCompositionRoot;
import org.example.mineide.bootstrap.ControllerFactory;
import org.example.mineide.infraestructure.lang.InMemoryLangRepository;
import org.example.mineide.domain.exceptions.ApiException;
import org.example.mineide.presentation.navigation.Navigator;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.example.mineide.application.usecase.StartAppUseCase.loadDataReuired;

public class App extends Application {

    private String[] fonts = {
            "Poppins-Regular.ttf",
            "Poppins-Light.ttf",
            "pins-Bold.ttf",
            "Poppins-Black.ttf",
            "PixelifySans-Regular.ttf"
    };

    @Override
    public void start(Stage stage) throws IOException {

        AppCompositionRoot root = new AppCompositionRoot();

        ControllerFactory factory = new ControllerFactory(root);

        for (String s: fonts){
            Font.loadFont(getClass().getResourceAsStream("/fonts/"+s), 14);
        }

        Navigator navigator = new Navigator(stage, factory);

        factory.setNavigator(navigator);

        navigator.navigate("index.fxml");

    }

    public static void main(String[] args) {

        launch(args);
    }
}