package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.OfertaRepository;
import com.example.tienda.tienda.model.Oferta;

@Service
public class OfertaService {
    private final OfertaRepository ofertaRepository;
    
    public OfertaService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }
    
    public List<Oferta> listarTodos() {
        return ofertaRepository.findAll();
    }
    
    public Optional<Oferta> obtenerPorId(Long id) {
        return ofertaRepository.findById(id);
    }
    
    public Oferta agregarOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }
    
    public boolean eliminarOferta(Long id) {
        if (ofertaRepository.existsById(id)) {
            ofertaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
