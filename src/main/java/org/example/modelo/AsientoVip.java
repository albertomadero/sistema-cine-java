package org.example.modelo;

public class AsientoVip extends Asiento{
    private double porcentajeRecargo;

    public AsientoVip(int idAsiento, String numeroAsiento, int idSala , double porcentajeRecargo) {
        super(idAsiento, numeroAsiento, idSala);
        this.porcentajeRecargo = porcentajeRecargo;
    }

    public double getPorcentajeRecargo() {
        return porcentajeRecargo;
    }

    @Override
    public double calcularPrecio(double precioBase) {
        return precioBase + (precioBase * porcentajeRecargo);
    }
}
