package org.example.mineide.presentation.navigation;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;

public class Navigator {

    private final Stage stage;
    private final Callback<Class<?>, Object> controllerFactory;

    public Navigator(Stage stage, Callback<Class<?>, Object> controllerFactory ) {
        this.stage = stage;
        this.controllerFactory = controllerFactory;
        this.stage.initStyle(StageStyle.TRANSPARENT);
    }




    public void navigate(String fxmlPath) throws IOException{
        navigate(fxmlPath, 0.90, 0.95);
    }

    public void navigate(String fxmlPath, double widthRatio, double heightRatio) throws IOException {



        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/mineide/" + fxmlPath)
        );

        loader.setControllerFactory(controllerFactory);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);

        scene.getStylesheets().addAll(
                getClass().getResource("/styles/index.css").toExternalForm(),
                getClass().getResource("/styles/center-styles.css").toExternalForm(),
                getClass().getResource("/styles/center/guide.css").toExternalForm(),
                getClass().getResource("/styles/mainPage.css").toExternalForm()
        );

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        double width = screen.getWidth() * widthRatio;
        double height = screen.getHeight() * heightRatio;

        stage.setWidth(width);
        stage.setHeight(height);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }




}
