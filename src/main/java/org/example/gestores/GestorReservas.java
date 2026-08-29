package org.example.gestores;

import org.example.modelo.Asiento;
import org.example.modelo.Funcion;
import org.example.modelo.Reserva;
import org.example.modelo.Usuario;
import org.example.exceptions.AsientoYaReservadoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private List<Reserva> reservas;

    public GestorReservas() {
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva crearReserva(Usuario usuario, Asiento asiento, Funcion funcion, LocalDateTime fechaReserva, int idReserva) throws AsientoYaReservadoException {
        for (Reserva r : reservas) {
            if (r.getAsiento().getIdAsiento() == asiento.getIdAsiento() && r.getFuncion().getIdFuncion() == funcion.getIdFuncion()) {
                throw new AsientoYaReservadoException("Ya existe un asiento reservado para la funcion");
            }
        }
        Reserva reserva2 = new Reserva(usuario, asiento, funcion, fechaReserva, idReserva);
        reservas.add(reserva2);
        return reserva2;
    }

    public void cargarReservaExistente(Reserva reservaExistente) {
        reservas.add(reservaExistente);
    }

    public List<Asiento> asientosDisponibles(Funcion funcion) {
        List<Asiento> disponibles = new ArrayList<>();
        List<Asiento> todosLosAsientos = funcion.getSala().getAsientos();

        for (Asiento asiento : todosLosAsientos) {
            boolean estaOcupado = false;

            for (Reserva reserva : reservas) {
                if (reserva.getAsiento().getIdAsiento() == asiento.getIdAsiento() && reserva.getFuncion().getIdFuncion() == funcion.getIdFuncion()) {
                    estaOcupado = true;
                }
            }
            if (!estaOcupado) {
                disponibles.add(asiento);
            }
        }
        return disponibles;
    }

    public int generarNuevoId() {
        int maximoEncontrado = 0;
        if (!reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                if (reserva.getIdReserva() > maximoEncontrado) {
                    maximoEncontrado = reserva.getIdReserva();
                }
            }
        }
        return maximoEncontrado + 1;
    }
}
