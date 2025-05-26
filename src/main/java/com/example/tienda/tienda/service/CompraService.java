package com.example.tienda.tienda.service;

import com.example.tienda.tienda.Repository.CompraRepository;
import com.example.tienda.tienda.model.Compra;
import com.example.tienda.tienda.model.DetalleCompra;
import com.example.tienda.tienda.model.EstadoCompra;
import com.example.tienda.tienda.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final ProductoService productoService;
    private final DetalleCompraService detalleCompraService;
    
    public CompraService(CompraRepository compraRepository, 
                        ProductoService productoService,
                        DetalleCompraService detalleCompraService) {
        this.compraRepository = compraRepository;
        this.productoService = productoService;
        this.detalleCompraService = detalleCompraService;
    }
    
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }
    
    public List<Compra> obtenerPorUsuarioId(Long usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }
    
    public Optional<Compra> obtenerPorId(Long id) {
        return compraRepository.findById(id);
    }
    
    @Transactional
    public Compra crearCompra(Compra compra) {
        // Validar que la compra tenga detalles
        if (compra.getDetalles() == null || compra.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La compra debe tener al menos un detalle");
        }
        
        // Validar stock y preparar los detalles
        for (DetalleCompra detalle : compra.getDetalles()) {
            if (!productoService.verificarStockDisponible(
                    detalle.getProducto().getId(), detalle.getCantidad())) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + 
                        detalle.getProducto().getNombre());
            }
        }
        
        // Establecer fecha y estado
        compra.setFechaCompra(new Date());
        compra.setEstado(EstadoCompra.PENDIENTE);
        
        // Guardar la compra
        Compra compraGuardada = compraRepository.save(compra);
        
        // Actualizar stock y guardar detalles
        for (DetalleCompra detalle : compra.getDetalles()) {
            detalle.setCompra(compraGuardada);
            detalle.setPrecioUnitario(detalle.getProducto().getPrecio());
            detalle.calcularSubtotal();
            
            // Guardar el detalle
            detalleCompraService.guardarDetalle(detalle);
            
            // Actualizar stock
            productoService.actualizarStock(
                detalle.getProducto().getId(), 
                detalle.getCantidad()
            );
        }
        
        // Calcular y actualizar el total
        compraGuardada.calcularTotal();
        return compraRepository.save(compraGuardada);
    }
    
    @Transactional
    public boolean actualizarCompra(Long id, Compra compra) {
        if (compraRepository.existsById(id)) {
            compra.setId(id);
            compraRepository.save(compra);
            return true;
        }
        return false;
    }
    
    @Transactional
    public boolean eliminarCompra(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Transactional
    public Compra actualizarEstadoCompra(Long id, EstadoCompra nuevoEstado) {
        Compra compra = compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada"));
        
        // Validar transición de estados
        if (nuevoEstado == EstadoCompra.CANCELADA && 
            compra.getEstado() != EstadoCompra.PENDIENTE) {
            throw new IllegalStateException(
                "Solo se puede cancelar una compra en estado PENDIENTE");
        }
        
        compra.setEstado(nuevoEstado);
        
        // Si se cancela la compra, devolver el stock
        if (nuevoEstado == EstadoCompra.CANCELADA) {
            for (DetalleCompra detalle : compra.getDetalles()) {
                Producto producto = detalle.getProducto();
                producto.incrementarStock(detalle.getCantidad());
                productoService.agregarProducto(producto);
            }
        }
        
        return compraRepository.save(compra);
    }
}