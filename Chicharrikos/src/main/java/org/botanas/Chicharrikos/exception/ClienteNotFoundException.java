package org.botanas.Chicharrikos.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Integer id) {
        super("Cliente con id: " + id + "no existe");
    }
}
