package com.example.proyectoiconosfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaLoguear.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Iconos Fx");
        stage.setScene(scene);
        stage.setMinWidth(40);
        stage.setMinHeight(40);
        stage.setMaxWidth(260);
        stage.setMaxHeight(250);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}