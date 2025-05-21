package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.MarcaRepository;
import com.example.tienda.tienda.model.Marca;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;
    
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }
    
    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }
    
    public Optional<Marca> obtenerPorId(Long id) {
        return marcaRepository.findById(id);
    }
    
    public Marca agregarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }
    
    public boolean actualizarMarca(Long id, Marca marcaActualizada) {
        Optional<Marca> marcaExistente = obtenerPorId(id);
        if (marcaExistente.isPresent()) {
            Marca m = marcaExistente.get();
            m.setNombre(marcaActualizada.getNombre());
            return true;
        }
        return false;
    }
    
    public boolean eliminarMarca(Long id) {
        return marcaRepository.existsById(id);
    }
}
