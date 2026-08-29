package org.example.modelo;

public class UsuarioVip extends Usuario{
    private double porcentajeDescuento;

    public UsuarioVip(int idUsuario, String nombre, int edad, double porcentajeDescuento) {
        super(idUsuario, nombre, edad);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public double aplicarDescuento(double precio) {
        return precio - (precio * porcentajeDescuento);
    }
}
