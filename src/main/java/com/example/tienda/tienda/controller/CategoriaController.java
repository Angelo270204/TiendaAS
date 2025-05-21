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

import com.example.tienda.tienda.model.Categoria;
import com.example.tienda.tienda.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService servicio;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return servicio.listarTodos();
    }

    @GetMapping("/{id}")
    public Categoria obtenerCategoria(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarCategoria(@RequestBody Categoria categoria) {
        servicio.agregarCategoria(categoria);
        return "Categoría agregada correctamente.";
    }

    @PutMapping("/{id}")
    public String actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        boolean actualizado = servicio.actualizarCategoria(id, categoria);
        return actualizado ? "Categoría actualizada." : "Categoría no encontrada.";
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarCategoria(id);
        return eliminado ? "Categoría eliminada." : "Categoría no encontrada.";
    }
}
