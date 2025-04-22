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

import com.example.tienda.tienda.model.Oferta;
import com.example.tienda.tienda.service.OfertaService;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {
    @Autowired
    private OfertaService servicio;

    @GetMapping
    public List<Oferta> listarOfertas() {
        return servicio.listarTodas();
    }

    @GetMapping("/{id}")
    public Oferta obtenerOferta(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarOferta(@RequestBody Oferta oferta) {
        servicio.agregarOferta(oferta);
        return "Oferta agregada correctamente.";
    }

    @DeleteMapping("/{id}")
    public String eliminarOferta(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarOferta(id);
        return eliminado ? "Oferta eliminada." : "Oferta no encontrada.";
    }
}
