package org.botanas.Chicharrikos.controller;

import org.botanas.Chicharrikos.model.Cliente; // Podría ser un DTO específico para registro/login
import org.botanas.Chicharrikos.service.ClienteService; // O un AuthService dedicado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/Chicharrikos/auth")
@CrossOrigin(origins = "http://127.0.0.1:5501") // Permite desde ambos orígenes
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
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", "Inicio de sesión exitoso");
            }});
        } else {
            return new ResponseEntity<>(new HashMap<String, String>() {{
                put("mensaje", "Credenciales inválidas");
            }}, HttpStatus.UNAUTHORIZED);
        }
    }
    }
