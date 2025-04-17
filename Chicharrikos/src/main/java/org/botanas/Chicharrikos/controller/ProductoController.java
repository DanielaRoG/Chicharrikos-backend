package org.botanas.Chicharrikos.controller;

import org.botanas.Chicharrikos.model.Producto;
import org.botanas.Chicharrikos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Chicharrikos/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id);
    }


    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto updatedProducto) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(updatedProducto.getNombre());
            producto.setPrecio(updatedProducto.getPrecio());
            producto.setExistencia(updatedProducto.getExistencia());
            producto.setCategoria(updatedProducto.getCategoria());
            return productoRepository.save(producto);
        }).orElseGet(() -> {
            updatedProducto.setIdproducto(id);
            return productoRepository.save(updatedProducto);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoRepository.deleteById(id);
    }
}
