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

public class CreacionController {

    @FXML
    private TextField usuarioTexto;

    @FXML
    private TextField contraTexto;

    @FXML
    private TextField contraTexto2;

    @FXML
    private Button crearBoton;


    @FXML
    private Button atras;

    /**
     * Metodo que crea una pantalla de loguin y cierra la actual,si el usuario fue creado correctamente.
     * @param actionEvent accion del boton crear
     * @throws IOException
     */
    public void accionCrear(ActionEvent actionEvent) throws IOException {

        String usuario=usuarioTexto.getText();
        String pass=contraTexto.getText();
        String pass2=contraTexto2.getText();
        DaoPerfiles con = new DaoPerfiles();
        if(controlLoguin(usuario,pass)) {
            if (equivalencia(pass,pass2)) {
                if (con.insertarUsuario(usuario, pass)) {

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaLoguear.fxml"));
                    Parent root = fxmlLoader.load();
                    LogginController controller = fxmlLoader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setTitle("Iconos Fx");
                    stage.setScene(scene);
                    stage.setMinWidth(40);
                    stage.setMinHeight(40);
                    stage.setMaxWidth(260);
                    stage.setMaxHeight(250);

                    stage.show();


                    stage.getOnCloseRequest();
                    mostrarAlertaCorrecto();
                    Stage actualStage = (Stage) this.crearBoton.getScene().getWindow();
                    System.out.println("Usuario Creado");
                    actualStage.close();

                } else {
                   mostrarAlertaError("El usuario ya existe");
                }

            } else {
                System.out.println("Las contraseña no coincide");
            }
        }
    }

    /**
     * Metodo de mensaje de alerta
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
     * Metodo de mensaje que el usuario ha sido creado correctamente
     */
    private void mostrarAlertaCorrecto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logueate por favor :)");
        alert.setTitle("Usuario");
        alert.setContentText("Usuario Creado");
        alert.showAndWait();
    }

    /**
     * Metodo que comprueba que las cajas de los textos contengan texto de un determinado tamaño minimo
     * @param usuario
     * @param contrasenha
     * @return boolean
     */
    private boolean controlLoguin(String usuario,String contrasenha){
        boolean correcto=false;

        if(usuario.length()<4 && contrasenha.length()<4) {
            System.out.println("Introduce un usuario de mas de 4 caracteres\n " +
                               "Introduce una contrasenha de mas de 4 caracteres\n");

            mostrarAlertaError("Introduce un usuario de mas de 4 caracteres\n"+
                               "Introduce una contraseña de mas de 4 caracteres");

        }else{
            if(usuario.length()<4){
                System.out.println("Introduce un usuario de mas de 4 caracteres");
                mostrarAlertaError("Introduce un usuario de mas de 4 caracteres");

            }else if(contrasenha.length()<4){
                System.out.println("Introduce una contraseña de mas de 4 caracteres");
                mostrarAlertaError("Introduce una contraseña de mas de 4 caracteres");

            }else{

                System.out.println("correcto");

                correcto=true;
            }
        }
        return correcto;
    }

    /**
     * Compara que las dos contraseñas sean iguales
     * @param contrasenha
     * @param contrasenha2
     * @return boolean
     */
    public boolean equivalencia(String contrasenha,String contrasenha2){
        boolean correcto=false;

        if(contrasenha.equals(contrasenha2)){
            correcto=true;

        }else{
            mostrarAlertaError("Las contraseñas no coinciden");
        }

        return correcto;
    }

    /**
     * Método que crea una pantalla de loguin y cierra la actual
     * @param event
     * @throws IOException
     */
    public void accionAtras(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantallaLoguear.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Iconos Fx");
        stage.setScene(scene);
        stage.setMinWidth(40);
        stage.setMinHeight(40);
        stage.setMaxWidth(260);
        stage.setMaxHeight(250);
        stage.show();
        stage.getOnCloseRequest();
        Stage actualStage = (Stage) this.atras.getScene().getWindow();
        actualStage.close();
    }

}
