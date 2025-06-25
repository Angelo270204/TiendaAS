package com.example.tienda.tienda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tienda.tienda.dto.UsuarioDto;
import com.example.tienda.tienda.dto.RegistroUsuarioDto;
import com.example.tienda.tienda.model.Usuario;
import com.example.tienda.tienda.model.Rol;
import com.example.tienda.tienda.service.UsuarioService;
import com.example.tienda.tienda.service.RolService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService servicio;

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        return servicio.listarTodos().stream()
                .map(UsuarioDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(UsuarioDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {
        servicio.agregarUsuario(usuario);
        return ResponseEntity.ok("Usuario agregado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id) {
        Usuario eliminado = servicio.eliminarUsuario(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint público para registro
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroUsuarioDto registroDto) {
        // Verificar si el correo ya existe
        if (servicio.listarTodos().stream().anyMatch(u -> u.getCorreo().equals(registroDto.getCorreo()))) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        // Buscar el rol CLIENTE
        Rol rolCliente = rolService.obtenerPorNombre("CLIENTE")
                .orElseGet(() -> rolService.agregarRol(new Rol("CLIENTE")));

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setCorreo(registroDto.getCorreo());
        usuario.setContraseña(registroDto.getContraseña());
        usuario.addRol(rolCliente);

        Usuario guardado = servicio.agregarUsuario(usuario);

        return ResponseEntity.ok(new UsuarioDto(guardado));
    }
}
