package com.example.sem3_a.Artista;


import com.example.sem3_a.Cancion.Cancion;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity // necesita un id
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //tipo UUID id, son ids unicos y complejos

    @Column(unique = true)
    private String username;

    private String descripcion;

    private String email;

    @ManyToMany(mappedBy ="artistas")
    private List<Cancion> canciones;


}
