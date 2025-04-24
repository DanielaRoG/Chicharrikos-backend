package org.botanas.Chicharrikos.exception;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(Long id) {
        super("No se encontró la categoría con ID: " + id);
    }
}