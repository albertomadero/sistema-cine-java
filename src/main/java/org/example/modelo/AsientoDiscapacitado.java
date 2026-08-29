package org.example.modelo;

public class AsientoDiscapacitado extends Asiento{

    public AsientoDiscapacitado(int idAsiento, String numeroAsiento, int idSala) {
        super(idAsiento, numeroAsiento, idSala);
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase;
    }
}
