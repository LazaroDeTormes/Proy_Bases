package com.example.proyectoiconosfx.controllers;

import com.example.proyectoiconosfx.models.Icon;
import com.example.proyectoiconosfx.models.Icono;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Funcion_Guardado implements Serializable {

    /**
     * En este método creamos un fichero JSON en el que pasamos los iconos seleccionados
     *
     *
     * @param iconosJ lista de emoticonos
     */
    public static void json(Icono[] iconosJ){

        ObjectMapper mapa = new ObjectMapper();

        try {
            File json = new File ("res/ficheros/ficheroJson.json");


            mapa.writeValue(json, iconosJ);


        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    /**
     * En este método creamos un fichero Bin en el que pasamos los iconos seleccionados
     *
     *
     * @param iconosB lista de emoticonos
     */
    public static void bin(Icono[] iconosB){


        try(ObjectOutputStream flujo = new ObjectOutputStream(new FileOutputStream("res/ficheros/ficheroBinario.bin"))){

            flujo.writeObject(iconosB);


        }catch (Exception e){
            System.out.println("Error: "+ e);
        }
    }




    /**
     * En este método creamos un fichero JSON en el que pasamos los iconos seleccionados
     *
     *
     * @param iconosX lista de emoticonos
     */
    public static void xml(Icono[] iconosX){
        XmlMapper mapaX = new XmlMapper();
        try {
            File xml = new File ("res/ficheros/ficheroXml.xml");
            mapaX.writeValue(xml, iconosX);

        } catch (Exception exc) {
            System.out.println("Error: "+exc);
        }
    }



    /**
     * En este método creamos un fichero JSON en el que pasamos los iconos seleccionados
     *
     *
     * @param iconosT lista de emoticonos
     */
    public static void txt(Icono[] iconosT){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("res/ficheros/ficheroTexto.txt"))){

            for (int i = 0; i < iconosT.length; i++){
               Icono icono=iconosT[i];
               bw.write(icono.getNombre()+","+icono.getUnicode()+","+icono.getGrupo()+","+icono.getCategoria()+"\n");

            }

        }catch (Exception ex){
            System.out.println("Error: "+ ex);
        }


    }

    /**
     * Metodo que guarda en un fichero.txt con formato csv la lista de iconos
     *
     * @param iconosT lista de iconos
     */

    public static void guardarCsvAll(Icon[] iconosT){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("res/ficheros/sql/ficheroIconos.txt"))){

            for (int i = 0; i < iconosT.length; i++){
                Icon icono=iconosT[i];
                String nombre[]=icono.name.split("≊",2);

                Iterator<String>it=icono.unicode.iterator();
                String lineaUnicode="";
                while(it.hasNext()){
                    lineaUnicode+=it.next()+" ";
                }

                bw.write(nombre[0]+","+lineaUnicode+","+icono.group+","+icono.category+"\n");

            }

        }catch (Exception ex){
            System.out.println("Error: "+ ex);
        }


    }

    /**
     * Metodo que guarda en un fichero.txt con formato csv la lista de categorias
     *
     * @param iconosT lista de iconos
     */
    public static void guardarCsvCategoria(Icon[] iconosT){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("res/ficheros/sql/ficheroCat.txt"))){

            HashSet<String>cat=new HashSet<>();

            for (int i = 0; i < iconosT.length; i++){
                Icon icono=iconosT[i];
                cat.add(icono.getCategory());
                }

            System.out.println("prueba cat");
            Iterator<String>it=cat.iterator();
            while(it.hasNext()){
                bw.write(it.next().toString()+"\n");

            }



        }catch (Exception ex){
            System.out.println("Error: "+ ex);
        }
    }

    /**
     * Metodo que guarda en un fichero.txt con formato csv la lista de grupos
     *
     * @param iconosT lista de iconos
     */
    public static void guardarCsvGrupo(Icon[] iconosT){


        try(BufferedWriter bw = new BufferedWriter(new FileWriter("res/ficheros/sql/ficheroGrupo.txt"))){

            HashSet<String>grupo=new HashSet<>();

            for (int i = 0; i < iconosT.length; i++){
                Icon icono=iconosT[i];
                grupo.add(icono.getGroup());
            }

            Iterator<String>it=grupo.iterator();
            while(it.hasNext()){
                bw.write(it.next().toString()+"\n");

            }


        }catch (Exception ex){
            System.out.println("Error: "+ ex);
        }


    }
}
