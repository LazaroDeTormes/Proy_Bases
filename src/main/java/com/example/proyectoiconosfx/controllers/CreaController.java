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

import java.io.IOException;

public class CreaController {

    @FXML
    private TextField usuarioTexto;

    @FXML
    private TextField contraTexto;

    @FXML
    private TextField contraTexto2;

    @FXML
    private Button crearBoton;


    @FXML
    private Label status;



    public void accionCrear(ActionEvent actionEvent) throws IOException {

        String usuario=usuarioTexto.getText();
        String pass=contraTexto.getText();
        String pass2=contraTexto2.getText();
        DaoPerfiles con = new DaoPerfiles();

        if(con.insertarUsuario(usuario,pass,pass2)){


            System.out.println("hola");



            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaLoguear.fxml"));
            Parent root = fxmlLoader.load();
            LogginController controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage=new Stage();

            stage.setTitle("Iconos Fx");
            stage.setScene(scene);
            stage.setMinWidth(40);
            stage.setMinHeight(40);
            stage.show();


            stage.getOnCloseRequest();

            Stage actualStage = (Stage) this.crearBoton.getScene().getWindow();
            actualStage.close();





        }
    }
}
