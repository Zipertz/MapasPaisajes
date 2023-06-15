package com.example.hellowordsem9.models;

public class Animes {

    private String nombre;
    private String dscripcion;
    private String foto;

    private boolean favorite;

    public Animes(String nombre, String dscripcion, String foto,boolean favorite ) {
        this.nombre = nombre;
        this.dscripcion = dscripcion;
        this.foto = foto;
        this.favorite = favorite;
    }

    public String getNombre() {
        return nombre;
    }



    public String getDescripcion() {
        return dscripcion;
    }

    public String getFoto() {
        return foto;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", numero='" + dscripcion + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
