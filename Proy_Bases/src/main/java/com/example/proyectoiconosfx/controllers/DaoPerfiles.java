package com.example.proyectoiconosfx.controllers;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;


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


    /**
     * Método que comprueba si el usuario existe,si existe compara la contraseña usando Bcrypt
     * @param nombre
     * @param password
     * @return boolean
     */
    public boolean comprobarUsuario(String nombre,String password){
        boolean valido=false;
        String resultado="";
            try {
                String busqueda = "select contrasenha from usuarios where nombre='" + nombre + "';";
                ResultSet rs;
                Statement stmt;

                try {
                    System.out.println(busqueda);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(busqueda);
                    while (rs.next()) {
                        resultado = rs.getString(1);

                    }
                    System.out.println(resultado);

                    valido=BCrypt.checkpw(password, resultado);
                    System.out.println(valido);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }catch(Exception ex){
                System.out.println(""+ex);
            }
        return valido;

    }

    /**
     * Método que comprueba si el usuario existe, si no existe lo crea con el usuario y contraseña introducidos,
     * lo encripta con bcrypt
     * @param nombre
     * @param password
     * @return
     */
    public boolean insertarUsuario(String nombre,String password){
        boolean valido=false;
        String resultado="";
        String busqueda = "select nombre from usuarios where nombre='"+nombre+"';";
        ResultSet rs;
        Statement stmt;

        try{

            stmt=con.createStatement();
            rs=stmt.executeQuery(busqueda);
            while(rs.next()){
                resultado=rs.getString(1);

            }
            System.out.println(resultado);
            if(resultado.equals("")) {
                String encriptado = BCrypt.hashpw(password, BCrypt.gensalt(10));

                System.out.println(encriptado);
                String insercion = "insert into usuarios (nombre, contrasenha) values ('" + nombre + "','" + encriptado + "')";

                try {
                    System.out.println(insercion);
                    stmt = con.createStatement();
                    int insert = stmt.executeUpdate(insercion);
                    valido = true;


                } catch (Exception e) {
                    System.out.println("" + e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return valido;

    }




}
