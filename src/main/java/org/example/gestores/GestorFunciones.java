package org.example.gestores;

import org.example.modelo.Funcion;
import org.example.modelo.Pelicula;
import org.example.modelo.Sala;
import org.example.exceptions.FuncionEnConflictoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorFunciones {
    private List<Funcion> listaFunciones;

    public GestorFunciones() {
        this.listaFunciones = new ArrayList<>();
    }

    public List<Funcion> getListaFunciones() {
        return listaFunciones;
    }

    public Funcion crearFuncion(Sala sala, Pelicula pelicula, LocalDateTime horario, double precio, int idFuncion) throws FuncionEnConflictoException {
        Funcion funcionTemporal = new Funcion(sala, pelicula, horario, precio, idFuncion);
        for (Funcion f : listaFunciones) {
            if (!(funcionTemporal.getHorarioFin().isBefore(f.getHorario()) || funcionTemporal.getHorarioFin().isEqual(f.getHorario()) ||
                    f.getHorarioFin().isBefore(funcionTemporal.getHorario()) || f.getHorarioFin().isEqual(funcionTemporal.getHorario()))
                    && funcionTemporal.getSala().getIdSala() == f.getSala().getIdSala()) {
                throw new FuncionEnConflictoException("La funcion ya se encuentra ocupada en ese horario");
            }
        }
        listaFunciones.add(funcionTemporal);
        return funcionTemporal;
    }

    public void cargarFuncionExistente(Funcion funcion) {
        listaFunciones.add(funcion);
    }
}
