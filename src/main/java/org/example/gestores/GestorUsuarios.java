package org.example.gestores;

import org.example.modelo.Usuario;
import org.example.exceptions.UsuarioYaExistenteException;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;
    private double descuentoUsuarioVip = 30.0;

    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public Usuario agregarUsuario(Usuario usuarioNuevo) throws UsuarioYaExistenteException {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == usuarioNuevo.getIdUsuario()) {
                throw new UsuarioYaExistenteException("Ya existe el usuario");
            }
        }
        usuarios.add(usuarioNuevo);
        return usuarioNuevo;
    }

    public void cargarUsuarioExistente(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public int generarNuevoId() {
        int maximoEncontrado = 0;
        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                if (usuario.getId() > maximoEncontrado) {
                    maximoEncontrado = usuario.getId();
                }
            }
        }
        return maximoEncontrado + 1;
    }

    public double getDescuentoUsuarioVip() {
        return descuentoUsuarioVip;
    }
}
