package org.example.controladores;

import org.example.dto.ReservaRequest;
import org.example.exceptions.AsientoYaReservadoException;
import org.example.gestores.GestorAsientos;
import org.example.gestores.GestorFunciones;
import org.example.gestores.GestorReservas;
import org.example.gestores.GestorUsuarios;
import org.example.modelo.Asiento;
import org.example.modelo.Funcion;
import org.example.modelo.Reserva;
import org.example.modelo.Usuario;
import org.example.utilidades.BuscadorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
public class ReservaController {

    private final GestorUsuarios gestorUsuarios;
    private final GestorAsientos gestorAsientos;
    private final GestorFunciones gestorFunciones;
    private final GestorReservas gestorReservas;

    public ReservaController(GestorUsuarios gestorUsuarios, GestorAsientos gestorAsientos, GestorFunciones gestorFunciones, GestorReservas gestorReservas) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorAsientos = gestorAsientos;
        this.gestorFunciones = gestorFunciones;
        this.gestorReservas = gestorReservas;
    }

    @PostMapping("/reservas")
    public Reserva crearReserva(@RequestBody ReservaRequest request) {
        Usuario usuarioBuscado = BuscadorUtil.buscarPorId(request.getIdUsuario(), gestorUsuarios.getUsuarios());
        Asiento asientoBuscado = BuscadorUtil.buscarPorId(request.getIdAsiento(), gestorAsientos.getAsientos());
        Funcion funcionBuscada = BuscadorUtil.buscarPorId(request.getIdFuncion(), gestorFunciones.getListaFunciones());

        // Crear reserva
        int idReserva = gestorReservas.generarNuevoId();
        try {
            return gestorReservas.crearReserva(usuarioBuscado, asientoBuscado, funcionBuscada, LocalDateTime.now(), idReserva);
        } catch (AsientoYaReservadoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
