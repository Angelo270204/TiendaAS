package com.example.tienda.tienda.controller;
import com.example.tienda.tienda.model.Producto;
import com.example.tienda.tienda.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Producto obtenerProducto(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarProducto(@RequestBody Producto producto) {
        servicio.agregarProducto(producto);
        return "Producto agregado correctamente.";
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        boolean actualizado = servicio.actualizarProducto(id, producto);
        return actualizado ? "Producto actualizado." : "Producto no encontrado.";
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarProducto(id);
        return eliminado ? "Producto eliminado." : "Producto no encontrado.";
    }
}
