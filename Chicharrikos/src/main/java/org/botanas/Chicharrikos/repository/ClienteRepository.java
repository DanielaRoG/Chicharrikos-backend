package org.botanas.Chicharrikos.repository;

import org.botanas.Chicharrikos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCorreo(String correo);
}




