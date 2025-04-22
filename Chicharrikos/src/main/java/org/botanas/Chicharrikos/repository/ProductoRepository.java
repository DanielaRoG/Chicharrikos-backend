package org.botanas.Chicharrikos.repository;

import org.botanas.Chicharrikos.model.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
