package com.example.sem3_a.persona;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persona") // esto se declara por defecto
public class Persona {
    @Id //todas las bases de datos necesitan un id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", unique = false)

    private String nombre;
    @Column(name = "apellido", unique = false)
    private String apellido;
}
