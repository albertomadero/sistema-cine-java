package org.example.repositorios;

import org.example.modelo.Usuario;
import org.example.modelo.UsuarioNormal;
import org.example.modelo.UsuarioVip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios {

    public void guardarUsuarios(List<Usuario> usuarios) {
        try (FileWriter writer = new FileWriter("usuarios.csv")){
            for (Usuario u : usuarios) {
                double descuento;
                String tipoUsuario;
                if (u instanceof UsuarioVip) {
                    descuento = ((UsuarioVip) u).getPorcentajeDescuento();
                    tipoUsuario = "VIP";
                } else {
                    descuento = 0;
                    tipoUsuario = "NORMAL";
                }
                writer.write(u.getIdUsuario() + "," + u.getNombre() + "," + u.getEdad() + "," + tipoUsuario + "," + descuento + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al guardar reservas: " + e.getMessage());
        }
    }

    public List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.csv"))){
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                int idUsuario = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                int edad = Integer.parseInt(partes[2]);
                String tipo = partes[3];
                double descuento = Double.parseDouble(partes[4]);

                if (tipo.equals("VIP")) {
                    UsuarioVip usuarioVip = new UsuarioVip(idUsuario, nombre, edad, descuento);
                    usuarios.add(usuarioVip);
                } else {
                    UsuarioNormal usuarioNormal = new UsuarioNormal(idUsuario, nombre, edad);
                    usuarios.add(usuarioNormal);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return usuarios;
    }
}
