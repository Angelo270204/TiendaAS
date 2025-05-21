package com.example.tienda.tienda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;
    @Column(name = "fecha_emision", nullable = false)
    private String fechaEmision;
    @Column(name = "numero_factura", nullable = false)
    private String numeroFactura;
    @Column(name = "detalles", nullable = false)
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
