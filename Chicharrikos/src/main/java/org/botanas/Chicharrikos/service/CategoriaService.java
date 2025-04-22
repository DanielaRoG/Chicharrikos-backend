package org.botanas.Chicharrikos.service;

import org.botanas.Chicharrikos.exception.CategoriaNotFoundException;
import org.botanas.Chicharrikos.model.Categoria;
import org.botanas.Chicharrikos.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria createCategoria(Categoria nuevaCategoria) {
        if (categoriaRepository.findByNombre(nuevaCategoria.getNombre()) != null) {
            // Considera lanzar una excepción específica para categoría existente
            throw new RuntimeException("Ya existe una categoría con este nombre.");
        }
        return categoriaRepository.save(nuevaCategoria);
    }

    public void deleteCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new CategoriaNotFoundException(id);
        }
    }

    public Categoria updateCategoria(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }
}