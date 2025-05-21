package com.example.tienda.tienda.dto;

import com.example.tienda.tienda.model.Usuario;

public class UsuarioDto {
    private final Long id;
    private final String nombre;
    private final String correo;
    private final String rol;  // Solo el nombre del rol

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.rol = usuario.getRoles().stream()
                .findFirst()
                .map(rol -> rol.getNombre())
                .orElse("SIN_ROL");
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }
}
