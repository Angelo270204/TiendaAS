package com.example.tienda.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.tienda.model.Rol;
import com.example.tienda.tienda.service.RolService;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private RolService servicio;

    @GetMapping
    public List<Rol> listarRoles() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRol(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(rol -> new ResponseEntity<>(rol, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Rol> agregarRol(@RequestBody Rol rol) {
        if (servicio.existePorNombre(rol.getNombre())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Rol nuevoRol = servicio.agregarRol(rol);
        return new ResponseEntity<>(nuevoRol, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarRol(id);
        if (eliminado) {
            return new ResponseEntity<>("Rol eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
