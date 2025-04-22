package com.example.tienda.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.tienda.model.Compra;
import com.example.tienda.tienda.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService servicio;

    @GetMapping
    public List<Compra> listarCompras() {
        return servicio.listarTodas();
    }

    @GetMapping("/{id}")
    public Compra obtenerCompra(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Compra> listarPorUsuario(@PathVariable Long usuarioId) {
        return servicio.obtenerPorUsuarioId(usuarioId);
    }

    @PostMapping
    public String agregarCompra(@RequestBody Compra compra) {
        servicio.agregarCompra(compra);
        return "Compra registrada.";
    }
}
