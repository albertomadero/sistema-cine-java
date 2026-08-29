package org.example.repositorios;

import org.example.modelo.Asiento;
import org.example.modelo.AsientoDiscapacitado;
import org.example.modelo.AsientoNormal;
import org.example.modelo.AsientoVip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioAsientos {
    public void guardarAsientos(List<Asiento> asientos) {
        try (FileWriter writer = new FileWriter("asientos.csv")){
            for (Asiento asiento : asientos) {
                double porcentajeRecargo;
                String tipoAsiento;
                if (asiento instanceof AsientoVip) {
                    porcentajeRecargo = ((AsientoVip) asiento).getPorcentajeRecargo();
                    tipoAsiento = "VIP";
                } else if (asiento instanceof AsientoNormal) {
                    porcentajeRecargo = 0;
                    tipoAsiento = "NORMAL";
                } else {
                    porcentajeRecargo = 0;
                    tipoAsiento = "DISCAPACITADO";
                }
                writer.write(asiento.getIdAsiento() + "," + asiento.getNumeroAsiento() + "," + tipoAsiento + "," + asiento.getIdSala() + "," + porcentajeRecargo + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar archivo asientos.csv: " + e.getMessage());
        }
    }

    public List<Asiento> leerAsientos() {
        List<Asiento> asientos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("asientos.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idAsiento = Integer.parseInt(partes[0]);
                String numeroAsiento = partes[1];
                String tipoAsiento = partes[2];
                int idSala = Integer.parseInt(partes[3]);
                double porcentajeRecargo = Double.parseDouble(partes[4]);


                if (tipoAsiento.equals("VIP")) {
                    AsientoVip asientoVip = new AsientoVip(idAsiento, numeroAsiento, idSala, porcentajeRecargo);
                    asientos.add(asientoVip);
                } else if (tipoAsiento.equals("NORMAL")) {
                    AsientoNormal asientoNormal = new AsientoNormal(idAsiento, numeroAsiento, idSala);
                    asientos.add(asientoNormal);
                } else if (tipoAsiento.equals("DISCAPACITADO")) {
                    AsientoDiscapacitado asientoDiscapacitado = new AsientoDiscapacitado(idAsiento, numeroAsiento, idSala);
                    asientos.add(asientoDiscapacitado);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo asientos.csv: " + e.getMessage());
        }
        return asientos;
    }
}
