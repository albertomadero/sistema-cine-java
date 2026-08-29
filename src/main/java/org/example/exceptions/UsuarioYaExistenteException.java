package org.example.exceptions;

public class UsuarioYaExistenteException extends Exception{
    public UsuarioYaExistenteException(String mensaje) {
        super(mensaje);
    }
}
