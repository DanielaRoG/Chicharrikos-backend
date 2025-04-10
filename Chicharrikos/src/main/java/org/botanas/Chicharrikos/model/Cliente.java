package org.botanas.Chicharrikos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;


@Entity
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

        public Cliente() {
        }

        public Cliente(Integer idcliente, String nombre, String correo, String direccion, String telefono, String contraseña) {
            this.idcliente = idcliente;
            this.nombre = nombre;
            this.correo = correo;
            this.direccion = direccion;
            this.telefono = telefono;
            this.contraseña = contraseña;
        }

        public Integer getIdcliente() {
            return idcliente;
        }

        public void setIdcliente(Integer idcliente) {
            this.idcliente = idcliente;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "idcliente=" + idcliente +
                    ", nombre='" + nombre + '\'' +
                    ", correo='" + correo + '\'' +
                    ", direccion='" + direccion + '\'' +
                    ", telefono='" + telefono + '\'' +
                    ", contraseña='" + contraseña + '\'' +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idcliente, cliente.idcliente) && Objects.equals(nombre, cliente.nombre) && Objects.equals(correo, cliente.correo) && Objects.equals(direccion, cliente.direccion) && Objects.equals(telefono, cliente.telefono) && Objects.equals(contraseña, cliente.contraseña);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcliente, nombre, correo, direccion, telefono, contraseña);
    }
}



