package org.example.controladores;

import org.example.gestores.GestorFunciones;
import org.example.modelo.Funcion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FuncionController {

    private final GestorFunciones gestorFunciones;

    public FuncionController(GestorFunciones gestorFunciones) {
        this.gestorFunciones = gestorFunciones;
    }

    @GetMapping("/funciones")
    public List<Funcion> verFunciones() {
        return gestorFunciones.getListaFunciones();
    }
}
