package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Categoria;

@Service
public class CategoriaService {
     private List<Categoria> categorias = new ArrayList<>();
    private long nextId = 1;

    public CategoriaService() {
        // Agregando categorías de ejemplo
        categorias.add(new Categoria(nextId++, "Refrigeradoras"));
        categorias.add(new Categoria(nextId++, "Lavadoras"));
        categorias.add(new Categoria(nextId++, "Cocinas"));
        categorias.add(new Categoria(nextId++, "Televisores"));
    }

    public List<Categoria> listarTodas() {
        return categorias;
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void agregarCategoria(Categoria categoria) {
        categoria.setId(nextId++);
        categorias.add(categoria);
    }

    public boolean actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Optional<Categoria> categoriaExistente = obtenerPorId(id);
        if (categoriaExistente.isPresent()) {
            Categoria c = categoriaExistente.get();
            c.setNombre(categoriaActualizada.getNombre());
            return true;
        }
        return false;
    }

    public boolean eliminarCategoria(Long id) {
        return categorias.removeIf(c -> c.getId().equals(id));
    }
}
