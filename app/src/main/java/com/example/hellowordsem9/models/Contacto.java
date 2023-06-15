package com.example.hellowordsem9.models;

public class Contacto {
    private String nombre;
    private String numero;
    private String foto;


    public Contacto(String nombre, String numero, String foto) {
        this.nombre = nombre;
        this.numero = numero;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
