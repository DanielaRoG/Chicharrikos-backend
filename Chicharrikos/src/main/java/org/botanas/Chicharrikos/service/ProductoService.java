package org.botanas.Chicharrikos.service;

import org.botanas.Chicharrikos.exception.ProductoNotFoundException;
import org.botanas.Chicharrikos.model.Categoria;
import org.botanas.Chicharrikos.model.Producto;
import org.botanas.Chicharrikos.repository.CategoriaRepository;
import org.botanas.Chicharrikos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto createProducto(Producto nuevoProducto) {
        if (nuevoProducto.getCategoria() != null && nuevoProducto.getCategoria().getCategoria_id() != null) {
            Categoria categoria = categoriaRepository.findById(nuevoProducto.getCategoria().getCategoria_id())
                    .orElse(null); // Devuelve null si no se encuentra la categoría
            nuevoProducto.setCategoria(categoria);
        }
        return productoRepository.save(nuevoProducto);
    }

    public Producto updateProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setExistencia(productoActualizado.getExistencia());
                    if (productoActualizado.getCategoria() != null && productoActualizado.getCategoria().getCategoria_id() != null) {
                        Categoria categoria = categoriaRepository.findById(productoActualizado.getCategoria().getCategoria_id())
                                .orElse(null);
                        producto.setCategoria(categoria);
                    }
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public void deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new ProductoNotFoundException(id);
        }
    }
}