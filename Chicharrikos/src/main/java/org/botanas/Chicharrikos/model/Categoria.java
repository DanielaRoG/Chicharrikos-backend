package org.botanas.Chicharrikos.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    private Long categoria_id;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<org.botanas.Chicharrikos.model.Producto> productos;

    public Categoria() {}

    public Categoria(Long categoria_id, String nombre) {
        this.categoria_id = categoria_id;
        this.nombre = nombre;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
