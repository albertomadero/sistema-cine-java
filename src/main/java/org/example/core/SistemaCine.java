package org.example.core;

import org.example.gestores.GestorFunciones;
import org.example.gestores.GestorReservas;
import org.example.gestores.GestorUsuarios;
import org.example.repositorios.RepositorioFuncion;
import org.example.repositorios.RepositorioReservas;
import org.example.repositorios.RepositorioUsuarios;

public class SistemaCine {
    private RepositorioFuncion repositorioFuncion;
    private RepositorioUsuarios repositorioUsuarios;
    private RepositorioReservas repositorioReservas;
    private GestorFunciones gestorFunciones;
    private GestorUsuarios gestorUsuarios;
    private GestorReservas gestorReservas;

    public SistemaCine(RepositorioFuncion repositorioFuncion, RepositorioUsuarios repositorioUsuarios, RepositorioReservas repositorioReservas,
                       GestorFunciones gestorFunciones, GestorUsuarios gestorUsuarios, GestorReservas gestorReservas) {
        this.repositorioFuncion = repositorioFuncion;
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioReservas = repositorioReservas;
        this.gestorFunciones = gestorFunciones;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorReservas = gestorReservas;
    }

    public RepositorioUsuarios getRepositorioUsuarios() {
        return repositorioUsuarios;
    }

    public RepositorioFuncion getRepositorioFuncion() {
        return repositorioFuncion;
    }

    public RepositorioReservas getRepositorioReservas() {
        return repositorioReservas;
    }

    public GestorFunciones getGestorFunciones() {
        return gestorFunciones;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    public GestorReservas getGestorReservas() {
        return gestorReservas;
    }
}
