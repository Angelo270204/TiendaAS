package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.CategoriaRepository;
import com.example.tienda.tienda.model.Categoria;

@Service
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
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
        return categoriaRepository.existsById(id);
    }
}
