package com.example.tienda.tienda.model;

public class Producto {
    private Long id;
    private String nombre;
    private double precio;
    private int stock;
    private Marca marca;
    private Categoria categoria;

    public Producto() {}

    public Producto(Long id, String nombre, double precio, int stock, Marca marca, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.marca = marca;
        this.categoria = categoria;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public Marca getMarca(){
        return marca;
    }

    public void setMarca(Marca marca){
        this.marca = marca;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }
}

