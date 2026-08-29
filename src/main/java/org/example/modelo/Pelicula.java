package org.example.modelo;

public class Pelicula implements Identificable{
    private int idPelicula;
    private String nombre;
    private String genero;
    private String clasificacion;
    private boolean estaActiva;
    private int duracionMinutos;

    public Pelicula(int idPelicula, String nombre, String genero, String clasificacion, boolean estaActiva, int duracionMinutos) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.estaActiva = estaActiva;
        this.duracionMinutos = duracionMinutos;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public void activar() {
        this.estaActiva = true;
    }

    public void desactivar() {
        this.estaActiva = false;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public boolean puedeVerla(int edad) {
        if (clasificacion.equals("A")) {
            return true;
        } else if (clasificacion.equals("B")) {
            return edad >= 12;
        } else if (clasificacion.equals("B15")) {
            return edad >= 15;
        } else if (clasificacion.equals("C")) {
            return edad >= 18;
        } else {
            return false;
        }
    }

    @Override
    public int getId() {
        return getIdPelicula();
    }
}
