package org.example.modelo;

public abstract class Usuario implements Identificable{
    private int idUsuario;
    private String nombre;
    private int edad;

    protected Usuario(int idUsuario, String nombre, int edad){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public abstract double aplicarDescuento(double precio);

    @Override
    public int getId() {
        return getIdUsuario();
    }
}
