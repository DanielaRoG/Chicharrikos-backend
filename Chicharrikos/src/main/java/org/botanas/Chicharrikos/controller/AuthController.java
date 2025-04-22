package org.botanas.Chicharrikos.controller;

import org.botanas.Chicharrikos.model.Cliente; // Podría ser un DTO específico para registro/login
import org.botanas.Chicharrikos.service.ClienteService; // O un AuthService dedicado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Chicharrikos/auth")
public class AuthController {

    private final ClienteService clienteService; // Considera un AuthService

    @Autowired
    public AuthController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Cliente> registrarUsuario(@RequestBody Cliente nuevoCliente) {
        if (clienteService.findByCorreo(nuevoCliente.getCorreo()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 si el correo ya existe
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(nuevoCliente));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Cliente clienteLogin) {
        Cliente cliente = clienteService.findByCorreo(clienteLogin.getCorreo());
        if (cliente != null && cliente.getContraseña().equals(clienteLogin.getContraseña())) {
            // Aquí podrías generar un token JWT o iniciar una sesión
            return ResponseEntity.ok("Login exitoso");
        } else {
            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED); // 401
        }
    }
}