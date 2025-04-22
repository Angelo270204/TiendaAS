package com.example.tienda.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.tienda.model.Factura;
import com.example.tienda.tienda.service.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    private FacturaService servicio;

    @GetMapping
    public List<Factura> listarFacturas() {
        return servicio.listarTodas();
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarFactura(@RequestBody Factura factura) {
        servicio.agregarFactura(factura);
        return "Factura generada.";
    }
}
