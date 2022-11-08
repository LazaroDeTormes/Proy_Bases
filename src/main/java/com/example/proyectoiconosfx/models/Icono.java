package com.example.proyectoiconosfx.models;

public class Icono {

    private String nombre;
    private String unicode;
    private String grupo;
    private String categoria;

    public Icono(String nombre, String unicode, String grupo, String categoria) {
        this.nombre = nombre;
        this.unicode = unicode;
        this.grupo = grupo;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getCategoria() {
        return categoria;
    }


    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    @Override
    public String toString() {
        return "Icono{" +
                "nombre='" + nombre + '\'' +
                ", unicode='" + unicode + '\'' +
                ", grupo='" + grupo + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
