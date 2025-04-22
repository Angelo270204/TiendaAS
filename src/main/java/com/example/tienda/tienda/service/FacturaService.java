package com.example.tienda.tienda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.tienda.tienda.model.Factura;

@Service
public class FacturaService {
    private List<Factura> facturas = new ArrayList<>();
    private Long nextId = 1L;

    public List<Factura> listarTodas() {
        return facturas;
    }

    public Optional<Factura> obtenerPorId(Long id) {
        return facturas.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    public void agregarFactura(Factura factura) {
        factura.setId(nextId++);
        facturas.add(factura);
    }
}
