package org.botanas.Chicharrikos.controller;

import org.botanas.Chicharrikos.exception.CategoriaNotFoundException;
import org.botanas.Chicharrikos.model.Categoria;
import org.botanas.Chicharrikos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Chicharrikos/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.getCategoriaById(id));
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria nuevaCategoria) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.createCategoria(nuevaCategoria));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict si ya existe
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
        try {
            return ResponseEntity.ok(categoriaService.updateCategoria(id, categoriaActualizada));
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (CategoriaNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}