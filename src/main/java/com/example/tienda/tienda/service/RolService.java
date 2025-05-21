package com.example.tienda.tienda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.tienda.Repository.RolRepository;
import com.example.tienda.tienda.model.Rol;

@Service
public class RolService {
    private final RolRepository rolRepository;
    
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }
    
    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }
    
    public Optional<Rol> obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
    
    public Rol agregarRol(Rol rol) {
        return rolRepository.save(rol);
    }
    
    public boolean eliminarRol(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public boolean existePorNombre(String nombre) {
        return rolRepository.existsByNombre(nombre);
    }
}
