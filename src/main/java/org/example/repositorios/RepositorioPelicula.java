package org.example.repositorios;

import org.example.modelo.Pelicula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPelicula {
    public void guardarPelicula(List<Pelicula> peliculas) {
        try (FileWriter writer = new FileWriter("peliculas.csv")){
            for (Pelicula pelicula : peliculas) {
                writer.write(pelicula.getIdPelicula() + "," + pelicula.getNombre() + "," + pelicula.getGenero() +
                        "," + pelicula.getClasificacion() + "," + pelicula.isEstaActiva() + "," +
                        pelicula.getDuracionMinutos() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public List<Pelicula> leerPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("peliculas.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idPelicula = Integer.parseInt(partes[0]);
                String nombrePelicula = partes[1];
                String genero = partes[2];
                String clasificacion = partes[3];
                boolean estaActiva = Boolean.parseBoolean(partes[4]);
                int duracionMinutos = Integer.parseInt(partes[5]);

                Pelicula pelicula = new Pelicula(idPelicula, nombrePelicula, genero, clasificacion, estaActiva, duracionMinutos);
                peliculas.add(pelicula);
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
        return peliculas;
    }
}
