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
import org.springframework.web.bind.annotation.PutMapping;
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
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompra(@PathVariable Long id) {
        Optional<Compra> compra = servicio.obtenerPorId(id);
        return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compra> agregarCompra(@RequestBody Compra compra) {
        Compra nuevaCompra = servicio.agregarCompra(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarCompra(@PathVariable Long id, @RequestBody Compra compra) {
        boolean actualizado = servicio.actualizarCompra(id, compra);
        if (actualizado) {
            Optional<Compra> compraActualizada = servicio.obtenerPorId(id);
            return compraActualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarCompra(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
