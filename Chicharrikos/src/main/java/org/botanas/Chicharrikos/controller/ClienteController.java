package org.botanas.Chicharrikos.controller;

import org.botanas.Chicharrikos.model.Cliente;
import org.botanas.Chicharrikos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController //indica que es un controlador REST.
@RequestMapping("/api")//mapear solicitudes HTTP a métodos de controladores
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}

