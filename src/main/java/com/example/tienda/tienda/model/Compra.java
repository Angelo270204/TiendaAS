package com.example.tienda.tienda.model;

import java.util.List;

public class Compra {
    private Long id;
    private Usuario usuario;
    private List<Producto> productos;
    private Double total;
    private String fecha;

    public Compra(){

    }

    public Compra(Long id, Usuario usuario, List<Producto> productos, Double total, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.productos = productos;
        this.total = total;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
