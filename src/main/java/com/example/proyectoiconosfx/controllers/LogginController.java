package com.example.proyectoiconosfx.controllers;

import com.example.proyectoiconosfx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.io.IOException;

public class LogginController {

    @FXML
    private TextField usuarioTexto;

    @FXML
    private TextField contraTexto;

    @FXML
    private Button aceptarBoton;


    @FXML
    private Label status;

    @FXML
    public void accionAceptar(ActionEvent actionEvent) throws IOException {
        String usuario=usuarioTexto.getText();
        String pass=contraTexto.getText();

        DaoPerfiles con = new DaoPerfiles();

        if(con.comprobarUsuario(usuario,pass, status)){






            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaPrincipal.fxml"));
            Parent root = fxmlLoader.load();
            Controller controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage=new Stage();

            stage.setTitle("Iconos Fx");
            stage.setScene(scene);
            stage.setMinWidth(740);
            stage.setMinHeight(490);
            stage.show();


            stage.getOnCloseRequest();

            Stage actualStage = (Stage) this.aceptarBoton.getScene().getWindow();
            actualStage.close();





        }

    }
}
