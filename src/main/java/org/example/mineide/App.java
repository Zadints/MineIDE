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
import org.example.mineide.core.domain.exceptions.ApiException;

import java.io.IOException;

import static org.example.mineide.core.application.usecase.StartAppUseCase.loadDataReuired;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {


        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Poppins-Regular.ttf"),
                14
        );

        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Poppins-Light.ttf"),
                14
        );

        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"),
                14
        );

        Font.loadFont(
                getClass().getResourceAsStream("/fonts/Poppins-Black.ttf"),
                14
        );

        Font.loadFont(
                getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"),
                14
        );



        FXMLLoader fxmlLoader = new FXMLLoader(
                App.class.getResource("mainPage.fxml")
        );

        Parent root = fxmlLoader.load();



        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);



        scene.getStylesheets().addAll(
                getClass().getResource("/styles/index.css").toExternalForm(),
                getClass().getResource("/styles/center-styles.css").toExternalForm(),
                getClass().getResource("/styles/mainPage.css").toExternalForm()
        );


        stage.initStyle(StageStyle.TRANSPARENT);

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        double width = screen.getWidth() * 0.80;
        double height = screen.getHeight() * 0.90;

        stage.setWidth(width);
        stage.setHeight(height);

        stage.setX(
                screen.getMinX() +
                        (screen.getWidth() - width) / 2
        );

        stage.setY(
                screen.getMinY() +
                        (screen.getHeight() - height) / 2
        );


        stage.setMinWidth(1300);
        stage.setMinHeight(700);

        stage.setMaximized(false);

        stage.setTitle("MineIDE");

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        try {
            loadDataReuired();
        }catch (ApiException e){
            //aqui colocaremos una exception personalizada para mostrar en ui
        }

        launch();
    }
}