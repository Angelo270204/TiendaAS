package com.example.tienda.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        return servicio.listarTodos();
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

    @PutMapping("/{id}")
    public String actualizarFactura(@PathVariable Long id, @RequestBody Factura factura) {
        boolean actualizado = servicio.actualizarFactura(id, factura);
        return actualizado ? "Factura actualizada." : "Factura no encontrada.";
    }

    @DeleteMapping("/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarFactura(id);
        return eliminado ? "Factura eliminada." : "Factura no encontrada.";
    }
}
