package com.example.proyectoiconosfx.controllers;

import com.example.proyectoiconosfx.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogginController {

    @FXML
    private TextField usuarioTexto;

    @FXML
    private TextField contraTexto;

    @FXML
    private Button aceptarBoton;


    /**
     * Método que crea ventana principal y cierra la actual si el usuario y contraseña son correctos
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void accionAceptar(ActionEvent actionEvent) throws IOException {
        String usuario=usuarioTexto.getText();
        String pass=contraTexto.getText();
        DaoPerfiles con = new DaoPerfiles();

        if(controlLoguin(usuario,pass)) {

            if (con.comprobarUsuario(usuario, pass)) {
                mostrarAlertaCorrecto();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaPrincipal.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setTitle("Iconos Fx");
                stage.setScene(scene);
                stage.setMinWidth(740);
                stage.setMinHeight(490);
                stage.show();
                stage.getOnCloseRequest();
                Stage actualStage = (Stage) this.aceptarBoton.getScene().getWindow();
                actualStage.close();


            }else{
                mostrarAlertaError("El usuario o la contraseña son incorrectos");
            }
        }

    }

    /**
     * Metodo que muestra un mensaje de error en pantalla
     * @param error
     */
    private void mostrarAlertaError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    /**
     * Metodo que muestra un mensaje de usuario verificado
     */
    private void mostrarAlertaCorrecto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Pasalo bien :)");
        alert.setTitle("Usuario");
        alert.setContentText("Usuario verificado");
        alert.showAndWait();
    }

    /**
     *Metodo que crea la pantalla de creación de usuario y cierra la actual.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void llevarPantallaCreacion (ActionEvent actionEvent) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaCreacion.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setTitle("Usuario");
            stage.setScene(scene);
            stage.setMinWidth(242);
            stage.setMinHeight(208);
            stage.setMaxWidth(260);
            stage.setMaxHeight(250);
            stage.show();
            stage.getOnCloseRequest();
            Stage actualStage = (Stage) this.aceptarBoton.getScene().getWindow();
            actualStage.close();

        }

    /**
     * Metodo que controla las cajas de texto
     * @param usuario
     * @param contrasenha
     * @return
     */
    private boolean controlLoguin(String usuario,String contrasenha){
    boolean correcto=false;

        if(usuario.equals("") && contrasenha.equals("")) {

            mostrarAlertaError("rellena los campos");
        }else{
            if(usuario.equals("")){

                mostrarAlertaError("Introduce usuario");

            }else if(contrasenha.equals("")){

                mostrarAlertaError("introduce la contraseña");

            }else{

                System.out.println("correcto");

                correcto=true;
            }
        }
    return correcto;
    }


    }

