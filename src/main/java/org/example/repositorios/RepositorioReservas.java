package org.example.repositorios;

import org.example.modelo.Asiento;
import org.example.modelo.Funcion;
import org.example.modelo.Reserva;
import org.example.modelo.Usuario;
import org.example.utilidades.BuscadorUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioReservas {
    public void guardarReserva(List<Reserva> reservas) {
        try (FileWriter writer = new FileWriter("reservas.csv")){
            for (Reserva r : reservas) {
                writer.write(r.getIdReserva() + "," + r.getUsuario().getIdUsuario() + "," + r.getAsiento().getIdAsiento() + ","
                + r.getFuncion().getIdFuncion() + "," + r.getPrecio() + "," + r.getFechaReserva() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar reservas: " + e.getMessage());
        }
    }

    public List<Reserva> leerReservas(List<Usuario> usuarios, List<Asiento> asientos, List<Funcion> funciones) {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("reservas.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idReserva = Integer.parseInt(partes[0]);
                int idUsuario = Integer.parseInt(partes[1]);
                int idAsiento = Integer.parseInt(partes[2]);
                int idFuncion = Integer.parseInt(partes[3]);
                double precio = Double.parseDouble(partes[4]);
                LocalDateTime fechaReserva = LocalDateTime.parse(partes[5]);

                Usuario usuarioBuscado = BuscadorUtil.buscarPorId(idUsuario, usuarios);
                Asiento asientoBuscado = BuscadorUtil.buscarPorId(idAsiento, asientos);
                Funcion funcionBuscada = BuscadorUtil.buscarPorId(idFuncion, funciones);

                Reserva reserva = new Reserva(usuarioBuscado, asientoBuscado, funcionBuscada, fechaReserva, idReserva, precio);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return reservas;
    }
}
