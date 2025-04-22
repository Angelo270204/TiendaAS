package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Usuario;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();
    private Long nextId = 1L;

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public void agregarUsuario(Usuario usuario) {
        usuario.setId(nextId++);
        usuarios.add(usuario);
    }

    public boolean eliminarUsuario(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}
