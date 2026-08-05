package org.example.mineide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Geist-SemiBold.ttf"),14
        );
        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Geist-Medium.ttf"),14
        );
        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Geist-Regular.ttf"),14
        );
        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Geist-Bold.ttf"),14
        );
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().addAll(
                getClass().getResource("/styles/app.css").toExternalForm(),
                getClass().getResource("/styles/console-style.css").toExternalForm()
        );
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1300);
        stage.setHeight(700);
        stage.setMaximized(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//https://getnova.zip/