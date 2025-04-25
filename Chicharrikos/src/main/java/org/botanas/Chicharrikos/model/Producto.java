package org.botanas.Chicharrikos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name =  "producto")
public class Producto {

    @Id
    private Long idproducto;
    private String nombre;
    private Double precio;
    private Long existencia;
    private String url;

    @ManyToOne
    @JsonBackReference // le dice a Jackson: “esto es el reverso, no lo serialices para evitar bucles”.
    @JoinColumn(name = "id_categoria_id") // se relaciona con la columna categoria_id de la tabla producto
    private Categoria categoria;

    // constructor vacio para JBA
    public Producto(){
    }

    //Contructor
    public Producto(Long idproducto, String nombre, Double precio, Long existencia, Categoria categoria, String url) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;
        this.categoria = categoria;
        this.url = url;
    }

    // Getter and Setter

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getExistencia() {
        return existencia;
    }

    public void setExistencia(Long existencia) {
        this.existencia = existencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    //Tostring

    @Override
    public String toString() {
        return "Producto{" +
                "idproducto=" + idproducto +
                ", descripcion='" + nombre + '\'' +
                ", precio=" + precio +
                ", existencia=" + existencia +
                ", categoria=" + categoria +
                ", url=" + url+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(idproducto, producto.idproducto) && Objects.equals(nombre, producto.nombre) && Objects.equals(precio, producto.precio) && Objects.equals(existencia, producto.existencia) && Objects.equals(categoria, producto.categoria) && Objects.equals(url, producto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproducto, nombre, precio, existencia, categoria, url);
    }
}// class producto
