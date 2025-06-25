package com.example.tienda.tienda.controller;
import com.example.tienda.tienda.model.Producto;
import com.example.tienda.tienda.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @GetMapping
    public List<Producto> listarProductos() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> agregarProducto(@RequestBody Producto producto) {
        servicio.agregarProducto(producto);
        return ResponseEntity.ok("Producto agregado correctamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        boolean actualizado = servicio.actualizarProducto(id, producto);
        if (actualizado) {
            return ResponseEntity.ok("Producto actualizado.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarProducto(id);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado.");
        }
        return ResponseEntity.notFound().build();
    }
}
