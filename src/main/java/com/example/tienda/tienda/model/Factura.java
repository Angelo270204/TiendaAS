package com.example.tienda.tienda.model;

public class Factura {
    private Long id;
    private Compra compra;
    private String fechaEmision;
    private String numeroFactura;
    private String detalles;

    public Factura() {
    }

    public Factura(Long id, Compra compra, String fechaEmision, String numeroFactura, String detalles) {
        this.id = id;
        this.compra = compra;
        this.fechaEmision = fechaEmision;
        this.numeroFactura = numeroFactura;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}
