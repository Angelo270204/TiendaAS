package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Marca;

@Service
public class MarcaService {
    private List<Marca> marcas = new ArrayList<>();
    private long nextId = 1;

    public MarcaService() {
        // Agregando marcas de ejemplo
        marcas.add(new Marca(nextId++, "LG"));
        marcas.add(new Marca(nextId++, "Samsung"));
        marcas.add(new Marca(nextId++, "Panasonic"));
    }

    public List<Marca> listarTodas() {
        return marcas;
    }

    public Optional<Marca> obtenerPorId(Long id) {
        return marcas.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public void agregarMarca(Marca marca) {
        marca.setId(nextId++);
        marcas.add(marca);
    }

    public boolean actualizarMarca(Long id, Marca marcaActualizada) {
        Optional<Marca> marcaExistente = obtenerPorId(id);
        if (marcaExistente.isPresent()) {
            Marca m = marcaExistente.get();
            m.setNombre(marcaActualizada.getNombre());
            return true;
        }
        return false;
    }

    public boolean eliminarMarca(Long id) {
        return marcas.removeIf(m -> m.getId().equals(id));
    }
}
