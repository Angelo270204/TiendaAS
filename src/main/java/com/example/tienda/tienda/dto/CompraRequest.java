package com.example.tienda.tienda.dto;

import java.util.List;

public class CompraRequest {
    private Long usuarioId;
    private List<DetalleCompraRequest> detalles;
    
    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public List<DetalleCompraRequest> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<DetalleCompraRequest> detalles) {
        this.detalles = detalles;
    }
    
    public static class DetalleCompraRequest {
        private Long productoId;
        private int cantidad;
        
        // Getters y Setters
        public Long getProductoId() {
            return productoId;
        }
        
        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }
        
        public int getCantidad() {
            return cantidad;
        }
        
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}