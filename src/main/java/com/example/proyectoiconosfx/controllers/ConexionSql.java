package com.example.proyectoiconosfx.controllers;

import com.example.proyectoiconosfx.models.Icon;
import com.example.proyectoiconosfx.models.Icono;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConexionSql {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/iconosbbdd";
    private Connection con;


    public ConexionSql() {
        try {
            con = DriverManager.getConnection(jdbcUrl, "root", "root");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Inserta en la tabla categoria, una categoria unica del ficheroCategoria
     *
     * @param cat string
     */
    public void insertarCat(String cat) {

        PreparedStatement ps;
        try {
            String insert = "INSERT INTO categorias (nombre_cat)VALUES(?);";
            ps = con.prepareStatement(insert);
            ps.setString(1, cat);
            ps.execute();
            System.out.println("categoria insertada");


        } catch (SQLException e) {
            System.out.println(e);

        }

    }

    /**
     * Inserta en la tabla grupo, una categoria unica del ficheroGrupo
     *
     * @param grupo string
     */
    public void insertarGrupo(String grupo) {

        PreparedStatement ps;
        try {
            System.out.println("paso por aqui");
            String insert = "INSERT INTO grupos (nombre_grupo)VALUES(?);";
            ps = con.prepareStatement(insert);
            ps.setString(1, grupo);
            ps.execute();
            System.out.println("grupo insertado");


        } catch (SQLException e) {
            System.out.println(e);

        }


    }

    /**
     *Inserta en la tabla iconos, un icono unico del fichero ficheroIconos.txt
     *@param iconos lista de iconos
     */
    public void insertarIcono(String[] iconos) {

        PreparedStatement ps;
        try {
            String insert="INSERT INTO iconos (nombre,unicodigo,grupo,categoria)VALUES(?,?,?,?);";
            ps=con.prepareStatement(insert);
            ps.setString(1,iconos[0]);
            ps.setString(2,iconos[1]);
            ps.setString(3,iconos[2]);
            ps.setString(4,iconos[3]);
            ps.execute();
            System.out.println("icono insertado");



        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    /**
     * Metodo que trae todas las categorias de la tabla categorias
     * @return una lista de categorias
     */
    public ObservableList<String> checkCat(){
        ObservableList<String> itemsCat = FXCollections.observableArrayList();
        String busquedaCategoria = "select nombre_cat from categorias;";
        ResultSet rs;
        Statement stmt;
        try{

            stmt=con.createStatement();
            rs=stmt.executeQuery(busquedaCategoria);
            while(rs.next()){

                itemsCat.add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemsCat;
    }

    /**
     * Metodo que trae todos los grupos de la tabla grupos
     * @return lista de grupos
     */
    public ObservableList<String> checkGrupo(){
        ObservableList<String> itemsCat = FXCollections.observableArrayList();
        String busquedaCategoria = "select nombre_grupo from grupos;";
        ResultSet rs;
        Statement stmt;
        try{

            stmt=con.createStatement();
            rs=stmt.executeQuery(busquedaCategoria);
            while(rs.next()){

                itemsCat.add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemsCat;
    }

    /**
     * Metodo que busca un grupo en especifico de la tabla y la devuelve
     * @param nombreGrupo String
     * @return lista de iconos
     */
    public List <Icono> busquedaGrupo(String nombreGrupo){
        List<Icono>lista = new ArrayList<>();
        String busquedaGrupo = "select * from iconos where grupo = '"+nombreGrupo+"';";
        ResultSet rs;
        Statement stmt;
        try{

            stmt=con.createStatement();
            rs=stmt.executeQuery(busquedaGrupo);
            while(rs.next()){
                Icono icono=new Icono(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                lista.add(icono);
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;

    }

    /**
     * Metodo que busca una categoria en especifico de la tabla y la devuelve
     * @param nombreCategoria String
     * @return lista de iconos
     */
    public List <Icono> busquedaCategoria(String nombreCategoria){
        List<Icono>lista = new ArrayList<>();
        String busquedaCategoria = "select * from iconos where categoria = '"+nombreCategoria+"';";
        ResultSet rs;
        Statement stmt;
        try{

            stmt=con.createStatement();
            rs=stmt.executeQuery(busquedaCategoria);
            while(rs.next()){
                Icono icono=new Icono(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                lista.add(icono);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;

    }

    /**
     * Metodo que recoge el total de tuplas de la tabla iconos y calcula un número aleatorio entre el rango 0
     * y total de las tuplas.
     * @return int random
     */
    public int numeroAleatorio(){
        ResultSet rs;
        Statement stmt;
        int numero=0;
        int ran=0;
        Random random=new Random();
        String busqueda="Select count(*) from iconos;";
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(busqueda);
            while(rs.next()){
                numero=rs.getInt(1);

            }
                ran= random.nextInt(numero);

        }catch (SQLException e){
            System.out.println("fallo"+e);

        }

        return ran;
    }

    /**
     * Metodo que usa un número aleatorio y con el busca el id en la tabla iconos correspondiente
     * @return String unicode
     */
    public String iconoAleatorio() {
        ResultSet rs;

        Statement stmt;
        String codigo = "";
        do {
            int numero = numeroAleatorio();
            String busqueda = "Select unicodigo from iconos where id=" + numero + ";";
            System.out.println(busqueda);

            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(busqueda);
                while (rs.next()) {
                    codigo = rs.getString(1);
                    System.out.println("codigo:" + codigo);
                }

        } catch(SQLException e){
            System.out.println("fallo" + e);

        }
      } while (codigo.equals("")) ;
      return codigo;
    }
}