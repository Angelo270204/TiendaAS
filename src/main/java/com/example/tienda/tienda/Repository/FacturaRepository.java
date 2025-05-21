package com.example.tienda.tienda.Repository;

import com.example.tienda.tienda.model.Factura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    
}
