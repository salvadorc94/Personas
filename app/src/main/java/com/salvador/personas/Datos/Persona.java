package com.salvador.personas.Datos;

/**
 * Created by UCA on 16/05/2018.
 */

public class Persona {
    private String dui;
    private String nombre;

    public Persona(){}

    public Persona(String dui, String nombre) {
        this.dui = dui;
        this.nombre = nombre;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
