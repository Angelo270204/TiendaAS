package com.example.tienda.tienda.service;

import com.example.tienda.tienda.Repository.DetalleCompraRepository;
import com.example.tienda.tienda.model.DetalleCompra;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetalleCompraService {
    private final DetalleCompraRepository detalleCompraRepository;
    
    public DetalleCompraService(DetalleCompraRepository detalleCompraRepository) {
        this.detalleCompraRepository = detalleCompraRepository;
    }
    
    @Transactional
    public DetalleCompra guardarDetalle(DetalleCompra detalle) {
        return detalleCompraRepository.save(detalle);
    }
    
    public List<DetalleCompra> obtenerDetallesPorCompra(Long compraId) {
        return detalleCompraRepository.findByCompraId(compraId);
    }
    
    @Transactional
    public void eliminarDetalle(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}