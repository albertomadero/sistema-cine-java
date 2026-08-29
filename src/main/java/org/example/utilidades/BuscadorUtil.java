package org.example.utilidades;

import org.example.modelo.Identificable;

import java.util.List;

public class BuscadorUtil {
    public static <T extends Identificable> T buscarPorId(int id, List<T> lista) {
        for (T elemento : lista) {
            if (elemento.getId() == id) {
                return elemento;
            }
        }
        return null;
    }
}
