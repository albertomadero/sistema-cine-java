package org.example;

import org.example.core.SistemaCine;
import org.example.exceptions.*;
import org.example.gestores.*;
import org.example.modelo.*;
import org.example.repositorios.*;
import org.example.utilidades.BuscadorUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear repositorios
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        RepositorioAsientos repositorioAsientos = new RepositorioAsientos();
        RepositorioSala repositorioSala = new RepositorioSala();
        RepositorioPelicula repositorioPelicula = new RepositorioPelicula();
        RepositorioFuncion repositorioFuncion = new RepositorioFuncion();
        RepositorioReservas repositorioReservas = new RepositorioReservas();

        // Crear gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorReservas gestorReservas = new GestorReservas();
        GestorFunciones gestorFunciones = new GestorFunciones();
        GestorAsientos gestorAsientos = new GestorAsientos();
        GestorPeliculas gestorPeliculas = new GestorPeliculas();
        GestorSalas gestorSalas = new GestorSalas();

        // lectura y guardado de lista de repos
        List<Usuario> usuariosExistentes = repositorioUsuarios.leerUsuarios();
        List<Asiento> asientosExistentes = repositorioAsientos.leerAsientos();
        List<Sala> salasExistente = repositorioSala.leerSalas(repositorioAsientos);
        List<Pelicula> pelicasExistentes = repositorioPelicula.leerPeliculas();
        List<Funcion> funcionesExistentes = repositorioFuncion.leerFunciones(salasExistente, pelicasExistentes);
        List<Reserva> reservasExistentes = repositorioReservas.leerReservas(usuariosExistentes, asientosExistentes, funcionesExistentes);

        // Agregar datos a su respectivo gestor
        // Usuarios
        for (Usuario usuario : usuariosExistentes) {
            gestorUsuarios.cargarUsuarioExistente(usuario);
        }

        // Asientos
        for (Asiento asiento : asientosExistentes) {

            gestorAsientos.cargarAsientoExistente(asiento);
        }

        // Salas
        for (Sala sala : salasExistente) {
            gestorSalas.cargarSalaExistente(sala);
        }

        // Peliculas
        for (Pelicula pelicula : pelicasExistentes) {
            gestorPeliculas.cargarPeliculaExistente(pelicula);
        }

        // Funciones
        for (Funcion funcion : funcionesExistentes) {
            gestorFunciones.cargarFuncionExistente(funcion);
        }

        // Reservas
        for (Reserva reserva : reservasExistentes) {
            gestorReservas.cargarReservaExistente(reserva);
        }

        // Crear usuarios nuevos
        UsuarioNormal usuarioNormal = new UsuarioNormal(1, "Alberto", 31);
        UsuarioVip usuarioVip = new UsuarioVip(2, "Jesus", 28, 0.30);

        // Agregar usuarios a la lista desde el gestor
        try {
            gestorUsuarios.agregarUsuario(usuarioNormal);
        } catch (UsuarioYaExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            gestorUsuarios.agregarUsuario(usuarioVip);
        } catch (UsuarioYaExistenteException e) {
            System.out.println(e.getMessage());
        }

        // Generar archivos csv
        repositorioUsuarios.guardarUsuarios(gestorUsuarios.getUsuarios());
        repositorioAsientos.guardarAsientos(gestorAsientos.getAsientos());
        repositorioSala.guardarSala(gestorSalas.getSalas());
        repositorioPelicula.guardarPelicula(gestorPeliculas.getPeliculas());
        repositorioFuncion.guardarFuncion(gestorFunciones.getListaFunciones());
        repositorioReservas.guardarReserva(gestorReservas.getReservas());

        // Mostrar información de cada lista
        // Usuarios
        for (Usuario usuario : repositorioUsuarios.leerUsuarios()) {
            System.out.println("Nombre: " + usuario.getNombre() + ", edad: " + usuario.getEdad());
        }

        // Asientos
        for (Asiento asiento : repositorioAsientos.leerAsientos()) {
            System.out.println("Asiento: " + asiento.getIdAsiento() + ", " + asiento.getNumeroAsiento());
        }

        // Salas
        for (Sala sala : repositorioSala.leerSalas(repositorioAsientos)) {
            System.out.println("Sala: " + sala.getIdSala());
        }

        //Peliculas
        for (Pelicula pelicula : repositorioPelicula.leerPeliculas()) {
            System.out.println("Pelicula: " + pelicula.getNombre() + ", genero: " + pelicula.getGenero());
        }

        // Funciones
        for (Funcion funcion : repositorioFuncion.leerFunciones(repositorioSala.leerSalas(repositorioAsientos), repositorioPelicula.leerPeliculas())) {
            System.out.println(funcion.getPelicula().getNombre() + " - $" + funcion.getPrecio());
        }

        // Reservas
        for (Reserva reserva : repositorioReservas.leerReservas(repositorioUsuarios.leerUsuarios(),
                repositorioAsientos.leerAsientos(),
                repositorioFuncion.leerFunciones(repositorioSala.leerSalas(repositorioAsientos),
                        repositorioPelicula.leerPeliculas()))) {
            System.out.println("Reserva: " + reserva.getIdReserva() + ", fecha: " + reserva.getFechaReserva() + ", asiento: " + reserva.getAsiento().getNumeroAsiento()
                   +  ", funcion: " + reserva.getFuncion().getIdFuncion());
        }

        SistemaCine sistemaCine = new SistemaCine(repositorioFuncion, repositorioUsuarios, repositorioReservas, gestorFunciones, gestorUsuarios, gestorReservas);
        menu(sistemaCine);
    }

    public static void menu(SistemaCine sistemaCine) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int opcion;

        do {
            System.out.println("Bienvenido a Cinemas");
            System.out.println("Selecciona una opción: \n1. Ver funciones disponibles\n2. Reservar asiento\n3. Salir");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opción valida");
                scanner.nextLine();
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    System.out.println("Funciones: ");
                    List<Funcion> funciones = sistemaCine.getGestorFunciones().getListaFunciones();
                    for (Funcion funcion : funciones) {
                        System.out.println(funcion.getIdFuncion() +". Pelicula: " + funcion.getPelicula().getNombre() + " | Sala: " + funcion.getSala().getIdSala()
                        + " | precio: " + funcion.getPrecio() + " | horario: " + funcion.getHorario());
                    }
                    break;
                case 2:
                    System.out.println("Funciones");
                    List<Funcion> funcionesReserva = sistemaCine.getGestorFunciones().getListaFunciones();
                    for (Funcion funcion : funcionesReserva) {
                        System.out.println(funcion.getIdFuncion() +". Pelicula: " + funcion.getPelicula().getNombre() + " | Sala: " + funcion.getSala().getIdSala()
                                + " | precio: " + funcion.getPrecio() + " | horario: " + funcion.getHorario());
                    }
                    int opcionFuncion;
                    System.out.println("Selecciona la funcion que deseas");
                    try {
                        opcionFuncion = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Ingresa una opción valida");
                        scanner.nextLine();
                        break;
                    }
                    Funcion funcionBuscada = BuscadorUtil.buscarPorId(opcionFuncion, funcionesReserva);
                    if (funcionBuscada != null) {
                        List<Asiento> asientosDisponibles = sistemaCine.getGestorReservas().asientosDisponibles(funcionBuscada);
                        if (asientosDisponibles.isEmpty()) {
                            System.out.println("No se tienen asientos disponibles en esta funcion");
                            break;
                        }
                        System.out.println("Aientos disponibles: ");
                        for (Asiento asiento : asientosDisponibles) {
                            System.out.println(asiento.getIdAsiento() + " | " + asiento.getNumeroAsiento());
                        }
                        int opcionAsiento;
                        System.out.println("Selecciona un asiento mediante si id: ");
                        Asiento asientoParaReservar = null;
                        try {
                            opcionAsiento = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Selecciona una opción valida");
                            scanner.nextLine();
                            break;
                        }
                        asientoParaReservar = BuscadorUtil.buscarPorId(opcionAsiento, asientosDisponibles);
                        if (asientoParaReservar != null) {
                            System.out.println("Se encontó el asiento correctamente");
                        } else {
                            System.out.println("No se encontro el asiento seleccionado");
                            break;
                        }
                        int opcionUsuarioExistente;
                        Usuario usuarioParaReserva = null;
                        System.out.println("¿Ya tienes una cuenta?\n1. Sí, usar mi usuario existente\n2. No, crear cuenta nueva");
                        try {
                            opcionUsuarioExistente = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Selecciona una opción valida");
                            scanner.nextLine();
                            break;
                        }
                        if (opcionUsuarioExistente == 1) {
                            int idUsuario;
                            System.out.println("Ingresa el id de tu usuario");
                            try {
                                idUsuario = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Selecciona una opción valida");
                                scanner.nextLine();
                                break;
                            }
                            List<Usuario> usuarios = sistemaCine.getGestorUsuarios().getUsuarios();
                            Usuario usuarioExistente = BuscadorUtil.buscarPorId(idUsuario, usuarios);
                            if (usuarioExistente != null) {
                                System.out.println("Se encontró el usuario correctamente");
                                usuarioParaReserva = usuarioExistente;
                            } else {
                                System.out.println("No se encontró al usuario");
                                break;
                            }
                        } else if (opcionUsuarioExistente == 2) {
                            String nombreUsuario;
                            int edadUsuario;
                            int idUsuario;
                            double porcentajeDescuento;
                            int tipoUsuario;
                            System.out.println("Ingresa tu nombre:");
                            nombreUsuario = scanner.nextLine();
                            System.out.println("Ingresa tu edad:");
                            try {
                                edadUsuario = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Ingresaste un dato incorrecto");
                                scanner.nextLine();
                                break;
                            }
                            System.out.println("Ingresa el tipo de usuario que deseas ser:\n" +
                                    "1. Usuario normal\n2. Usuario Vip");
                            try {
                                tipoUsuario = scanner.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Ingresaste un dato incorrecto");
                                scanner.nextLine();
                                break;
                            }
                            idUsuario = sistemaCine.getGestorUsuarios().generarNuevoId();
                            if (tipoUsuario == 1) {
                                UsuarioNormal usuarioNuevoNormal = new UsuarioNormal(idUsuario, nombreUsuario, edadUsuario);
                                usuarioParaReserva = usuarioNuevoNormal;
                                System.out.println("Se creó el usuario correctamente");
                                try {
                                    sistemaCine.getGestorUsuarios().agregarUsuario(usuarioNuevoNormal);
                                } catch (UsuarioYaExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else if (tipoUsuario == 2){
                                porcentajeDescuento = sistemaCine.getGestorUsuarios().getDescuentoUsuarioVip();
                                UsuarioVip usuarioNuevoVip = new UsuarioVip(idUsuario, nombreUsuario, edadUsuario, porcentajeDescuento);
                                usuarioParaReserva = usuarioNuevoVip;
                                System.out.println("Se creó el usuario correctamente");
                                try {
                                    sistemaCine.getGestorUsuarios().agregarUsuario(usuarioNuevoVip);
                                } catch (UsuarioYaExistenteException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Seleccionaste una opción incorrecta");
                                break;
                            }
                        } else {
                            System.out.println("Selecciona una opción valida");
                            break;
                        }
                        if (usuarioParaReserva != null) {
                            int idReserva;
                            idReserva = sistemaCine.getGestorReservas().generarNuevoId();
                            try {
                                sistemaCine.getGestorReservas().crearReserva(usuarioParaReserva, asientoParaReservar, funcionBuscada, LocalDateTime.now(), idReserva);
                                List<Reserva> listaReservasActualizadas = sistemaCine.getGestorReservas().getReservas();
                                sistemaCine.getRepositorioReservas().guardarReserva(listaReservasActualizadas);
                            } catch (AsientoYaReservadoException e) {
                                System.out.println(e.getMessage());
                            }
                            List<Usuario> listaUsuariosActualizados = sistemaCine.getGestorUsuarios().getUsuarios();
                            sistemaCine.getRepositorioUsuarios().guardarUsuarios(listaUsuariosActualizados);
                        }
                    } else {
                        System.out.println("No se encontro la funcion");
                    }
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (continuar);
    }
}