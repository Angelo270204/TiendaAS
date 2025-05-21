package com.example.tienda.tienda.Repository;

import com.example.tienda.tienda.model.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    
}
