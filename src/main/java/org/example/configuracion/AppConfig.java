package org.example.configuracion;

import org.example.gestores.GestorFunciones;
import org.example.modelo.Funcion;
import org.example.modelo.Pelicula;
import org.example.modelo.Sala;
import org.example.repositorios.RepositorioAsientos;
import org.example.repositorios.RepositorioFuncion;
import org.example.repositorios.RepositorioPelicula;
import org.example.repositorios.RepositorioSala;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public GestorFunciones gestorFunciones() {
        // Crear repositorios necesarios
        RepositorioAsientos repositorioAsientos = new RepositorioAsientos();
        RepositorioSala repositorioSalas = new RepositorioSala();
        RepositorioPelicula repositorioPeliculas = new RepositorioPelicula();
        RepositorioFuncion repositorioFunciones = new RepositorioFuncion();

        // Crear Gestores necearios
        GestorFunciones gestorFunciones = new GestorFunciones();

        // Leer y crear listas necesarias
        List<Sala> salasExistentes = repositorioSalas.leerSalas(repositorioAsientos);
        List<Pelicula> peliculasExistentes = repositorioPeliculas.leerPeliculas();
        List<Funcion> funcionesExistentes = repositorioFunciones.leerFunciones(salasExistentes, peliculasExistentes);

        // Cargar funciones al gestorFunciones
        for (Funcion funcion : funcionesExistentes) {
            gestorFunciones.cargarFuncionExistente(funcion);
        }

        return gestorFunciones;
    }
}
