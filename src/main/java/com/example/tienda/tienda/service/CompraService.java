package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Compra;

@Service
public class CompraService {
    private List<Compra> compras = new ArrayList<>();
    private Long nextId = 1L;

    public List<Compra> listarTodas() {
        return compras;
    }

    public Optional<Compra> obtenerPorId(Long id) {
        return compras.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void agregarCompra(Compra compra) {
        compra.setId(nextId++);
        compras.add(compra);
    }

    public List<Compra> obtenerPorUsuarioId(Long usuarioId) {
        List<Compra> resultado = new ArrayList<>();
        for (Compra c : compras) {
            if (c.getUsuario().getId().equals(usuarioId)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
