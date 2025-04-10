package org.botanas.Chicharrikos.service;

import org.botanas.Chicharrikos.exception.ClienteNotFoundException;
import org.botanas.Chicharrikos.model.Cliente;
import org.botanas.Chicharrikos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtener todos los clientes
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    // Crear un nuevo cliente
    public Cliente createCliente(Cliente nuevoCliente) {
        return clienteRepository.save(nuevoCliente);
    }

    // Buscar cliente por ID
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    // Recuperar un cliente por email
    public Cliente findByCorreo(String correo){
        return clienteRepository.findByCorreo(correo);
    }

    // Eliminar cliente por ID
    public void deleteCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new ClienteNotFoundException(id);
        }
    }

    // Actualizar cliente por ID
    public Cliente updateCliente(Cliente cliente, Integer id) {
        return clienteRepository.findById(id)
                .map(clienteMap -> {
                    clienteMap.setNombre(cliente.getNombre());
                    clienteMap.setCorreo(cliente.getCorreo());
                    clienteMap.setDireccion(cliente.getDireccion());
                    clienteMap.setTelefono(cliente.getTelefono());
                    clienteMap.setContraseña(cliente.getContraseña());
                    return clienteRepository.save(clienteMap);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }


}
