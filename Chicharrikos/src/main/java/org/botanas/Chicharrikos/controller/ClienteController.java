package org.botanas.Chicharrikos.controller;

import jakarta.validation.Valid;
import org.botanas.Chicharrikos.exception.ClienteNotFoundException;
import org.botanas.Chicharrikos.model.Cliente;
import org.botanas.Chicharrikos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501") // Permite peticiones desde tu frontend
@RequestMapping("/api/Chicharrikos/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Mappear metodo de Cliente Service getClientes();
    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.getClientes();
    }

    // Si existe informacion lanzo status 409
    // Si no existe informacion, creo el usuario y lanzo un status 201
    @PostMapping
    public ResponseEntity<Cliente> createUser(@Valid @RequestBody Cliente newCliente) {
        if(clienteService.findByCorreo(newCliente.getCorreo()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT); //409
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(newCliente)); //201
    }

    // Metodo para obtener usuario por id (404 NotFound y 200 OK)
    @GetMapping("{id}") // El path es variable
    public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(clienteService.findById(id));
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para eliminar usuario por id (204 y 404)
    @DeleteMapping("/eliminar-cliente/{id}")
    public ResponseEntity<Cliente> deleteUser(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Metodo para updateUser (200 y 404)
    @PutMapping("/actualizar-cliente/{id}")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable Integer id,
            @Valid @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.updateCliente(cliente, id));
        } catch (ClienteNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    // Metodo para recuperar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> getByCorreo(@PathVariable String correo){
        Cliente clienteByCorreo = clienteService.findByCorreo(correo);
        if (clienteByCorreo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteByCorreo);
    }

}