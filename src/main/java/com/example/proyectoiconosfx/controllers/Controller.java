package com.example.proyectoiconosfx.controllers;
import com.example.proyectoiconosfx.HelloApplication;
import com.example.proyectoiconosfx.models.Icon;
import com.example.proyectoiconosfx.models.Icono;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static com.example.proyectoiconosfx.controllers.Funcion_Guardado.*;


public class Controller implements Initializable {


    @FXML
    private ComboBox<String> catIdCombox;
    @FXML
    private ComboBox<String> grupoIdCombox;

    @FXML
    private Label iconoLabel;

    @FXML
    private TableView<Icono> tableIcons;

    @FXML
    private CheckBox chkJson;

    @FXML
    private CheckBox chkXml;

    @FXML
    private CheckBox chkBin;

    @FXML
    private CheckBox chkTex;

    @FXML
    private Button guardarId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Inicia conexion*/

        ConexionSql con = new ConexionSql();

        /*Carga los ComboBox*/
        this.catIdCombox.setItems(con.checkCat());
        this.grupoIdCombox.setItems(con.checkGrupo());


    }





    /**
     * Este método recoge un emoticono aleatorio y lo muestra en la interfaz gráfica.
     *
     * @param actionEvent detecta cuando se pulsa el botón correspondiente
     */
    @FXML
    public void aleatorio(ActionEvent actionEvent) {

        try {
            ConexionSql con = new ConexionSql();

            String icono = con.iconoAleatorio().trim();
            System.out.println("iconocontroller: " + icono);
            String substitucion = icono.replace("U+", "0x");
            int entero = Integer.decode(substitucion);
            String iconoTraducido = new String(Character.toChars(entero));
            iconoLabel.setText(iconoTraducido);


        } catch (Exception e) {
            System.out.println(e);

        }

    }


    /**
     * Este método recoge la lista de emoticonos de un grupo concreto y los muestra en la tabla de la interfaz gráfica.
     *
     * @param actionEvent detecta cuando se pulsa el botón correspondiente
     */
    public void buscarGrupo(ActionEvent actionEvent) {

        try {
            ConexionSql con = new ConexionSql();
            if (!grupoIdCombox.getSelectionModel().isEmpty()) {
                tableIcons.getItems().clear();
                List<Icono> lista = con.busquedaGrupo(grupoIdCombox.getSelectionModel().getSelectedItem());
                List<Icono> listaFinalIconos = traduccionIconos(lista);
                tableIcons.getItems().addAll(listaFinalIconos);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Este método recoge la lista los emoticonos de una categoría en concreto y los muestra en la tabla de la interfaz gráfica.
     *
     * @param actionEvent detecta cuando se pulsa el botón correspondiente
     */
    public void buscarCategoria(ActionEvent actionEvent) {

        try {
            if (!catIdCombox.getSelectionModel().isEmpty()) {
                ConexionSql con = new ConexionSql();
                tableIcons.getItems().clear();
                tableIcons.getItems().clear();
                List<Icono> lista = con.busquedaCategoria(catIdCombox.getSelectionModel().getSelectedItem());
                List<Icono> listaFinalIconos = traduccionIconos(lista);
                tableIcons.getItems().addAll(listaFinalIconos);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    /**
     * Este método comprueba los checkBox seleccionados y ejecuta los metodos correspondientes para su guardado
     */
    public void checkSave(Icono[] datos) {
        if (chkJson.isSelected()) {
            json(datos);
        }
        if (chkBin.isSelected()) {
            bin(datos);
        }
        if (chkXml.isSelected()) {
            xml(datos);
        }
        if (chkTex.isSelected()) {
            txt(datos);
        }
    }

    /**
     * Metodo que modifica el código del emoji para hacerlo visible en java
     */
    public List<Icono> traduccionIconos(List<Icono> iconos) {
        List<Icono> listaFinalIconos = new ArrayList<>();
        Iterator<Icono> it = iconos.iterator();
        System.out.println("ssss");
        while (it.hasNext()) {

            Icono icon = it.next();
            String[] iconoViejo = icon.getUnicode().split(" ");
            String substitucion = iconoViejo[0].replace("U+", "0x");
            int entero = Integer.decode(substitucion);
            String iconoTraducido = new String(Character.toChars(entero));
            icon.setUnicode(iconoTraducido);
            listaFinalIconos.add(icon);

        }
        return listaFinalIconos;
    }

    /**
     * Este método recibe los datos de la tabla y los manda como parámetro al método «checkSave», el cual discierne
     * entre los diferentes métodos de guardado.
     *
     * @param event Al pulsar el botón guarda los datos de la tabla según el checkbox seleccionado
     */
    @FXML
    public void setGuardarId(ActionEvent event) {
        Icono[] icons = tableIcons.getItems().toArray(Icono[]::new);

        if (icons.length > 0) {
            checkSave(icons);
        }

    }

    /**********************************descarga api y la transforma en ficheros csv************/
    public void descargarAll() {

        try {
            String enlaceFijo = "https://emojihub.herokuapp.com/api/";
            URL enlace = new URL(enlaceFijo + "all");
            ObjectMapper mapa = new ObjectMapper();
            Icon[] respuesta = mapa.readValue(enlace, Icon[].class);
            Funcion_Guardado.guardarCsvAll(respuesta);
            Funcion_Guardado.guardarCsvGrupo(respuesta);
            Funcion_Guardado.guardarCsvCategoria(respuesta);


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /*********************************restablecer bbdd**********************************/
    public void restablecerBaseDatos() {
        try {
            descargarAll();
            Path catDir = Path.of("res/ficheros/sql/ficheroCat.txt");

            try (BufferedReader br = Files.newBufferedReader(catDir)) {
                String linea;
                ConexionSql con = new ConexionSql();
                while ((linea = br.readLine()) != null) {
                    con.insertarCat(linea);
                    System.out.println(linea);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Path grupoDir = Path.of("res/ficheros/sql/ficheroGrupo.txt");

            try (BufferedReader br = Files.newBufferedReader(grupoDir)) {
                String linea;
                ConexionSql con = new ConexionSql();
                while ((linea = br.readLine()) != null) {
                    con.insertarGrupo(linea);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Path iconoDir = Path.of("res/ficheros/sql/ficheroIconos.txt");

            try (BufferedReader br = Files.newBufferedReader(iconoDir)) {
                String linea;
                ConexionSql con = new ConexionSql();
                while ((linea = br.readLine()) != null) {
                    String[] lineaSeparada = linea.split(",");
                    con.insertarIcono(lineaSeparada);
                    System.out.println(lineaSeparada[0] + "," + lineaSeparada[1] + "," + lineaSeparada[2] + "," + lineaSeparada[3]);


                }
                System.out.println("Base de datos actualizada!!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (Exception e) {
            System.out.println("Fallo en el acceso a la API: "+e);
        }
    }
}
