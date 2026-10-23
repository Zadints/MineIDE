package org.example.mineide.application.dto;

import javafx.scene.layout.BorderPane;

public record NavigateCenterDto(
        BorderPane container, String fileName
) {
}
