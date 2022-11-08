package com.example.proyectoiconosfx.controllers;

import com.example.proyectoiconosfx.HelloApplication;
import com.example.proyectoiconosfx.models.Icono;
import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPerfiles {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/perfilesbbdd";
    private Connection con;




    public DaoPerfiles() {
        try {
            con = DriverManager.getConnection(jdbcUrl, "root", "root");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    public boolean comprobarUsuario(String nombre,String password, Label status){
        boolean valido=false;
        String resultado="";

        String busqueda = "select contrasenha from usuarios where nombre='"+nombre+"';";
        ResultSet rs;
        Statement stmt;

        var encript = BcryptFunction.getInstance(12);

        var result = encript.hash(password).getResult();


        System.out.println(result);
        try{
            System.out.println(busqueda);
            stmt=con.createStatement();
            rs=stmt.executeQuery(busqueda);
            while(rs.next()){
                resultado=rs.getString(1);

            }
            var result2 = encript.check(resultado,result);
            System.out.println(result2);

        if(resultado.equals("")) {
            status.setText("No existe el usuario");
        }else if(result2){
            status.setText("Bienvenido");
            valido=true;

        }else{

            status.setText("La contraseña es incorrecta");
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return valido;

    }


}
