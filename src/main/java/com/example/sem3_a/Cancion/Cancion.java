package com.example.sem3_a.Cancion;

import com.example.sem3_a.Artista.Artista;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import java.util.ArrayList;

@Entity
@Data

public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String titulo;
    Integer duracion;
    @ManyToMany
    @JoinTable(
            name = "cancion_artista",
            joinColumns = @JoinColumn(name = "cancion_id"),
            inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas = new ArrayList<>();
}
