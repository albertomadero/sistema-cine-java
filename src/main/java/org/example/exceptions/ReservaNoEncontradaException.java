package org.example.exceptions;

import org.example.controladores.ReservaController;

public class ReservaNoEncontradaException extends Exception{
    public ReservaNoEncontradaException(String mensaje) {super(mensaje);}
}
