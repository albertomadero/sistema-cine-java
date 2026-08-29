package org.example.modelo;

public class UsuarioNormal extends Usuario{
    public UsuarioNormal(int idUsuario, String nombre, int edad){
        super(idUsuario, nombre, edad);
    }

    @Override
    public double aplicarDescuento(double precio) {
        return precio;
    }
}
