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

import com.example.tienda.tienda.model.Marca;
import com.example.tienda.tienda.service.MarcaService;

@RestController
@RequestMapping("/marcas")
public class MarcaControlller {
    @Autowired
    private MarcaService servicio;

    @GetMapping
    public List<Marca> listarMarcas() {
        return servicio.listarTodas();
    }

    @GetMapping("/{id}")
    public Marca obtenerMarca(@PathVariable Long id) {
        return servicio.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public String agregarMarca(@RequestBody Marca marca) {
        servicio.agregarMarca(marca);
        return "Marca agregada correctamente.";
    }

    @PutMapping("/{id}")
    public String actualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        boolean actualizado = servicio.actualizarMarca(id, marca);
        return actualizado ? "Marca actualizada." : "Marca no encontrada.";
    }

    @DeleteMapping("/{id}")
    public String eliminarMarca(@PathVariable Long id) {
        boolean eliminado = servicio.eliminarMarca(id);
        return eliminado ? "Marca eliminada." : "Marca no encontrada.";
    }
}
