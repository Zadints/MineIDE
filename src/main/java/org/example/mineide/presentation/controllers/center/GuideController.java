package org.example.mineide.presentation.controllers.center;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GuideController {

    @FXML private VBox guideContainer;
    @FXML private VBox configureCard;
    @FXML private VBox languageCard;
    @FXML private VBox startCard;
    @FXML private VBox pluginsCard;

    @FXML
    private void initialize() {
        animateIn(guideContainer, 0);

        animateIn(configureCard, 120);
        animateIn(languageCard, 200);
        animateIn(startCard, 280);
        animateIn(pluginsCard, 360);
    }

    private void animateIn(Node node, int delay) {
        node.setOpacity(0);
        node.setTranslateY(18);

        FadeTransition fade = new FadeTransition(
                Duration.millis(450), node
        );
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setDelay(Duration.millis(delay));

        TranslateTransition slide = new TranslateTransition(
                Duration.millis(450), node
        );
        slide.setFromY(18);
        slide.setToY(0);
        slide.setDelay(Duration.millis(delay));

        new ParallelTransition(fade, slide).play();
    }

    @FXML private void onConfigureServer() {

    }

    @FXML private void onChangeLanguage() {

    }

    @FXML private void onStartServer() {

    }

    @FXML private void onInstallPlugins() {

    }


}
