package org.example.gestores;

import org.example.exceptions.AsientoYaExistenteException;
import org.example.modelo.Asiento;

import java.util.ArrayList;
import java.util.List;

public class GestorAsientos {
    private List<Asiento> asientos;

    public GestorAsientos() {
        this.asientos = new ArrayList<>();
    }

    public Asiento agregarAsiento(Asiento asientoNuevo) throws AsientoYaExistenteException {
        for (Asiento asiento : asientos){
            if (asiento.getIdAsiento() == asientoNuevo.getIdAsiento()) {
                throw new AsientoYaExistenteException("Ya existe el asiento");
            }
        }
        asientos.add(asientoNuevo);
        return asientoNuevo;
    }

    public void cargarAsientoExistente(Asiento asiento) {
        asientos.add(asiento);
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }
}
