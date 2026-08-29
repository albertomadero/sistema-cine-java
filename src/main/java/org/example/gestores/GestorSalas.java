package org.example.gestores;

import org.example.exceptions.SalaYaExistenteException;
import org.example.modelo.Sala;

import java.util.ArrayList;
import java.util.List;

public class GestorSalas {
    private List<Sala> salas;

    public GestorSalas() {
        this.salas = new ArrayList<>();
    }

    public Sala agregarSala(Sala salaNueva) throws SalaYaExistenteException {
        for (Sala sala : salas) {
            if (sala.getIdSala() == salaNueva.getIdSala()) {
                throw new SalaYaExistenteException("Ya existe la sala");
            }
        }
        salas.add(salaNueva);
        return salaNueva;
    }

    public void cargarSalaExistente(Sala sala) {
        salas.add(sala);
    }

    public List<Sala> getSalas() {
        return salas;
    }
}
