package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Oferta;

@Service
public class OfertaService {
    private List<Oferta> ofertas = new ArrayList<>();
    private Long nextId = 1L;

    public List<Oferta> listarTodas() {
        return ofertas;
    }

    public Optional<Oferta> obtenerPorId(Long id) {
        return ofertas.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public void agregarOferta(Oferta oferta) {
        oferta.setId(nextId++);
        ofertas.add(oferta);
    }

    public boolean eliminarOferta(Long id) {
        return ofertas.removeIf(o -> o.getId().equals(id));
    }
}
