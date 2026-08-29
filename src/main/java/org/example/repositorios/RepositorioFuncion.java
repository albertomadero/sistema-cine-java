package org.example.repositorios;

import org.example.modelo.Funcion;
import org.example.modelo.Pelicula;
import org.example.modelo.Sala;
import org.example.utilidades.BuscadorUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFuncion {
    public void guardarFuncion(List<Funcion> funciones) {
        try (FileWriter writer = new FileWriter("funciones.csv")){
            for (Funcion funcion : funciones) {
                writer.write(funcion.getSala().getIdSala() + "," + funcion.getPelicula().getIdPelicula() + "," +
                        funcion.getHorario() + "," + funcion.getPrecio() + "," + funcion.getIdFuncion() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public List<Funcion> leerFunciones(List<Sala> salas, List<Pelicula> peliculas) {
        List<Funcion> funciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("funciones.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idSala = Integer.parseInt(partes[0]);
                int idPelicula = Integer.parseInt(partes[1]);
                LocalDateTime horario = LocalDateTime.parse(partes[2]);
                double precio = Double.parseDouble(partes[3]);
                int idFuncion = Integer.parseInt(partes[4]);

                Sala salaBuscada = BuscadorUtil.buscarPorId(idSala, salas);
                Pelicula peliculaBuscada = BuscadorUtil.buscarPorId(idPelicula, peliculas);

                Funcion funcion = new Funcion(salaBuscada, peliculaBuscada, horario, precio, idFuncion);
                funciones.add(funcion);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return funciones;
    }
}
