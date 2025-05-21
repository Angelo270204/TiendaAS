package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.CompraRepository;
import com.example.tienda.tienda.model.Compra;

@Service
public class CompraService {
    
    private final CompraRepository compraRepository;
    
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }
    
    public List<Compra> listarTodos() {
        return compraRepository.findAll();
    }
    
    public Optional<Compra> obtenerPorId(Long id) {
        return compraRepository.findById(id);
    }
    
    public Compra agregarCompra(Compra compra) {
        return compraRepository.save(compra);
    }
    
    public boolean actualizarCompra(Long id, Compra compraActualizada) {
        Optional<Compra> compraExistente = obtenerPorId(id);
        if (compraExistente.isPresent()) {
            Compra c = compraExistente.get();
            c.setFecha(compraActualizada.getFecha());
            return true;
        }
        return false;
    }
    
    public boolean eliminarCompra(Long id) {
        return compraRepository.existsById(id);
    }
}
