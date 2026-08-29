package org.example.modelo;

import org.example.exceptions.AsientoNoDisponibleException;
import org.example.exceptions.AsientoYaExistenteException;

import java.util.ArrayList;
import java.util.List;

public class Sala implements Identificable{
    private int idSala;
    private int cantidadAsientos;
    private List<Asiento> asientos;

    public Sala(int idSala, int cantidadAsientos) {
        this.idSala = idSala;
        this.cantidadAsientos = cantidadAsientos;
        this.asientos = new ArrayList<>();
    }

    public int getIdSala() {
        return idSala;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void agregarAsiento(Asiento asientoNuevo) throws AsientoNoDisponibleException,  AsientoYaExistenteException{
        if (asientos.size() >= cantidadAsientos) {
            throw new AsientoNoDisponibleException("Ya no hay espacios disponibles");
        }
        for (Asiento asiento : asientos) {
            if (asiento.getIdAsiento() == asientoNuevo.getIdAsiento()) {
                throw new AsientoYaExistenteException("El asiento ya se encuentra en la sala");
            }
        }
        asientos.add(asientoNuevo);
    }

    public void mostrarAsientos() {
        for (Asiento asiento : asientos) {
            System.out.println("Asiento: " + asiento.getNumeroAsiento());
        }
    }

    @Override
    public int getId() {
        return getIdSala();
    }
}
