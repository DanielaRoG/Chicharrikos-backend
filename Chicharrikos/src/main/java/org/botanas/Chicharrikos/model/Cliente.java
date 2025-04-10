package org.botanas.Chicharrikos.model;

import jakarta.persistence.*;
import lombok.Data;


    @Entity
    @Data
    @Table(name = "cliente")
    public class Cliente {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idcliente;

        private String nombre;
        private String correo;
        private String direccion;
        private String telefono;
        private String contraseña;

}



