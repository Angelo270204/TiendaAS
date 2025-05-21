package com.example.tienda.tienda.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.tienda.tienda.model.Oferta;
import com.example.tienda.tienda.service.OfertaService;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {
    @Autowired
    private OfertaService servicio;

    @GetMapping
    public List<Oferta> listarOfertas() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> obtenerOferta(@PathVariable Long id) {
        Optional<Oferta> oferta = servicio.obtenerPorId(id);
        return oferta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Oferta> agregarOferta(@RequestBody Oferta oferta) {
        Oferta nuevaOferta = servicio.agregarOferta(oferta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOferta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarOferta(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
