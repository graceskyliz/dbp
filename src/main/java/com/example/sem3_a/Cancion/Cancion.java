package com.example.sem3_a.Cancion;

import com.example.sem3_a.Artista.Artista;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String titulo;
    String autor;
    String genero;
    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;
}
