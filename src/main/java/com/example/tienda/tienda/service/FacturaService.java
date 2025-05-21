package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.FacturaRepository;
import com.example.tienda.tienda.model.Factura;

@Service
public class FacturaService {
  
    private final FacturaRepository facturaRepository;
    
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }
    
    public List<Factura> listarTodos() {
        return facturaRepository.findAll();
    }
    
    public Optional<Factura> obtenerPorId(Long id) {
        return facturaRepository.findById(id);
    }
    
    public Factura agregarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }
    
    public boolean actualizarFactura(Long id, Factura facturaActualizada) {
        Optional<Factura> facturaExistente = obtenerPorId(id);
        if (facturaExistente.isPresent()) {
            Factura f = facturaExistente.get();
            f.setFechaEmision(facturaActualizada.getFechaEmision());
            f.setNumeroFactura(facturaActualizada.getNumeroFactura());
            f.setDetalles(facturaActualizada.getDetalles());
            return true;
        }
        return false;
    }
    
    public boolean eliminarFactura(Long id) {
        return facturaRepository.existsById(id);
    }
}
