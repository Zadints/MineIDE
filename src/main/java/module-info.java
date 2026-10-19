module org.example.mineide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.yaml.snakeyaml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;

    opens org.example.mineide to javafx.fxml;
    opens org.example.mineide.presentation.controllers to javafx.fxml;
    opens org.example.mineide.presentation.controllers.center to javafx.fxml;
    opens org.example.mineide.presentation.controllers.mainPageOptions to javafx.fxml;

    exports org.example.mineide.domain.entities;
    exports org.example.mineide.application.usecase;
    exports org.example.mineide.domain.repository;
    exports org.example.mineide;
    exports org.example.mineide.presentation.controllers;
    exports org.example.mineide.application.usecase.Serverjar;
    exports org.example.mineide.application.services;
}