package org.example.modelo;

import java.time.LocalDateTime;

public class Funcion implements Identificable{
    private Sala sala;
    private Pelicula pelicula;
    private LocalDateTime horario;
    private double precio;
    private int idFuncion;

    public Funcion(Sala sala, Pelicula pelicula, LocalDateTime horario, double precio, int idFuncion) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.horario = horario;
        this.precio = precio;
        this.idFuncion = idFuncion;
    }

    public Sala getSala() {
        return sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public LocalDateTime getHorarioFin() {
        return horario.plusMinutes(pelicula.getDuracionMinutos());
    }

    @Override
    public int getId() {
        return getIdFuncion();
    }
}
