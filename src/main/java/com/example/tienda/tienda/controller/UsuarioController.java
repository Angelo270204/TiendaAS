package com.example.tienda.tienda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.tienda.tienda.dto.UsuarioDto;
import com.example.tienda.tienda.model.Usuario;
import com.example.tienda.tienda.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService servicio;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsuarioDto> listarUsuarios() {
        return servicio.listarTodos().stream()
                .map(UsuarioDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(UsuarioDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {
        servicio.agregarUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado correctamente.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        }
        return ResponseEntity.notFound().build();
    }
}
