package com.example.tienda.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.tienda.model.Usuario;
import com.example.tienda.tienda.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService servicio;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarUsuario(@RequestBody Usuario usuario) {
        servicio.agregarUsuario(usuario);
        return "Usuario agregado correctamente.";
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarUsuario(id);
        return eliminado ? "Usuario eliminado." : "Usuario no encontrado.";
    }
}
