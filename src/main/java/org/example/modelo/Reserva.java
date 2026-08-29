package org.example.modelo;

import java.time.LocalDateTime;

public class Reserva implements Identificable{
    private Usuario usuario;
    private Asiento asiento;
    private Funcion funcion;
    private double precio;
    private LocalDateTime fechaReserva;
    private int idReserva;

    public Reserva(Usuario usuario, Asiento asiento, Funcion funcion, LocalDateTime fechaReserva, int idReserva) {
        this.usuario = usuario;
        this.asiento = asiento;
        this.funcion = funcion;
        this.fechaReserva = fechaReserva;
        this.precio = calcularPrecioFinal();
        this.idReserva = idReserva;
    }

    public Reserva(Usuario usuario, Asiento asiento, Funcion funcion, LocalDateTime fechaReserva, int idReserva, double precioYaCalculado) {
        this.usuario = usuario;
        this.asiento = asiento;
        this.funcion = funcion;
        this.fechaReserva = fechaReserva;
        this.idReserva = idReserva;
        this.precio = precioYaCalculado;
    }

    private double calcularPrecioFinal() {
        double precioConRecargo = asiento.calcularPrecio(funcion.getPrecio());
        double precioFinal = usuario.aplicarDescuento(precioConRecargo);
        return precioFinal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdReserva() {return idReserva;}

    @Override
    public int getId() {
        return getIdReserva();
    }
}
