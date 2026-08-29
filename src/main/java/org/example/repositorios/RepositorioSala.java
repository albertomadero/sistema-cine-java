package org.example.repositorios;

import org.example.exceptions.AsientoYaExistenteException;
import org.example.modelo.Asiento;
import org.example.modelo.Sala;
import org.example.exceptions.AsientoNoDisponibleException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSala {
    public void guardarSala(List<Sala> salas) {
        try (FileWriter writer = new FileWriter("salas.csv")){
            for (Sala sala : salas) {
                writer.write(sala.getIdSala() + "," + sala.getCantidadAsientos() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public List<Sala> leerSalas(RepositorioAsientos repositorioAsientos) {
        List<Sala> salas = new ArrayList<>();
        List<Asiento> todosLosAsientos = repositorioAsientos.leerAsientos();

        try (BufferedReader reader = new BufferedReader(new FileReader("salas.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idSala = Integer.parseInt(partes[0]);
                int cantidadAsientos = Integer.parseInt(partes[1]);

                Sala sala = new Sala(idSala, cantidadAsientos);

                for (Asiento asiento : todosLosAsientos) {
                    if (asiento.getIdSala() == idSala) {
                        try {
                            sala.agregarAsiento(asiento);
                        } catch (AsientoNoDisponibleException e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (AsientoYaExistenteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                }

                salas.add(sala);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo salas.csv: " + e.getMessage());
        }

        return salas;
    }
}
