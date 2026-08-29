package org.example.gestores;

import org.example.exceptions.PeliculaYaExistenteException;
import org.example.modelo.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class GestorPeliculas {
    private List<Pelicula> peliculas;

    public GestorPeliculas() {
        this.peliculas = new ArrayList<>();
    }

    public Pelicula agregarPelicula(Pelicula peliculaNueva) throws PeliculaYaExistenteException {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getIdPelicula() == peliculaNueva.getIdPelicula()) {
                throw new PeliculaYaExistenteException("Ya existe la pelicula");
            }
        }
        peliculas.add(peliculaNueva);
        return peliculaNueva;
    }

    public void cargarPeliculaExistente(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }
}
