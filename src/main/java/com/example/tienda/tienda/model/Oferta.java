package com.example.tienda.tienda.model;

public class Oferta {
    private Long id;
    private String titulo;
    private String descripcion;
    private Double descuento; 
    private Producto producto;

    public Oferta(){

    }

    public Oferta(Long id, String titulo, String descripcion, Double descuento, Producto producto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
}
