package com.example.tienda.tienda.service;

import com.example.tienda.tienda.Repository.ProductoRepository;
import com.example.tienda.tienda.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    
    @Transactional
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    @Transactional
    public boolean eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }
    
    @Transactional
    public void actualizarStock(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        producto.descontarStock(cantidad);
        productoRepository.save(producto);
    }
    
    public boolean verificarStockDisponible(Long productoId, int cantidad) {
        return productoRepository.findById(productoId)
            .map(producto -> producto.tieneStockSuficiente(cantidad))
            .orElse(false);
    }
}