package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.ProductoRepository;
import com.example.tienda.tienda.model.Producto;


@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            productoRepository.save(producto);
            return true;
        }
        return false;
    }
}
