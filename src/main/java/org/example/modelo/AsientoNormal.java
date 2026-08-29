package org.example.modelo;

public class AsientoNormal extends Asiento{

    public AsientoNormal(int idAsiento, String numeroAsiento, int idSala) {
        super(idAsiento, numeroAsiento, idSala);
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase;
    }
}
