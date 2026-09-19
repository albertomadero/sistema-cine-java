package org.example.configuracion;

import org.example.gestores.GestorAsientos;
import org.example.gestores.GestorFunciones;
import org.example.gestores.GestorReservas;
import org.example.gestores.GestorUsuarios;
import org.example.modelo.*;
import org.example.repositorios.*;
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

    @Bean
    public GestorUsuarios gestorUsuarios() {
        // Crear repositorio
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        // Crear gestor
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        // Leer y crear la lista
        List<Usuario> usuarios = repositorioUsuarios.leerUsuarios();

        // Cargar usuarios al gestorUsuarios
        for (Usuario usuario : usuarios) {
            gestorUsuarios.cargarUsuarioExistente(usuario);
        }

        return gestorUsuarios;

    }

    @Bean
    public GestorAsientos gestorAsientos() {
        // Crear repo
        RepositorioAsientos repositorioAsientos = new RepositorioAsientos();

        // Leer y crear lista
        List<Asiento> asientos = repositorioAsientos.leerAsientos();

        // Crear gestor
       GestorAsientos gestorAsientos = new GestorAsientos();

        // Cargar asientos al gestorAsientos
        for (Asiento asiento : asientos) {
            gestorAsientos.cargarAsientoExistente(asiento);
        }

        return gestorAsientos;
    }

    @Bean
    public GestorReservas gestorReservas(GestorUsuarios gestorUsuarios, GestorAsientos gestorAsientos, GestorFunciones gestorFunciones) {
        // Crear repo
        RepositorioReservas repositorioReservas = new RepositorioReservas();

        // Leer y crear listas
        List<Usuario> usuarios = gestorUsuarios.getUsuarios();
        List<Asiento> asientos = gestorAsientos.getAsientos();
        List<Funcion> funciones = gestorFunciones.getListaFunciones();
        List<Reserva> reservas = repositorioReservas.leerReservas(usuarios, asientos, funciones);

        // Crear gestor
        GestorReservas gestorReservas = new GestorReservas();

        // Cargar reservas al gestor
        for (Reserva reserva : reservas) {
            gestorReservas.cargarReservaExistente(reserva);
        }

        return gestorReservas;
    }

    @Bean
    public RepositorioReservas repositorioReservas() {
        // Crear repo
        RepositorioReservas repositorioReservas = new RepositorioReservas();

        return repositorioReservas;
    }
}
