package com.example.tienda.tienda.Repository;

import com.example.tienda.tienda.model.Oferta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    
}
