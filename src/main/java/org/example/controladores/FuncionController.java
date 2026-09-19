package org.example.controladores;

import org.example.gestores.GestorFunciones;
import org.example.gestores.GestorReservas;
import org.example.modelo.Asiento;
import org.example.modelo.Funcion;
import org.example.utilidades.BuscadorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FuncionController {

    private final GestorFunciones gestorFunciones;
    private final GestorReservas gestorReservas;

    public FuncionController(GestorFunciones gestorFunciones, GestorReservas gestorReservas) {
        this.gestorFunciones = gestorFunciones;
        this.gestorReservas = gestorReservas;
    }

    @GetMapping("/funciones")
    public List<Funcion> verFunciones() {
        return gestorFunciones.getListaFunciones();
    }

    @GetMapping("/funciones/{id}/asientos")
    public List<Asiento> verAsientosDisponibles(@PathVariable int id) {
        Funcion funcionBuscada = BuscadorUtil.buscarPorId(id, gestorFunciones.getListaFunciones());

        if (funcionBuscada != null) {
            return gestorReservas.asientosDisponibles(funcionBuscada);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
