package org.example.exceptions;

public class PeliculaYaExistenteException extends Exception{
    public PeliculaYaExistenteException(String mensaje) {
        super(mensaje);
    }
}
