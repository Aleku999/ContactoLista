package com.example.lenovo.contactolista;

public class Contact {
    private String nombre;
    private String telefono;
    private String genero;

    public Contact(String nombre, String telefono, String genero) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getGenero() {
        return genero;
    }
}

