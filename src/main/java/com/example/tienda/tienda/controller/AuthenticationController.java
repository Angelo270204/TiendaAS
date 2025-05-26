package com.example.tienda.tienda.controller;

import com.example.tienda.tienda.model.Usuario;
import com.example.tienda.tienda.Repository.UsuarioRepository;
import com.example.tienda.tienda.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // Get the first role from the user's authorities
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            // Remove "ROLE_" prefix if it exists
            if (role.startsWith("ROLE_")) {
                role = role.substring(5);
            }
            
            final String finalRole = role;
            String token = jwtUtil.generarToken(userDetails.getUsername(), finalRole);

            // Find the user by email (which is used as username)
            Usuario user = usuarioRepository.findByCorreo(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            
            // Usar LinkedHashMap para mantener el orden de inserción
            Map<String, Object> userData = new LinkedHashMap<>();
            userData.put("id", user.getId());
            userData.put("nombre", user.getNombre());
            userData.put("correo", user.getCorreo());
            userData.put("role", finalRole);
            
            response.put("user", userData);
            

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }
}
