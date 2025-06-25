package com.example.tienda.tienda.controller;

import com.example.tienda.tienda.dto.CompraRequest;
import com.example.tienda.tienda.model.Compra;
import com.example.tienda.tienda.model.DetalleCompra;
import com.example.tienda.tienda.model.EstadoCompra;
import com.example.tienda.tienda.model.Producto;
import com.example.tienda.tienda.model.Usuario;
import com.example.tienda.tienda.service.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarTodasLasCompras() {
        return ResponseEntity.ok(compraService.listarTodas());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compra>> listarComprasPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(compraService.obtenerPorUsuarioId(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompra(@PathVariable Long id) {
        return compraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Compra> crearCompra(@RequestBody CompraRequest compraRequest) {
        // Convertir DTO a entidad
        Usuario usuario = new Usuario();
        usuario.setId(compraRequest.getUsuarioId());

        Compra compra = new Compra();
        compra.setUsuario(usuario);

        // Convertir detalles del DTO a entidades
        for (CompraRequest.DetalleCompraRequest detalleRequest : compraRequest.getDetalles()) {
            Producto producto = new Producto();
            producto.setId(detalleRequest.getProductoId());

            DetalleCompra detalle = new DetalleCompra();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleRequest.getCantidad());

            compra.agregarDetalle(detalle);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(compraService.crearCompra(compra));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Compra> actualizarEstadoCompra(
            @PathVariable Long id,
            @RequestParam EstadoCompra estado) {

        try {
            return ResponseEntity.ok(compraService.actualizarEstadoCompra(id, estado));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        if (compraService.eliminarCompra(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}