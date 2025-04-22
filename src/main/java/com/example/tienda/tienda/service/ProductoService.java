package com.example.tienda.tienda.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tienda.tienda.model.Categoria;
import com.example.tienda.tienda.model.Marca;
import com.example.tienda.tienda.model.Producto;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    private long nextId = 1;

    public ProductoService() {
        // Agregando productos de ejemplo
        productos.add(new Producto(nextId++, "Refrigeradora", 1800.0, 4,
                new Marca(1L, "LG"), new Categoria(1L, "Refrigeradoras")));

        productos.add(new Producto(nextId++, "Lavadora", 1500.0, 6,
                new Marca(2L, "Samsung"), new Categoria(2L, "Lavadoras")));
    }

    public List<Producto> listarTodos() {
        return productos;
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void agregarProducto(Producto producto) {
        producto.setId(nextId++);
        productos.add(producto);
    }

    public boolean actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoExistente = obtenerPorId(id);
        if (productoExistente.isPresent()) {
            Producto p = productoExistente.get();
            p.setNombre(productoActualizado.getNombre());
            p.setPrecio(productoActualizado.getPrecio());
            p.setStock(productoActualizado.getStock());
            p.setMarca(productoActualizado.getMarca());
            p.setCategoria(productoActualizado.getCategoria());
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(Long id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }
}
